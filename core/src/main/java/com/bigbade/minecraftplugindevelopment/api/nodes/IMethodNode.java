package com.bigbade.minecraftplugindevelopment.api.nodes;

import com.bigbade.minecraftplugindevelopment.api.nodes.builder.IMethodNodeBuilder;

import javax.lang.model.element.ExecutableElement;

/**
 * Represents a source method, used to easily manipulate values inside the method and
 * have changes reflected across processors.
 */
@SuppressWarnings("unused")
public interface IMethodNode {
    /**
     * The executable element of the method
     * @return Element of the method
     */
    ExecutableElement getMethodElement();

}
