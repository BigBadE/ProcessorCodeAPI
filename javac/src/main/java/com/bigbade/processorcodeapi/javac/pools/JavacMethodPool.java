package com.bigbade.processorcodeapi.javac.pools;

import com.bigbade.processorcodeapi.api.pools.MethodPool;
import com.bigbade.processorcodeapi.javac.nodes.MethodNode;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import lombok.RequiredArgsConstructor;

import javax.lang.model.element.ExecutableElement;

/**
 * A pool of all methods, used to allow changes be reflected across processors
 */
@RequiredArgsConstructor
public class JavacMethodPool extends MethodPool<MethodNode> {
    private final JavacInternals internals;

    /**
     * @param key Key used to instantiate the value
     * @return MethodNode internal node created from the element
     */
    @Override
    protected MethodNode instantiate(ExecutableElement key) {
        return new MethodNode(key, internals);
    }
}
