package com.bigbade.processorcodeapi.javac.factories;

import com.bigbade.processorcodeapi.api.TypeUtils;
import com.bigbade.processorcodeapi.api.code.IClassType;
import com.bigbade.processorcodeapi.api.code.ICodeBlock;
import com.bigbade.processorcodeapi.api.code.parameter.IParameterType;
import com.bigbade.processorcodeapi.api.factories.ICodeFactory;
import com.bigbade.processorcodeapi.api.factories.INodeFactory;
import com.bigbade.processorcodeapi.api.nodes.IClassNode;
import com.bigbade.processorcodeapi.api.nodes.IMethodNode;
import com.bigbade.processorcodeapi.javac.code.JavacClassType;
import com.bigbade.processorcodeapi.javac.code.JavacCodeBlock;
import com.bigbade.processorcodeapi.javac.code.parameter.JavacParameterType;
import com.bigbade.processorcodeapi.javac.pools.JavacClassPool;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.bigbade.processorcodeapi.javac.utils.JavacTypeUtils;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import lombok.Getter;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

public final class JavacNodeFactory implements INodeFactory {
    private final TypeUtils typeUtils = new JavacTypeUtils();
    private final JavacClassPool classPool;

    @Getter
    private final JavacInternals internals;

    @Getter
    private final IParameterType voidType;

    public static boolean isCorrectType(ProcessingEnvironment environment) {
        return environment instanceof JavacProcessingEnvironment;
    }

    public JavacNodeFactory(ProcessingEnvironment environment) {
        internals = new JavacInternals(environment);
        classPool = new JavacClassPool(internals);
        voidType = new JavacParameterType(null);
    }

    public IClassNode getClassNode(TypeElement element) {
        return classPool.getObject(element);
    }

    @Override
    public IClassType getVariableType(VariableElement element) {
        return getClassType(getVariableQualifiedName(element));
    }

    public static String getVariableQualifiedName(VariableElement element) {
        return ((Symbol.VarSymbol) element).type.tsym.getQualifiedName().toString();
    }

    @Override
    public IParameterType getParameterType(IClassType type) {
        return new JavacParameterType(type);
    }

    @Override
    public IMethodNode getMethodNode(ExecutableElement element) {
        return getClassNode((TypeElement) element.getEnclosingElement()).getMethod(element);
    }

    @Override
    public TypeUtils getTypeUtils() {
        return typeUtils;
    }


    @Override
    public ICodeBlock getCodeBlock() {
        return new JavacCodeBlock(internals);
    }

    @Override
    public ICodeFactory getCodeFactory() {
        return new CodeFactory();
    }

    @Override
    public IClassType getClassType(String qualifiedName) {
        return new JavacClassType(internals, qualifiedName);
    }

    @Override
    public IClassType getClassType(TypeElement element) {
        return new JavacClassType(internals, element);
    }

    @Override
    public Messager getMessenger() {
        return internals.getMessager();
    }
}
