package com.bigbade.minecraftplugindevelopment.javac.pools;

import com.bigbade.minecraftplugindevelopment.api.pools.ClassPool;
import com.bigbade.minecraftplugindevelopment.javac.nodes.ClassNode;
import com.bigbade.minecraftplugindevelopment.javac.utils.JavacInternals;
import lombok.RequiredArgsConstructor;

import javax.lang.model.element.TypeElement;

/**
 * A pool of all classes, used to allow changes be reflected across processors
 */
@RequiredArgsConstructor
public class JavacClassPool extends ClassPool<ClassNode> {
    private final JavacInternals internals;

    /**
     * @param key Key used to instantiate the value
     * @return ClassNode internal node created from the element
     */
    @Override
    protected ClassNode instantiate(TypeElement key) {
        return new ClassNode(key, internals);
    }
}
