package com.bigbade.minecraftplugindevelopment.javac.pools;

import com.bigbade.minecraftplugindevelopment.api.pools.MethodPool;
import com.bigbade.minecraftplugindevelopment.javac.nodes.MethodNode;
import com.bigbade.minecraftplugindevelopment.javac.utils.JavacInternals;
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
