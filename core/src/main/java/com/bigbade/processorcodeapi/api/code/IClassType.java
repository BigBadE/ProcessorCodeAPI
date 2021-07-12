package com.bigbade.processorcodeapi.api.code;

import com.bigbade.processorcodeapi.api.code.parameter.IParameterType;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * Represents a class type in memory. Used over qualified names or elements because class types can be
 * constructed by either, therefore giving more flexibility to the user.
 */
@SuppressWarnings("unused")
public interface IClassType {
    /**
     * Gets the qualified name
     * @return Qualified name of the class. Format is (package).(simple name)
     */
    String getQualifiedName();

    /**
     * Gets the simple name.
     * @return Simple name of the class. Examples are Object, String, and List
     */
    String getSimpleName();

    /**
     * Gets the processor TypeElement of the class type. Used to fetch internal data.
     * @return TypeElement of class type
     */
    TypeElement getElement();

    /**
     * Gets a specific method type
     * @param name Name of the method
     * @param returnType Return type of the method
     * @param modifiers Modifiers of the method
     * @param params Parameters of the method
     * @return Found method type
     * @see java.lang.reflect.Modifier
     */
    IMethodType getMethod(String name, IParameterType returnType, Integer modifiers, IParameterType... params);

    /**
     * Gets the method type from an ExecutableElement
     * @param element ExecutableElement to get the type from
     * @see ExecutableElement
     * @return Method type of the method
     */
    IMethodType getMethod(ExecutableElement element);
}
