package com.bigbade.processorcodeapi.api.code.parameter;

import com.bigbade.processorcodeapi.api.code.IClassType;

import java.util.List;

@SuppressWarnings("unused")
public interface IGeneric {
    String letter();

    List<IClassType> boundClasses();
}
