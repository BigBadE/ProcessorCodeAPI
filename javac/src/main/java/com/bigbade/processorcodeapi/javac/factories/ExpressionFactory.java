package com.bigbade.processorcodeapi.javac.factories;

import com.bigbade.processorcodeapi.api.code.IClassType;
import com.bigbade.processorcodeapi.api.code.IMethodType;
import com.bigbade.processorcodeapi.api.expressions.IAssignmentExpression;
import com.bigbade.processorcodeapi.api.expressions.IBasicExpression;
import com.bigbade.processorcodeapi.api.expressions.IExpressionReference;
import com.bigbade.processorcodeapi.api.expressions.IGetVariableExpression;
import com.bigbade.processorcodeapi.api.expressions.ILiteralExpression;
import com.bigbade.processorcodeapi.api.expressions.INewInstanceExpression;
import com.bigbade.processorcodeapi.api.expressions.ISelectExpression;
import com.bigbade.processorcodeapi.api.factories.IExpressionFactory;
import com.bigbade.processorcodeapi.javac.expressions.AssignmentExpression;
import com.bigbade.processorcodeapi.javac.expressions.ExpressionReference;
import com.bigbade.processorcodeapi.javac.expressions.GetVariableExpression;
import com.bigbade.processorcodeapi.javac.expressions.IJavacExpression;
import com.bigbade.processorcodeapi.javac.expressions.LiteralExpression;
import com.bigbade.processorcodeapi.javac.expressions.NewInstanceExpression;
import com.bigbade.processorcodeapi.javac.expressions.SelectVariableExpression;

import javax.annotation.Nullable;

public class ExpressionFactory implements IExpressionFactory {
    @Override
    public IAssignmentExpression createAssignment(ISelectExpression variable, IBasicExpression value) {
        return new AssignmentExpression((IJavacExpression<?>) variable, (IJavacExpression<?>) value);
    }

    @Override
    public IAssignmentExpression createAssignment(IGetVariableExpression variable, IBasicExpression value) {
        return new AssignmentExpression((IJavacExpression<?>) variable, (IJavacExpression<?>) value);
    }

    @Override
    public <T> ILiteralExpression<T> createLiteral(T object) {
        return new LiteralExpression<>(object);
    }

    @Override
    public ISelectExpression selectVariable(String name) {
        return new SelectVariableExpression(name);
    }

    @Override
    public IExpressionReference createReference(IClassType[] generics, IMethodType method, IBasicExpression... params) {
        return new ExpressionReference(generics, method, params);
    }

    @Override
    public IExpressionReference createReference(@Nullable IClassType[] generics, IGetVariableExpression method,
                                                IBasicExpression... params) {
        return new ExpressionReference(generics, method, params);
    }

    @Override
    public IGetVariableExpression getVariable(IBasicExpression variable, String name) {
        return new GetVariableExpression(variable, name);
    }

    @Override
    public IGetVariableExpression getVariable(IClassType type, String name) {
        return new GetVariableExpression(type, name);
    }

    @Override
    public ISelectExpression thisReference(IClassType thisType) {
        return new SelectVariableExpression(thisType);
    }

    @Override
    public INewInstanceExpression instantiateClass(@Nullable IClassType[] generics, IClassType clazz,
                                                   IBasicExpression... params) {
        return new NewInstanceExpression(generics, clazz, params);
    }
}
