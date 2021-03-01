package com.bigbade.minecraftplugindevelopment.javac.code;

import com.bigbade.minecraftplugindevelopment.api.code.IClassType;
import com.bigbade.minecraftplugindevelopment.api.code.IMethodType;
import com.bigbade.minecraftplugindevelopment.api.code.parameter.IParameterType;
import com.bigbade.minecraftplugindevelopment.javac.utils.JavacInternals;
import com.sun.tools.javac.tree.JCTree;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.util.regex.Pattern;

public class JavacClassType implements IClassType, JavacVersion<JCTree.JCIdent> {
    private static final Pattern PACKAGE_PATTERN = Pattern.compile("\\.");

    private final JavacInternals internals;
    private String qualifiedName;
    private TypeElement element;

    public JavacClassType(JavacInternals internals, String qualifiedName) {
        this.qualifiedName = qualifiedName;
        this.internals = internals;
    }

    public JavacClassType(JavacInternals internals, TypeElement element) {
        this.element = element;
        this.internals = internals;
    }

    @Override
    public String getQualifiedName() {
        if(qualifiedName != null) {
            return qualifiedName;
        } else {
            return element.getQualifiedName().toString();
        }
    }

    @Override
    public String getSimpleName() {
        if(qualifiedName != null) {
            String[] split = PACKAGE_PATTERN.split(qualifiedName);
            return split[split.length-1];
        } else {
            return element.getSimpleName().toString();
        }
    }

    @Override
    public TypeElement getElement() {
        if(element != null) {
            return element;
        } else {
            TypeElement foundElement = internals.getElements().getTypeElement(qualifiedName);
            if(foundElement == null) {
                throw new IllegalArgumentException("Passed type with no element!");
            }
            return foundElement;
        }
    }

    @Override
    public IMethodType getMethod(String name, IParameterType returnType, IParameterType... params) {
        return new JavacMethodType(this, name, returnType, params);
    }

    @Override
    public IMethodType getMethod(ExecutableElement element) {
        return new JavacMethodType(element);
    }

    @Override
    public JCTree.JCIdent getExpression(JavacInternals internals) {
        return internals.getTreeMaker().Ident(internals.getNames().fromString(getSimpleName()));
    }
}
