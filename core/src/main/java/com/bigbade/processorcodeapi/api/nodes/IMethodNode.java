package com.bigbade.processorcodeapi.api.nodes;

import com.bigbade.processorcodeapi.api.code.ICodeBlock;

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

    /**
     * The code block of the method
     * @return Code block
     */
    ICodeBlock getCodeBlock();
}
