package com.bigbade.processorcodeapi.api.code;

import java.util.List;

/**
 * A representation of a generic, one bound to a class or method (But not a variable!)
 */
@SuppressWarnings("unused")
public interface IGeneric {
    /**
     * The letter of the generic.
     * Technically whole words are allowed (hence the String type) but called letter
     * due to the standard being generics are represented by a single capital letter.
     * @return Letter of the generic.
     */
    String getLetter();

    /**
     * The constraints of the generic, mapped to the type of constraint.
     * @return The generic constraints and constraint type
     */
    List<IClassType> getClassBounds();
}
