package com.bigbade.minecraftplugindevelopment.api.factories;

import com.bigbade.minecraftplugindevelopment.api.TypeUtils;
import com.bigbade.minecraftplugindevelopment.api.code.IClassType;
import com.bigbade.minecraftplugindevelopment.api.code.ICodeBlock;
import com.bigbade.minecraftplugindevelopment.api.code.parameter.IParameterType;
import com.bigbade.minecraftplugindevelopment.api.nodes.IClassNode;
import com.bigbade.minecraftplugindevelopment.api.nodes.IMethodNode;

import javax.annotation.processing.Messager;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

@SuppressWarnings("unused")
public interface INodeFactory {
    /**
     * Gets the processor messenger from the factory
     * @return Messenger for the processor.
     */
    Messager getMessenger();

    /**
     * Retrieves the class node from the element
     * @param element Element to get the class from
     * @return Class node of the element
     */
    IClassNode getClassNode(TypeElement element);

    /**
     * Returns a void type
     * @return Void type
     */
    IParameterType getVoidType();

    /**
     * Gets the type of the variable
     * @param element Variable
     * @return Type of variable
     */
    IClassType getVariableType(VariableElement element);

    /**
     * Gets a parameter type
     * @return Parameter type
     */
    IParameterType getParameterType(IClassType type);

    /**
     * Retrieves the method node from the element
     * @param element Element to get the method from
     * @return Method node of the element
     */
    IMethodNode getMethodNode(ExecutableElement element);

    TypeUtils getTypeUtils();

    /**
     * Creates a new code block
     * @return Newly created code block
     */
    ICodeBlock getCodeBlock();

    /**
     * Returns a factory to create code blocks
     * @return Code factory
     */
    ICodeFactory getCodeFactory();

    /**
     * Returns a class type from the name
     * @return Class type of given qualified name
     */
    IClassType getClassType(String qualifiedName);

    /**
     * Returns a class type from the element
     * @return Class type of given element
     */
    IClassType getClassType(TypeElement element);
}
