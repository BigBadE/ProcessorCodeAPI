package com.bigbade.processorcodeapi.api.factories;

import com.bigbade.processorcodeapi.api.code.IClassType;
import com.bigbade.processorcodeapi.api.code.IMethodType;
import com.bigbade.processorcodeapi.api.expressions.IAssignmentExpression;
import com.bigbade.processorcodeapi.api.expressions.IBasicExpression;
import com.bigbade.processorcodeapi.api.expressions.IExpressionReference;
import com.bigbade.processorcodeapi.api.expressions.ILiteralExpression;
import com.bigbade.processorcodeapi.api.expressions.IGetVariableExpression;
import com.bigbade.processorcodeapi.api.expressions.INewInstanceExpression;
import com.bigbade.processorcodeapi.api.expressions.ISelectVariableExpression;
import com.bigbade.processorcodeapi.api.expressions.IThisExpression;
import com.bigbade.processorcodeapi.api.statements.IBasicStatement;
import com.bigbade.processorcodeapi.api.statements.ICallStatement;
import com.bigbade.processorcodeapi.api.statements.IIfStatement;

import javax.annotation.Nullable;

/**
 * A factory class to create code to be inserted into code blocks.
 */
@SuppressWarnings("unused")
public interface ICodeFactory {
    /**
     * Assigns the value to the variable
     * @param variable Name of variable to assign to
     * @param value Value to assign to variable
     * @return Assignment expression with given params
     */
    IAssignmentExpression createAssignment(ISelectVariableExpression variable, IBasicExpression value);

    /**
     * Assigns the value to the variable
     * @param variable Name and class of variable to assign to
     * @param value Value to assign to variable
     * @return Assignment expression with given params
     */
    IAssignmentExpression createAssignment(IGetVariableExpression variable, IBasicExpression value);

    /**
     * Creates a literal of given value
     * @param object Value of literal
     * @param <T> Type of literal
     * @return Literal expression of given value
     */
    <T> ILiteralExpression<T> createLiteral(T object);

    /**
     * Selects a variable with the given name
     * @param name Name of the variable
     * @return Select expression to get the variable of that name
     */
    ISelectVariableExpression selectVariable(String name);

    /**
     * Creates a reference to a specific method in the current class (or statically imported),
     * with the given parameters, so it can be called.
     * @param generics Types of any generic in the method, or null/empty for no generics.
     * @param method Method to be called
     * @param params Parameters of the method
     * @return Reference to the given method with given params and generic types
     */
    IExpressionReference createReference(@Nullable IClassType[] generics, IMethodType method,
                                         IBasicExpression... params);

    /**
     * Creates a reference to a specific method, with the given parameters, so it can be called.
     * @param generics Types of any generic in the method, or null/empty for no generics.
     * @param method Method to be called, internally gotten the same way as fields,
     *               requiring an object and a method name.
     * @param params Parameters of the method
     * @return Reference to the given method with given params and generic types
     */
    IExpressionReference createReference(@Nullable IClassType[] generics, IGetVariableExpression method,
                                         IBasicExpression... params);

    /**
     * Calls the expression referenced
     * @param reference Reference to call
     * @return Call statement
     */
    ICallStatement callReference(IExpressionReference reference);

    /**
     * Gets a field (or method) from the object returned by the expression.
     * @param variable Expression to get the field/method from
     * @param name Name of field/method
     * @return Reference to the given field/method (without parameters)
     */
    IGetVariableExpression getVariable(IBasicExpression variable, String name);

    /**
     * Gets a field (or method) in the current class (or static) from the type represented by the class type.
     * Must abide by the same rules as field/method calls without an object.
     * @param type Type of the class
     * @param name Name of field/method
     * @return Reference to the given field/method (without parameters)
     */
    IGetVariableExpression getVariable(IClassType type, String name);

    /**
     * Creates a reference to the "this" type, abides by the same rules as the "this" keyword in Java.
     * @param thisType Class type to get "this" reference from
     * @return Reference to "this" type
     */
    IThisExpression thisReference(IClassType thisType);

    /**
     * Instantiates the class given the generic types and parameters. Does not need a reference to a specific
     * constructor.
     * @param generics Types of constructor generics (or none/null if there are none)
     * @param clazz Class to call the constructor from
     * @param params Parameters to pass to the constructor.
     * @return Expression to create a new instance of the class.
     */
    INewInstanceExpression instantiateClass(@Nullable IClassType[] generics, IClassType clazz, IBasicExpression... params);

    /**
     * Creates an if statement, taking the condition and if true calling ifPassed, if false calling ifFailed.
     * @param condition Condition, should evaluate to boolean "true" or "false"
     * @param ifPassed Statement to be called if the condition is true
     * @param ifFailed Statement to be called if the condition is false
     * @return If statement with the given parameters.
     */
    IIfStatement createIfStatement(IBasicExpression condition, IBasicStatement ifPassed, IBasicStatement ifFailed);
}
