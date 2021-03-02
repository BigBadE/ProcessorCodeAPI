package com.bigbade.processorcodeapi.api.code;

import javax.lang.model.element.ExecutableElement;
import java.util.Optional;

/**
 * Represents a method. Allows the "searching" of a specific expression without directly operating on
 * a TypeElement to find the desired method.
 *
 * @see ExecutableElement
 */
public interface IMethodType {
    /**
     * Gets the name of the method
     * @return Simple name of the method, like this one would be "getMethodName"
     */
    String getMethodName();

    /**
     * Attempts to find the element from the type.
     * SHOULD NOT BE USED ON NON-LIBRARY CODE. ALL CODE BEING COMPILED SHOULD BE
     * ACCESSED USING METHOD NODES, OR CHANGES TO THE CLASS WILL NOT BE REFLECTED!
     * @see ExecutableElement
     * @see com.bigbade.processorcodeapi.api.nodes.IMethodNode
     * @return Optional containing the element if it could be found
     */
    Optional<ExecutableElement> getElement();
}
