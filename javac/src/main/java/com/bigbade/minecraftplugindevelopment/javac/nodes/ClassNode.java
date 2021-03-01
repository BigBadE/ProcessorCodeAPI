package com.bigbade.minecraftplugindevelopment.javac.nodes;

import com.bigbade.minecraftplugindevelopment.api.code.IClassType;
import com.bigbade.minecraftplugindevelopment.api.code.parameter.IParameterType;
import com.bigbade.minecraftplugindevelopment.api.nodes.IClassNode;
import com.bigbade.minecraftplugindevelopment.api.nodes.IMethodNode;
import com.bigbade.minecraftplugindevelopment.api.nodes.builder.IMethodNodeBuilder;
import com.bigbade.minecraftplugindevelopment.api.pools.MethodPool;
import com.bigbade.minecraftplugindevelopment.javac.code.JavacCodeBlock;
import com.bigbade.minecraftplugindevelopment.javac.expressions.LiteralExpression;
import com.bigbade.minecraftplugindevelopment.javac.nodes.builder.MethodNodeBuilder;
import com.bigbade.minecraftplugindevelopment.javac.pools.JavacMethodPool;
import com.bigbade.minecraftplugindevelopment.javac.utils.JavacInternals;
import com.bigbade.minecraftplugindevelopment.javac.utils.ListUtils;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.tree.JCTree;
import lombok.Getter;

import javax.annotation.Nullable;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
public class ClassNode implements IClassNode {
    private final MethodPool<MethodNode> methodPool;

    @Getter
    private final TypeElement classElement;
    @Getter
    private final JavacInternals internals;

    @Getter
    private final JCTree.JCClassDecl classDecl;

    /**
     * Instantiates a class node from an element and internals.
     * This should only be done through the class pool
     *
     * @param element   Element representing the class
     * @param internals Javac internals bundle
     * @see com.bigbade.minecraftplugindevelopment.javac.pools.JavacClassPool
     */
    public ClassNode(TypeElement element, JavacInternals internals) {
        this.classElement = element;
        this.internals = internals;
        classDecl = (JCTree.JCClassDecl) internals.getTrees().getTree(element);
        methodPool = new JavacMethodPool(internals);
    }

    @SuppressWarnings("unchecked")
    private static <E, T extends E> List<T> castInternalList(com.sun.tools.javac.util.List<E> base) {
        return (List<T>) base;
    }

    @Override
    public IMethodNode getMethod(ExecutableElement element) {
        if (!classElement.getEnclosedElements().contains(element)) {
            throw new IllegalStateException("Tried to get element " + element.getSimpleName() + " from class "
                    + classElement.getSimpleName() + ", but no such method existed!");
        }
        return methodPool.getObject(element);
    }

    @Override
    public List<IMethodNode> findMethods(@Nullable String name, @Nullable IParameterType[] parameters,
                                         @Nullable IParameterType returnType, @Nullable Integer modifiers) {
        Set<Modifier> modifierSet = modifiers == null ? null : Flags.asModifierSet(modifiers);
        List<IMethodNode> found = new ArrayList<>();
        for (Element enclosed : classElement.getEnclosedElements()) {
            if (enclosed instanceof ExecutableElement) {
                ExecutableElement method = (ExecutableElement) enclosed;
                if (matches(method, name, parameters, returnType, modifierSet)) {
                    found.add(getMethod(method));
                }
            }
        }
        return found;
    }

    @Override
    public void importClass(IClassType type) {
        String clazz = type.getQualifiedName();
        JCTree.JCCompilationUnit tree = (JCTree.JCCompilationUnit) internals.getTrees().getPath(classElement).getCompilationUnit();

        tree.defs = ListUtils.prependObjectToSunList(tree.defs, internals.getTreeMaker().Import(
                internals.getTreeMaker().Select(
                        internals.getTreeMaker().Ident(internals.getNames().fromString(clazz.substring(0,
                                clazz.lastIndexOf(".")))),
                        internals.getNames().fromString(clazz.substring(clazz.lastIndexOf(".") + 1))), false));
    }

    private boolean matches(ExecutableElement element, @Nullable String name, @Nullable IParameterType[] parameters,
                            @Nullable IParameterType returnType, @Nullable Set<Modifier> modifiers) {
        if (name != null && !element.getSimpleName().contentEquals(name)) {
            return false;
        }
        if (parameters != null) {
            if (parameters.length != element.getParameters().size()) {
                return false;
            }
            for (int i = 0; i < parameters.length; i++) {
                VariableElement parameter = element.getParameters().get(i);
                IParameterType found = parameters[i];
                if (found != null && !((Symbol.VarSymbol) parameter).type.tsym.getQualifiedName()
                        .contentEquals(found.getType().getQualifiedName())) {
                    return false;
                }
            }
        }
        if (returnType != null &&
                (returnType.getType() == null ? !(element.getReturnType() instanceof Type.JCNoType ||
                        element.getReturnType() instanceof Type.JCVoidType) :
                        !((Type) element.getReturnType()).tsym.getQualifiedName()
                                .contentEquals(returnType.getType().getQualifiedName()))) {
            return false;
        }
        if (modifiers != null) {
            if (modifiers.size() != element.getModifiers().size()) {
                return false;
            }
            for (Modifier modifier : modifiers) {
                if (!element.getModifiers().contains(modifier)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void removeMethod(ExecutableElement element) {
        classDecl.defs = ListUtils
                .removeObjectFromSunList(classDecl.defs, (JCTree.JCMethodDecl) internals.getTrees().getTree(element));
    }

    @Override
    public IMethodNodeBuilder getMethodBuilder(String name) {
        return new MethodNodeBuilder(this, name);
    }

    @Override
    public IMethodNodeBuilder getMethodBuilder(IMethodNode node) {
        JCTree.JCMethodDecl decl = (JCTree.JCMethodDecl) internals.getTrees().getTree(node.getMethodElement());
        classDecl.defs = ListUtils.removeObjectFromSunList(classDecl.defs, decl);
        MethodNodeBuilder builder = new MethodNodeBuilder(this, node.getMethodElement().getSimpleName().toString(),
                decl.getModifiers().getAnnotations(), decl.getParameters(), decl.getTypeParameters(),
                castInternalList(decl.getThrows()), (JCTree.JCExpression) decl.getReturnType());
        return builder.setCodeBlock(new JavacCodeBlock(internals))
                .setModifier((int) decl.getModifiers().flags)
                .setDefaultValue(decl.getDefaultValue() == null ? null :
                        new LiteralExpression<>(((JCTree.JCLiteral) decl.getDefaultValue()).value));
    }

    @Override
    public IMethodNodeBuilder getConstructorBuilder() {
        return new MethodNodeBuilder(this);
    }

    public IMethodNode buildNodeBuilder(JCTree.JCMethodDecl methodDecl) {
        for (JCTree decl : classDecl.defs) {
            if (decl instanceof JCTree.JCMethodDecl && decl.equals(methodDecl)) {
                JCTree.JCMethodDecl method = (JCTree.JCMethodDecl) decl;
                method.body = methodDecl.getBody();
                return methodPool.getObject(method.sym);
            }
        }
        classDecl.defs = ListUtils.addObjectToSunList(classDecl.defs, methodDecl);
        return methodPool.getObject(methodDecl.sym);
    }
}
