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

@SuppressWarnings("unused")
public interface ICodeFactory {
    IAssignmentExpression createAssignment(ISelectVariableExpression variable, IBasicExpression value);

    <T> ILiteralExpression<T> createLiteral(T object);

    ISelectVariableExpression selectVariable(String name);

    IExpressionReference createReference(@Nullable IClassType[] generics, IMethodType method,
                                         IBasicExpression... params);

    IExpressionReference createReference(@Nullable IClassType[] generics, IBasicExpression method,
                                         IBasicExpression... params);

    ICallStatement callReference(IExpressionReference reference);

    IGetVariableExpression getVariable(IBasicExpression variable, String name);

    IGetVariableExpression getVariable(IClassType type, String name);

    IThisExpression thisReference(IClassType thisType);

    INewInstanceExpression instantiateClass(@Nullable IClassType[] generics, IClassType clazz, IBasicExpression... params);

    IIfStatement createIfStatement(IBasicExpression condition, IBasicStatement ifPassed, IBasicStatement ifFailed);
}
