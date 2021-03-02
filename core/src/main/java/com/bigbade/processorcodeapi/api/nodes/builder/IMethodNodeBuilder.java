package com.bigbade.processorcodeapi.api.nodes.builder;

import com.bigbade.processorcodeapi.api.code.IAnnotation;
import com.bigbade.processorcodeapi.api.code.IClassType;
import com.bigbade.processorcodeapi.api.code.ICodeBlock;
import com.bigbade.processorcodeapi.api.code.parameter.IParameter;
import com.bigbade.processorcodeapi.api.code.parameter.IParameterType;
import com.bigbade.processorcodeapi.api.expressions.ILiteralExpression;
import com.bigbade.processorcodeapi.api.nodes.IMethodNode;

/**
 * A builder for creating/editing a method node, must be built for changes to be reflected in the
 * compiled code.
 */
@SuppressWarnings("unused")
public interface IMethodNodeBuilder {
    /**
     * Sets the modifiers of the method.
     * @param modifier Modifier to use. Should use the Modifiers class from Java's reflect package, and XOR ( | )
     *                 together any modifiers you want to combine
     * @return Current builder
     * @see java.lang.reflect.Modifier
     */
    IMethodNodeBuilder setModifier(int modifier);

    /**
     * Adds an annotation to the method
     * @param annotation Annotation to add
     * @return Current builder
     */
    IMethodNodeBuilder addAnnotation(IAnnotation annotation);

    /**
     * Removes an annotation to the method
     * @param annotation Type of annotation to remove
     * @return Current builder
     */
    IMethodNodeBuilder removeAnnotation(IClassType annotation);

    /**
     * Sets the type returned from the method. Default is void
     * @param returned Returned type
     * @return Current builder
     */
    IMethodNodeBuilder setReturnType(IClassType returned);

    /**
     * Adds the given exception to be thrown by the method
     * @param exception Type of exception
     * @return Current builder
     */
    IMethodNodeBuilder addException(IClassType exception);

    /**
     * Remove exception from the method
     * @param type Exception type
     * @return Current builder
     */
    IMethodNodeBuilder removeException(IClassType type);

    /**
     * Adds the parameter to the method
     * @param parameter Parameter to add
     * @return Current builder
     */
    IMethodNodeBuilder addParameter(IParameter parameter);


    /**
     * Remove parameter from the method
     * @param type Type of param to remove
     * @param name Name of the param
     * @return Current builder
     */
    IMethodNodeBuilder removeParameter(IParameterType type, String name);

    /**
     * Sets the default value of the method. Only applicable for methods in annotations
     * @param value Default value
     * @return Current builder
     */
    IMethodNodeBuilder setDefaultValue(ILiteralExpression<?> value);

    /**
     * Sets the code block of the method
     * @param block Code block to set code to
     * @return Current builder
     */
    IMethodNodeBuilder setCodeBlock(ICodeBlock block);

    /**
     * Gets the code block of the method
     * @return Code block
     */
    ICodeBlock getCodeBlock();

    /**
     * Builds the method, replacing any exact dulicates in the parent method
     * @return New node
     */
    IMethodNode build();
}
