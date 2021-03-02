package com.bigbade.processorcodeapi.api.code;

import java.util.List;

/**
 * A representation of a generic, one bound to a class or method (But not a variable!)
 */
@SuppressWarnings("unused")
public interface IGeneric {
    String letter();

    List<IClassType> boundClasses();
}
