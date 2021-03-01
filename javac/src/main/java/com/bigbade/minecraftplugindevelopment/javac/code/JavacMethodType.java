package com.bigbade.minecraftplugindevelopment.javac.code;

import com.bigbade.minecraftplugindevelopment.api.code.IClassType;
import com.bigbade.minecraftplugindevelopment.api.code.IMethodType;
import com.bigbade.minecraftplugindevelopment.api.code.parameter.IParameterType;
import com.bigbade.minecraftplugindevelopment.javac.factories.JavacNodeFactory;
import com.bigbade.minecraftplugindevelopment.javac.utils.JavacInternals;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.tree.JCTree;

import javax.annotation.Nullable;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public class JavacMethodType implements IMethodType, JavacVersion<JCTree.JCIdent> {
    private String methodName = null;
    private IClassType classType = null;
    private IParameterType[] params = new IParameterType[0];
    private IParameterType returnType = null;
    private ExecutableElement element = null;

    public JavacMethodType(IClassType classType, String methodName,
                           @Nullable IParameterType returnType, IParameterType... params) {
        this.returnType = returnType;
        this.methodName = methodName;
        this.classType = classType;
        if(params.length > 0) {
            this.params = params;
        }
    }

    public JavacMethodType(ExecutableElement element) {
        this.element = element;
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

                if (found) {
                    return Optional.of(method);
                }
            }
            return Optional.empty();
        }
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

    @Override
    public JCTree.JCIdent getExpression(JavacInternals internals) {
        return internals.getTreeMaker().Ident(internals.getNames().fromString(getMethodName()));
    }
}
