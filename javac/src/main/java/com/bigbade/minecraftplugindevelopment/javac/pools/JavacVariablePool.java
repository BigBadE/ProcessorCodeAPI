package com.bigbade.minecraftplugindevelopment.javac.pools;

import com.bigbade.minecraftplugindevelopment.api.pools.VariablePool;
import com.bigbade.minecraftplugindevelopment.javac.nodes.VariableLeaf;
import com.bigbade.minecraftplugindevelopment.javac.utils.JavacInternals;
import lombok.RequiredArgsConstructor;

import javax.lang.model.element.VariableElement;

/**
 * A pool of all variables, used to allow changes be reflected across processors
 */
@RequiredArgsConstructor
public class JavacVariablePool extends VariablePool<VariableLeaf> {
    private final JavacInternals internals;

    /**
     * @param key Key used to instantiate the value
     * @return VariableLeaf internal leaf created from the element
     */
    @Override
    protected VariableLeaf instantiate(VariableElement key) {
        return new VariableLeaf(key, internals);
    }
}
