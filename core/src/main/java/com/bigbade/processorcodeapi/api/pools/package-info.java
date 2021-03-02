/**
 * Pools are objects that contain a key/value pair which prevent making copies of singleton objects (like methods,
 * variables, and classes). They also control instancing the object. This is also used to synchronize changes across
 * processors because changes to defs and elements aren't reflected in other processors.
 */
package com.bigbade.processorcodeapi.api.pools;
