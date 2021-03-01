package com.bigbade.minecraftplugindevelopment.api.code.parameter;

import javax.lang.model.element.Element;

public interface IParameter {
    IParameterType getType();

    String getName();
}
