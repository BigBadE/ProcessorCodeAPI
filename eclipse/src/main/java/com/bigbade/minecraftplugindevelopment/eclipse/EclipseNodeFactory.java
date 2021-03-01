package com.bigbade.minecraftplugindevelopment.eclipse;

import com.bigbade.minecraftplugindevelopment.api.TypeUtils;
import com.bigbade.minecraftplugindevelopment.api.code.IClassType;
import com.bigbade.minecraftplugindevelopment.api.code.ICodeBlock;
import com.bigbade.minecraftplugindevelopment.api.code.parameter.IParameterType;
import com.bigbade.minecraftplugindevelopment.api.factories.ICodeFactory;
import com.bigbade.minecraftplugindevelopment.api.factories.INodeFactory;
import com.bigbade.minecraftplugindevelopment.api.nodes.IClassNode;
import com.bigbade.minecraftplugindevelopment.api.nodes.IMethodNode;
import org.eclipse.jdt.internal.compiler.apt.dispatch.BaseProcessingEnvImpl;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

public final class EclipseNodeFactory implements INodeFactory {
    public EclipseNodeFactory(ProcessingEnvironment environment) {

    }

    public static boolean isCorrectType(ProcessingEnvironment environment) {
        return environment instanceof BaseProcessingEnvImpl;
    }

    @Override
    public Messager getMessenger() {
        //TODO
        return null;
    }

    @Override
    public IClassNode getClassNode(TypeElement element) {
        //TODO
        return null;
    }

    @Override
    public IParameterType getVoidType() {
        //TODO
        return null;
    }

    @Override
    public IClassType getVariableType(VariableElement element) {
        //TODO
        return null;
    }

    @Override
    public IParameterType getParameterType(IClassType type) {
        //TODO
        return null;
    }

    @Override
    public IMethodNode getMethodNode(ExecutableElement element) {
        //TODO
        return null;
    }

    @Override
    public TypeUtils getTypeUtils() {
        //TODO
        return null;
    }

    @Override
    public ICodeBlock getCodeBlock() {
        //TODO
        return null;
    }

    @Override
    public ICodeFactory getCodeFactory() {
        //TODO
        return null;
    }

    @Override
    public IClassType getClassType(String qualifiedName) {
        //TODO
        return null;
    }

    @Override
    public IClassType getClassType(TypeElement element) {
        //TODO
        return null;
    }
}
