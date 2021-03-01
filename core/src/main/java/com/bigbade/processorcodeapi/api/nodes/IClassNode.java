package com.bigbade.processorcodeapi.api.nodes;

import com.bigbade.processorcodeapi.api.code.IClassType;
import com.bigbade.processorcodeapi.api.code.parameter.IParameterType;
import com.bigbade.processorcodeapi.api.factories.INodeFactory;
import com.bigbade.processorcodeapi.api.nodes.builder.IMethodNodeBuilder;

import javax.annotation.Nullable;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.util.List;

/**
 * Represents a source class, used to easily manipulate values inside the class and
 * have changes reflected across processors.
 */
@SuppressWarnings("unused")
public interface IClassNode {
    /**
     * The type element of the class
     * @return Element of the class
     */
    TypeElement getClassElement();

    /**
     * Attempts to get the method from the class given the element
     * @param element Element to get the method from
     * @return Method node of element
     */
    IMethodNode getMethod(ExecutableElement element);

    /**
     * Attempts to find method nodes given the data
     * @param name Name of method, null if any works
     * @param parameters Parameters of method, null array if any parameters work, null element in array if any
     *                   individual parameter works
     * @param returnType Return type of method, null if any return types work, void type for void
     * @param modifiers Modifiers, should be taken from Modifier class and XOR'd with each other.
     * @return List of method nodes which match the given criteria
     * @see INodeFactory#getVoidType()
     * @see java.lang.reflect.Modifier
     */
    List<IMethodNode> findMethods(@Nullable String name, @Nullable IParameterType[] parameters,
                                  @Nullable IParameterType returnType, @Nullable Integer modifiers);

    /**
     * Import the class type, does nothing if already imported.
     * @param type Type to import
     */
    void importClass(IClassType type);

    /**
     * Attempts to remove the method from the class given the element
     * @param element Element to remove
     */
    void removeMethod(ExecutableElement element);

    /**
     * Gets a method node builder for the class
     * @param name Name of the method
     * @return Builder for a method of that name
     */
    IMethodNodeBuilder getMethodBuilder(String name);

    /**
     * Constructs a new builder based off of the element
     * @param node Node to base the builder off of
     * @return New builder that can be built to an equal method node
     */
    IMethodNodeBuilder getMethodBuilder(IMethodNode node);

    /**
     * Gets a constructor method node builder for the class
     * @return New constructor builder
     */
    IMethodNodeBuilder getConstructorBuilder();
}
