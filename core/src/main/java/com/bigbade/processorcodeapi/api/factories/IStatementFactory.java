package com.bigbade.processorcodeapi.api.factories;

import com.bigbade.processorcodeapi.api.expressions.IBasicExpression;
import com.bigbade.processorcodeapi.api.expressions.IExpressionReference;
import com.bigbade.processorcodeapi.api.statements.IBasicStatement;
import com.bigbade.processorcodeapi.api.statements.ICallStatement;
import com.bigbade.processorcodeapi.api.statements.IIfStatement;

/**
 * A factory class to create code to be inserted into code blocks.
 */
public interface IStatementFactory {
    /**
     * Creates an if statement, taking the condition and if true calling ifPassed, if false calling ifFailed.
     * Code blocks are also considered statements, and can be added freely.
     * @param condition Condition, should evaluate to boolean "true" or "false"
     * @param ifPassed Statement to be called if the condition is true
     * @param ifFailed Statement to be called if the condition is false
     * @return If statement with the given parameters.
     */
    IIfStatement createIfStatement(IBasicExpression condition, IBasicStatement ifPassed, IBasicStatement ifFailed);

    /**
     * Calls the expression referenced
     * @param reference Reference to call
     * @return Call statement
     */
    ICallStatement callReference(IExpressionReference reference);
}
