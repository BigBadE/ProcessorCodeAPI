package com.bigbade.processorcodeapi.api.code;

import com.bigbade.processorcodeapi.api.statements.IBasicStatement;

/**
 * Represents a block of code in memory, such as a method body, lambda body, etc...
 */
public interface ICodeBlock {
    /**
     * Adds a statement to the code block.
     * Must be a statement over an expression.
     * @param statement Code to add to the code block
     */
    void addStatement(IBasicStatement statement);
}
