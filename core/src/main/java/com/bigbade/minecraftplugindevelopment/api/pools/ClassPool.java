package com.bigbade.minecraftplugindevelopment.api.pools;

import com.bigbade.minecraftplugindevelopment.api.PoolableObject;
import com.bigbade.minecraftplugindevelopment.api.nodes.IClassNode;

import javax.lang.model.element.TypeElement;

/**
 * A pool of classes with an Element key
 * @param <V> Internal implementation of IClassNode
 * @see TypeElement
 * @see IClassNode
 */
public abstract class ClassPool<V extends IClassNode> extends PoolableObject<TypeElement, V> { }
