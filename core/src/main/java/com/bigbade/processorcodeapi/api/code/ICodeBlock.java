package com.bigbade.processorcodeapi.api.code;

import com.bigbade.processorcodeapi.api.statements.IBasicStatement;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Represents a block of code in memory, such as a method body, lambda body, etc...
 */
public interface ICodeBlock extends IBasicStatement {
    /**
     * Adds a statement to the code block.
     * Must be a statement over an expression.
     *
     * @param statement Code to add to the code block
     */
    void addStatement(IBasicStatement statement);

    /**
     * Adds a statement to the code block.
     * Must be a statement over an expression.
     *
     * @param index     Index to add to
     * @param statement Code to add to the code block
     */
    void addStatement(int index, IBasicStatement statement);

    /**
     * Replaces an existing statement to the code block.
     * Must be a statement over an expression.
     *
     * @param index     Index to replace at
     * @param statement Code to add to the code block
     */
    void replaceStatement(int index, IBasicStatement statement);


    /**
     * Returns the statement at the index.
     *
     * @param index Index to get from
     * @return Statement at that index
     */
    IBasicStatement getStatement(int index);

    /**
     * Gets the index of all statements conforming to the inputted class.
     *
     * @param statement Statement class to get from
     * @param verifier  Optional function verifying the statement
     * @return List of indexes with the passed in statements.
     */
    List<Integer> getStatementIndexes(Class<? extends IBasicStatement> statement,
                                      @Nullable Predicate<IBasicStatement> verifier);

    /**
     * Gets all statements conforming to the inputted class.
     *
     * @param statement Statement class to get from
     * @param verifier  Optional function verifying the statement
     * @param <T>       Statement type
     * @return List of statements matching T and the verifier.
     */
    <T extends IBasicStatement> List<T> getStatements(Class<T> statement,
                                                      @Nullable Predicate<T> verifier);

    /**
     * Gets the first statement conforming to the inputted class.
     *
     * @param statement Statement class to get from
     * @param verifier  Optional function verifying the statement
     * @param <T>       Statement type
     * @return First statement found, if any
     */
    <T extends IBasicStatement> Optional<T> getStatement(Class<T> statement,
                                                         @Nullable Predicate<T> verifier);
}
