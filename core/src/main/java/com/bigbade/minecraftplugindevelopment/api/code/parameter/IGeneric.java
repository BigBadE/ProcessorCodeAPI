package com.bigbade.minecraftplugindevelopment.api.code.parameter;

import com.bigbade.minecraftplugindevelopment.api.code.IClassType;

import java.util.List;

@SuppressWarnings("unused")
public interface IGeneric {
    String letter();

    List<IClassType> boundClasses();
}
