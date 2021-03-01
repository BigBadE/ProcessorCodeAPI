package com.bigbade.processorcodeapi.api.code;

import com.bigbade.processorcodeapi.api.code.parameter.IParameterType;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

@SuppressWarnings("unused")
public interface IClassType {
    String getQualifiedName();

    String getSimpleName();

    TypeElement getElement();

    IMethodType getMethod(String name, IParameterType returnType, IParameterType... params);

    IMethodType getMethod(ExecutableElement element);
}
