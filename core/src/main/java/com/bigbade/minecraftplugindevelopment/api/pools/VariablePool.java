package com.bigbade.minecraftplugindevelopment.api.pools;

import com.bigbade.minecraftplugindevelopment.api.PoolableObject;
import com.bigbade.minecraftplugindevelopment.api.leaf.IVariableLeaf;

import javax.lang.model.element.VariableElement;

/**
 * A pool of variables with an Element key
 * @param <V> Internal implementation of IVariableLeaf
 * @see VariableElement
 * @see IVariableLeaf
 */
public abstract class VariablePool<V extends IVariableLeaf> extends PoolableObject<VariableElement, V> { }
