package com.bigbade.processorcodeapi.javac.factories;

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
import com.bigbade.processorcodeapi.api.factories.ICodeFactory;
import com.bigbade.processorcodeapi.api.statements.IBasicStatement;
import com.bigbade.processorcodeapi.api.statements.ICallStatement;
import com.bigbade.processorcodeapi.api.statements.IIfStatement;
import com.bigbade.processorcodeapi.javac.expressions.AssignmentExpression;
import com.bigbade.processorcodeapi.javac.expressions.ExpressionReference;
import com.bigbade.processorcodeapi.javac.expressions.NewInstanceExpression;
import com.bigbade.processorcodeapi.javac.expressions.SelectVariableExpression;
import com.bigbade.processorcodeapi.javac.expressions.IJavacExpression;
import com.bigbade.processorcodeapi.javac.expressions.LiteralExpression;
import com.bigbade.processorcodeapi.javac.expressions.GetVariableExpression;
import com.bigbade.processorcodeapi.javac.expressions.ThisExpression;
import com.bigbade.processorcodeapi.javac.statements.CallStatement;
import com.bigbade.processorcodeapi.javac.statements.IJavacStatement;
import com.bigbade.processorcodeapi.javac.statements.IfStatement;
import com.sun.tools.javac.tree.JCTree;

import javax.annotation.Nullable;

public class CodeFactory implements ICodeFactory {
    @SuppressWarnings("unchecked")
    @Override
    public IAssignmentExpression createAssignment(ISelectVariableExpression variable, IBasicExpression value) {
        return new AssignmentExpression(variable, (IJavacExpression<JCTree.JCIdent>) value);
    }

    @Override
    public <T> ILiteralExpression<T> createLiteral(T object) {
        return new LiteralExpression<>(object);
    }

    @Override
    public ISelectVariableExpression selectVariable(String name) {
        return new SelectVariableExpression(name);
    }

    @Override
    public IExpressionReference createReference(IClassType[] generics, IMethodType method, IBasicExpression... params) {
        return new ExpressionReference(generics, method, params);
    }

    @Override
    public IExpressionReference createReference(@Nullable IClassType[] generics, IBasicExpression method,
                                                IBasicExpression... params) {
        return new ExpressionReference(generics, method, params);
    }

    @Override
    public ICallStatement callReference(IExpressionReference reference) {
        return new CallStatement(reference);
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
    public IThisExpression thisReference(IClassType thisType) {
        return new ThisExpression(thisType);
    }

    @Override
    public INewInstanceExpression instantiateClass(@Nullable IClassType[] generics, IClassType clazz,
                                                   IBasicExpression... params) {
        return new NewInstanceExpression(generics, clazz, params);
    }

    @Override
    public IIfStatement createIfStatement(IBasicExpression condition, IBasicStatement ifPassed, IBasicStatement ifFailed) {
        return new IfStatement((IJavacExpression<?>) condition, (IJavacStatement<?>) ifPassed,
                (IJavacStatement<?>) ifFailed);
    }
}
