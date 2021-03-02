package com.bigbade.processorcodeapi.api;

import java.util.HashMap;
import java.util.Map;

/**
 * An object that pools its values. This allows multiple annotations processors implementing this API (and multiple
 * individual Processors) to have changes made reflected no matter where the value is accessed
 * @param <K> Key used to instantiate K. Usually the compiler-specific implementation
 * @param <V> Value instantiated by the key. Usually the interface for the API
 */
public abstract class PoolableObject<K, V> {
    private final Map<K, V> pool = new HashMap<>();

    /**
     * Returns the given object, or instantiates a new instance if one is not found in the pool
     *
     * This method should be implemented by superclasses to take a non-compiler specific key (such as a name)
     *
     * @param key Key used to find values/instantiate new values
     * @return Value represented by the given key
     */
    public V getObject(K key) {
        if(pool.containsKey(key)) {
            return pool.get(key);
        } else {
            V found = instantiate(key);
            pool.put(key, found);
            return found;
        }
    }

    /**
     * Instantiates the value given the key
     * @param key Key used to instantiate the value
     * @return Value represented by the given key, usually the non-compiler specific API value
     */
    protected abstract V instantiate(K key);
}
