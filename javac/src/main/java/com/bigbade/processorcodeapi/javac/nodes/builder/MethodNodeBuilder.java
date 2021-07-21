package com.bigbade.processorcodeapi.javac.nodes.builder;

import com.bigbade.processorcodeapi.api.code.IAnnotation;
import com.bigbade.processorcodeapi.api.code.IClassType;
import com.bigbade.processorcodeapi.api.code.ICodeBlock;
import com.bigbade.processorcodeapi.api.code.IGeneric;
import com.bigbade.processorcodeapi.api.code.parameter.IParameter;
import com.bigbade.processorcodeapi.api.code.parameter.IParameterType;
import com.bigbade.processorcodeapi.api.expressions.IBasicExpression;
import com.bigbade.processorcodeapi.api.expressions.ILiteralExpression;
import com.bigbade.processorcodeapi.api.nodes.IMethodNode;
import com.bigbade.processorcodeapi.api.nodes.builder.IMethodNodeBuilder;
import com.bigbade.processorcodeapi.javac.code.JavacCodeBlock;
import com.bigbade.processorcodeapi.javac.code.JavacVersion;
import com.bigbade.processorcodeapi.javac.expressions.ExpressionReference;
import com.bigbade.processorcodeapi.javac.expressions.LiteralExpression;
import com.bigbade.processorcodeapi.javac.expressions.SelectVariableExpression;
import com.bigbade.processorcodeapi.javac.nodes.ClassNode;
import com.bigbade.processorcodeapi.javac.statements.CallStatement;
import com.bigbade.processorcodeapi.javac.utils.ListUtils;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import lombok.Getter;

import javax.tools.Diagnostic;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
public class MethodNodeBuilder implements IMethodNodeBuilder {
    private final ClassNode node;
    private final String name;

    private final List<JCTree.JCAnnotation> annotations;
    private final List<JCTree.JCVariableDecl> typeDecls;
    private final List<JCTree.JCTypeParameter> genericTypes;
    private final List<JCTree.JCIdent> thrown;

    private int modifier = Modifier.PUBLIC;
    private LiteralExpression<?> defaultValue = null;
    private JCTree.JCExpression returnType = null;
    @Getter
    private JavacCodeBlock codeBlock;

    /**
     * Creates a constructor method for the node
     *
     * @param node Class to create a constructor for
     * @param superArgs Arguments for super statement
     */
    public MethodNodeBuilder(ClassNode node, IBasicExpression... superArgs) {
        this.node = node;
        name = "<init>";
        codeBlock = new JavacCodeBlock(node.getInternals());
        codeBlock.addTopStatement(new CallStatement(new ExpressionReference(null,
                new SelectVariableExpression("super"), superArgs)));
        annotations = new ArrayList<>();
        typeDecls = new ArrayList<>();
        genericTypes = new ArrayList<>();
        thrown = new ArrayList<>();
    }

    /**
     * Creates a named method for the node
     *
     * @param node Class to create a constructor for
     * @param name Name of method
     */
    public MethodNodeBuilder(ClassNode node, String name) {
        this.node = node;
        this.name = name;
        codeBlock = new JavacCodeBlock(node.getInternals());
        annotations = new ArrayList<>();
        typeDecls = new ArrayList<>();
        genericTypes = new ArrayList<>();
        thrown = new ArrayList<>();
    }

    /**
     * Creates a named method for the node
     *
     * @param node Class to create a constructor for
     * @param name Name of method
     * @param annotations Annotations of method
     * @param typeDecls Parameters of method
     * @param genericTypes Generic types of method
     * @param thrown Exceptions thrown by the method
     * @param returnType Return type of method
     */
    public MethodNodeBuilder(ClassNode node, String name, List<JCTree.JCAnnotation> annotations,
                             List<JCTree.JCVariableDecl> typeDecls, List<JCTree.JCTypeParameter> genericTypes,
                             List<JCTree.JCIdent> thrown, JCTree.JCExpression returnType) {
        this.node = node;
        this.name = name;
        this.annotations = annotations;
        this.typeDecls = typeDecls;
        this.genericTypes = genericTypes;
        this.thrown = thrown;
        this.returnType = returnType;
    }

    public IMethodNodeBuilder setModifier(int modifier) {
        this.modifier = modifier;
        return this;
    }

    public IMethodNodeBuilder setReturnType(IClassType type) {
        returnType = node.getInternals().getTreeMaker().Ident(
                node.getInternals().getNames().fromString(type.getQualifiedName()));
        return this;
    }

    public IMethodNodeBuilder setCodeBlock(ICodeBlock codeBlock) {
        this.codeBlock = (JavacCodeBlock) codeBlock;
        return this;
    }

    public IMethodNodeBuilder setDefaultValue(LiteralExpression<?> value) {
        this.defaultValue = value;
        return this;
    }

    @Override
    public IMethodNodeBuilder addException(IClassType exception) {
        thrown.add(node.getInternals().getTreeMaker().Ident(
                node.getInternals().getNames().fromString(exception.getQualifiedName())));
        return this;
    }

    @Override
    public IMethodNodeBuilder removeException(IClassType type) {
        for (int i = 0; i < thrown.size(); i++) {
            if (thrown.get(i).getName().contentEquals(type.getQualifiedName())) {
                thrown.remove(i--);
            }
        }
        return this;
    }

    @SuppressWarnings("unchecked")
    public final IMethodNodeBuilder addAnnotation(IAnnotation annotation) {
        annotations.add(((JavacVersion<JCTree.JCAnnotation>) annotation).getExpression(node.getInternals()));
        return this;
    }

    @Override
    public IMethodNodeBuilder removeAnnotation(IClassType annotation) {
        for (int i = 0; i < annotations.size(); i++) {
            if (((JCTree.JCIdent) annotations.get(i).getAnnotationType()).getName()
                    .contentEquals(annotation.getQualifiedName())) {
                annotations.remove(i);
                return this;
            }
        }
        return this;
    }

    @SuppressWarnings("unchecked")
    public IMethodNodeBuilder addGeneric(IGeneric genericType) {
        genericTypes.add(((JavacVersion<JCTree.JCTypeParameter>) genericType).getExpression(node.getInternals()));
        return this;
    }

    @SuppressWarnings("unchecked")
    public IMethodNodeBuilder addParameter(IParameter parameter) {
        typeDecls.add(((JavacVersion<JCTree.JCVariableDecl>) parameter).getExpression(node.getInternals()));
        return this;
    }


    @Override
    public IMethodNodeBuilder removeParameter(IParameterType type, String name) {
        for (int i = 0; i < typeDecls.size(); i++) {
            if (typeDecls.get(i).getName().contentEquals(name)
                    && typeDecls.get(i).type.tsym.getQualifiedName().contentEquals(type.getType().getQualifiedName())) {
                typeDecls.remove(i);
                return this;
            }
        }

        return this;
    }

    @Override
    public IMethodNodeBuilder setDefaultValue(ILiteralExpression<?> value) {
        defaultValue = (LiteralExpression<?>) value;
        return this;
    }

    public IMethodNode build() {
        TreeMaker treeMaker = node.getInternals().getTreeMaker();

        List<String> names = new ArrayList<>();
        for (JCTree.JCIdent exception : thrown) {
            names.add(exception.name.toString());
        }
        for (int i = 1; i < thrown.size(); i++) {
            if (names.indexOf(names.get(i)) < i) {
                thrown.remove(i--);
                node.getInternals().getMessager().printMessage(Diagnostic.Kind.WARNING, "Duplicate thrown error " +
                        "detected, merged into one!");
            }
        }
        for (JCTree.JCVariableDecl parameter : typeDecls) {
            if (parameter.getName().contentEquals(name)) {
                node.getInternals().getMessager().printMessage(Diagnostic.Kind.ERROR, "Duplicate parameter added " +
                        "named " + name + "!");
            }
        }

        JCTree.JCMethodDecl methodDecl = treeMaker.MethodDef(getModifiers(treeMaker),
                node.getInternals().getNames().fromString(name),
                returnType, ListUtils.convertList(genericTypes), ListUtils.convertList(typeDecls),
                ListUtils.convertList(new ArrayList<>(thrown)), codeBlock.getExpression(node.getInternals()),
                defaultValue == null ? null : defaultValue.getExpression(node.getInternals()));
        methodDecl.sym = new Symbol.MethodSymbol(modifier, node.getInternals().getNames().fromString(name), null,
                (Symbol.ClassSymbol) node.getClassElement());
        //The flags are confusing, but I can't find any method that compiles to anything else (private, static,
        //native, and synchronized)
        methodDecl.type = new Type.MethodType(ListUtils.getTypes(typeDecls), returnType == null ? null : returnType.type,
                ListUtils.getTypes(thrown), new Symbol.ClassSymbol(Flags.ACYCLIC | Flags.PUBLIC,
                node.getInternals().getNames().fromString("Method"), null, methodDecl.sym));
        methodDecl.type.tsym.type = methodDecl.type;
        if (!genericTypes.isEmpty()) {
            methodDecl.type = new Type.ForAll(ListUtils.getTypes(genericTypes), methodDecl.type);
        }
        return node.buildNodeBuilder(methodDecl);
    }

    private JCTree.JCModifiers getModifiers(TreeMaker treeMaker) {
        Set<String> currentAnnotations = new HashSet<>();
        for (int i = 0; i < annotations.size(); i++) {
            JCTree.JCAnnotation annotation = annotations.get(i);
            String annotationName = ((JCTree.JCIdent) annotation.getAnnotationType()).name.toString();
            if (currentAnnotations.contains(annotationName)) {
                node.getInternals().getMessager().printMessage(Diagnostic.Kind.ERROR, "Duplicate annotation: "
                        + annotationName);
                annotations.remove(i--);
                continue;
            }
            currentAnnotations.add(annotationName);
        }
        return treeMaker.Modifiers(modifier, ListUtils.convertList(annotations));
    }
}
