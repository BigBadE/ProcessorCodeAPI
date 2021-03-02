package com.bigbade.processorcodeapi.api.code.parameter;

/**
 * Represents a basic parameter. Most of the complexities of parameters are in the IParameterType class
 * @see IParameterType
 */
public interface IParameter {
    IParameterType getType();

    String getName();
}
