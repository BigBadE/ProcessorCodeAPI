package com.bigbade.processorcodeapi.javac.code;

import com.bigbade.processorcodeapi.api.code.IClassType;
import com.bigbade.processorcodeapi.api.code.IMethodType;
import com.bigbade.processorcodeapi.api.code.parameter.IParameterType;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.sun.tools.javac.tree.JCTree;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.util.regex.Pattern;

public class JavacClassType implements IClassType, JavacVersion<JCTree.JCExpression> {
    private static final Pattern PACKAGE_PATTERN = Pattern.compile("\\.");

    private final JavacInternals internals;
    private final int dimensions;
    private String qualifiedName;
    private TypeElement element;

    public JavacClassType(JavacInternals internals, String qualifiedName, int dimensions) {
        this.qualifiedName = qualifiedName;
        this.internals = internals;
        this.dimensions = dimensions;
    }

    public JavacClassType(JavacInternals internals, TypeElement element, int dimensions) {
        this.element = element;
        this.internals = internals;
        this.dimensions = dimensions;
    }

    public JavacClassType(JavacInternals internals, JCTree.JCExpression expression) {
        JCTree.JCExpression found = expression;
        int foundDimensions = 0;
        while (found instanceof JCTree.JCArrayTypeTree) {
            foundDimensions++;
            found = ((JCTree.JCArrayTypeTree)expression).elemtype;
        }
        this.qualifiedName = ((JCTree.JCIdent) found).name.toString();
        this.internals = internals;
        this.dimensions = foundDimensions;
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
    public IMethodType getMethod(String name, IParameterType returnType, Integer modifiers,
                                 IParameterType... params) {
        return new JavacMethodType(this, name, returnType, modifiers, params);
    }

    @Override
    public IMethodType getMethod(ExecutableElement element) {
        return new JavacMethodType(element);
    }

    @Override
    public JCTree.JCExpression getExpression(JavacInternals internals) {
        JCTree.JCExpression expression = internals.getTreeMaker().Ident(internals.getNames().fromString(getQualifiedName()));
        int currentDimension = dimensions;
        while (currentDimension-- > 0) {
            expression = internals.getTreeMaker().TypeArray(expression);
        }
        return expression;
    }
}
