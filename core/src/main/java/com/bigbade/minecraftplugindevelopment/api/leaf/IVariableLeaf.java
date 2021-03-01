package com.bigbade.minecraftplugindevelopment.api.leaf;

import javax.lang.model.element.VariableElement;

/**
 * Represents a source method, used to easily manipulate values inside the variable and
 * have changes reflected across processors.
 */
@SuppressWarnings("unused")
public interface IVariableLeaf {
    /**
     * The executable element of the variable
     * @return Element of the variable
     */
    VariableElement getVariableElement();
}
