package com.bigbade.processorcodeapi.eclipse;

import com.bigbade.processorcodeapi.api.TypeUtils;
import com.bigbade.processorcodeapi.api.code.IClassType;
import com.bigbade.processorcodeapi.api.code.ICodeBlock;
import com.bigbade.processorcodeapi.api.code.parameter.IParameterType;
import com.bigbade.processorcodeapi.api.factories.IExpressionFactory;
import com.bigbade.processorcodeapi.api.factories.INodeFactory;
import com.bigbade.processorcodeapi.api.factories.IStatementFactory;
import com.bigbade.processorcodeapi.api.nodes.IClassNode;
import com.bigbade.processorcodeapi.api.nodes.IMethodNode;
import org.eclipse.jdt.internal.compiler.apt.dispatch.BaseProcessingEnvImpl;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

//TODO this entire thing
public final class EclipseNodeFactory implements INodeFactory {
    public EclipseNodeFactory(ProcessingEnvironment environment) {
        throw new IllegalStateException("Eclipse's JDT system isn't supported yet. Please use Sun's javac instead.");
    }

    public static boolean isCorrectType(ProcessingEnvironment environment) {
        return environment instanceof BaseProcessingEnvImpl;
    }

    @Override
    public Messager getMessenger() {
        return null;
    }

    @Override
    public IClassNode getClassNode(TypeElement element) {
        return null;
    }

    @Override
    public IParameterType getVoidType() {
        return null;
    }

    @Override
    public IClassType getVariableType(VariableElement element) {
        return null;
    }

    @Override
    public IParameterType getParameterType(IClassType type) {
        return null;
    }

    @Override
    public IMethodNode getMethodNode(ExecutableElement element) {
        return null;
    }

    @Override
    public TypeUtils getTypeUtils() {
        return null;
    }

    @Override
    public ICodeBlock getCodeBlock() {
        return null;
    }

    @Override
    public IExpressionFactory getExpressionFactory() {
        return null;
    }

    @Override
    public IStatementFactory getStatementFactory() {
        return null;
    }

    @Override
    public IClassType getClassType(String qualifiedName) {
        return null;
    }

    @Override
    public IClassType getClassType(String qualifiedName, int dimensions) {
        return null;
    }

    @Override
    public IClassType getClassType(TypeElement element) {
        return null;
    }

    @Override
    public IClassType getClassType(TypeElement element, int dimensions) {
        return null;
    }
}
