package com.bigbade.processorcodeapi.javac.code;

import com.bigbade.processorcodeapi.api.code.IClassType;
import com.bigbade.processorcodeapi.api.code.IMethodType;
import com.bigbade.processorcodeapi.api.code.parameter.IParameterType;
import com.bigbade.processorcodeapi.javac.factories.JavacNodeFactory;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.tree.JCTree;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("unused")
public class JavacMethodType implements IMethodType, JavacVersion<JCTree.JCIdent> {
    private static final Map<Modifier, Integer> MODIFIER_FLAGS = new EnumMap<>(Modifier.class);

    static {
        MODIFIER_FLAGS.put(Modifier.PUBLIC, java.lang.reflect.Modifier.PUBLIC);
        MODIFIER_FLAGS.put(Modifier.PROTECTED, java.lang.reflect.Modifier.PROTECTED);
        MODIFIER_FLAGS.put(Modifier.PRIVATE, java.lang.reflect.Modifier.PRIVATE);
        MODIFIER_FLAGS.put(Modifier.ABSTRACT, java.lang.reflect.Modifier.ABSTRACT);
        MODIFIER_FLAGS.put(Modifier.STATIC, java.lang.reflect.Modifier.STATIC);
        MODIFIER_FLAGS.put(Modifier.FINAL, java.lang.reflect.Modifier.FINAL);
        MODIFIER_FLAGS.put(Modifier.TRANSIENT, java.lang.reflect.Modifier.TRANSIENT);
        MODIFIER_FLAGS.put(Modifier.VOLATILE, java.lang.reflect.Modifier.VOLATILE);
        MODIFIER_FLAGS.put(Modifier.SYNCHRONIZED, java.lang.reflect.Modifier.SYNCHRONIZED);
        MODIFIER_FLAGS.put(Modifier.NATIVE, java.lang.reflect.Modifier.NATIVE);
    }

    private String methodName = null;
    private IClassType classType = null;
    private IParameterType[] params = new IParameterType[0];
    private IParameterType returnType = null;
    private Integer modifiers = null;
    private ExecutableElement element = null;

    public JavacMethodType(IClassType classType, String methodName,
                           IParameterType returnType, Integer modifiers, IParameterType... params) {
        this.returnType = returnType;
        this.methodName = methodName;
        this.classType = classType;
        this.modifiers = modifiers;
        this.params = params;
    }

    public JavacMethodType(ExecutableElement element) {
        this.element = element;
    }

    public static List<Element> getSuperclasses(Element element) {
        List<Element> elements = new ArrayList<>();
        Type superClass = (Type) element.asType();
        while (superClass.asElement() != null) {
            elements.add(superClass.asElement());
            superClass = (Type) ((TypeElement) superClass.asElement()).getSuperclass();
        }
        return elements;
    }

    public String getMethodName() {
        if (methodName != null) {
            return methodName;
        } else {
            return element.getSimpleName().toString();
        }
    }

    //TODO reduce cognitive complexity
    @Override
    public Optional<ExecutableElement> getElement() {
        if (element != null) {
            return Optional.of(element);
        } else {
            for (Element enclosed : classType.getElement().getEnclosedElements()) {
                if (!(enclosed instanceof ExecutableElement)) {
                    continue;
                }
                ExecutableElement method = (ExecutableElement) enclosed;
                boolean found = returnType.typeMatches(method.getReturnType());

                if (!found || method.getParameters().size() != params.length) {
                    continue;
                }

                int i = 0;
                for (VariableElement param : method.getParameters()) {
                    if (!params[i].getType().getQualifiedName()
                            .equals(JavacNodeFactory.getVariableQualifiedName(param))) {
                        found = false;
                        break;
                    }
                    i++;
                }

                int foundModifiers = 0;
                for (Modifier modifier : method.getModifiers()) {
                    foundModifiers ^= MODIFIER_FLAGS.get(modifier);
                }

                if (found && foundModifiers == this.modifiers) {
                    this.element = method;
                    return Optional.of(method);
                }
            }
            return Optional.empty();
        }
    }

    @Override
    public JCTree.JCIdent getExpression(JavacInternals internals) {
        return internals.getTreeMaker().Ident(internals.getNames().fromString(getMethodName()));
    }
}
