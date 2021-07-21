package com.bigbade.processorcodeapi.api.code;

import java.util.List;

public interface IWildcard {
    /**
     * Gets the constraint type of the constraint
     * @return Constraint type
     */
    GenericConstraint getConstraint();

    /**
     * The constraints of the generic, mapped to the type of constraint.
     * @return The generic constraints and constraint type
     */
    List<IClassType> getClassBounds();
}
