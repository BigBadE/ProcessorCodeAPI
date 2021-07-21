package com.bigbade.processorcodeapi.javac.factories;

import com.bigbade.processorcodeapi.api.expressions.IBasicExpression;
import com.bigbade.processorcodeapi.api.expressions.IExpressionReference;
import com.bigbade.processorcodeapi.api.factories.IStatementFactory;
import com.bigbade.processorcodeapi.api.statements.IBasicStatement;
import com.bigbade.processorcodeapi.api.statements.ICallStatement;
import com.bigbade.processorcodeapi.api.statements.IIfStatement;
import com.bigbade.processorcodeapi.javac.expressions.IJavacExpression;
import com.bigbade.processorcodeapi.javac.statements.CallStatement;
import com.bigbade.processorcodeapi.javac.statements.IJavacStatement;
import com.bigbade.processorcodeapi.javac.statements.IfStatement;

public class StatementFactory implements IStatementFactory {
    @Override
    public IIfStatement createIfStatement(IBasicExpression condition, IBasicStatement ifPassed, IBasicStatement ifFailed) {
        return new IfStatement((IJavacExpression<?>) condition, (IJavacStatement<?>) ifPassed,
                (IJavacStatement<?>) ifFailed);
    }

    @Override
    public ICallStatement callReference(IExpressionReference reference) {
        return new CallStatement(reference);
    }
}
