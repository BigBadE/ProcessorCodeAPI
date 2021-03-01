package com.bigbade.processorcodeapi.api.pools;

import com.bigbade.processorcodeapi.api.PoolableObject;
import com.bigbade.processorcodeapi.api.leaf.IVariableLeaf;

import javax.lang.model.element.VariableElement;

/**
 * A pool of variables with an Element key
 * @param <V> Internal implementation of IVariableLeaf
 * @see VariableElement
 * @see IVariableLeaf
 */
public abstract class VariablePool<V extends IVariableLeaf> extends PoolableObject<VariableElement, V> { }
