package com.bigbade.processorcodeapi.api.pools;

import com.bigbade.processorcodeapi.api.nodes.IMethodNode;

import javax.lang.model.element.ExecutableElement;

/**
 * A pool of methods with an Element key
 * @param <V> Internal implementation of IMethodNode
 * @see ExecutableElement
 * @see IMethodNode
 */
public abstract class MethodPool<V extends IMethodNode> extends PoolableObject<ExecutableElement, V> { }
