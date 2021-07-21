package com.bigbade.processorcodeapi.api.factories;

import com.bigbade.processorcodeapi.api.TypeUtils;
import com.bigbade.processorcodeapi.api.code.IClassType;
import com.bigbade.processorcodeapi.api.code.ICodeBlock;
import com.bigbade.processorcodeapi.api.code.parameter.IParameterType;
import com.bigbade.processorcodeapi.api.nodes.IClassNode;
import com.bigbade.processorcodeapi.api.nodes.IMethodNode;

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
     * @param type Type of parameter
     * @return Parameter type
     */
    IParameterType getParameterType(IClassType type);

    /**
     * Retrieves the method node from the element
     * @param element Element to get the method from
     * @return Method node of the element
     */
    IMethodNode getMethodNode(ExecutableElement element);

    /**
     * Gets the TypeUtils instance
     * @return Current TypeUtils instance
     */
    TypeUtils getTypeUtils();

    /**
     * Creates a new code block
     * @return Newly created code block
     */
    ICodeBlock getCodeBlock();

    /**
     * Returns a factory to create instructions to be passed to statements
     * @return Expression factory
     */
    IExpressionFactory getExpressionFactory();

    /**
     * Returns a factory to create instructions for code blocks
     * @return Statement factory
     */
    IStatementFactory getStatementFactory();

    /**
     * Returns a class type from the name
     * @param qualifiedName Qualified name of class
     * @return Class type of given qualified name
     */
    IClassType getClassType(String qualifiedName);

    /**
     * Returns a class type from the name with the given dimensions
     * @param qualifiedName Qualified name of class
     * @param dimensions Array dimensions. Default is 0
     * @return Class type of given qualified name
     */
    IClassType getClassType(String qualifiedName, int dimensions);

    /**
     * Returns a class type from the element
     * @param element Element to get type of
     * @return Class type of given element
     */
    IClassType getClassType(TypeElement element);

    /**
     * Returns a class type from the element
     * @param element Element to get type of
     * @param dimensions Array dimensions. Default is 0
     * @return Class type of given element
     */
    IClassType getClassType(TypeElement element, int dimensions);
}
