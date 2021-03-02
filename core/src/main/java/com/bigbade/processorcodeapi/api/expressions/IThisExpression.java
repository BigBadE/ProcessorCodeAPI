package com.bigbade.processorcodeapi.api.expressions;

/**
 * Returns an instance of this, similar to getting a variable but for the current class.
 * Can only be used like normal Java "this" keyword, so not in static methods.
 */
public interface IThisExpression extends IBasicExpression {
}
