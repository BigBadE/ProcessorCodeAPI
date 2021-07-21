package com.bigbade.processorcodeapi.javac.statements;

import com.bigbade.processorcodeapi.api.statements.IIfStatement;
import com.bigbade.processorcodeapi.javac.expressions.IJavacExpression;
import com.bigbade.processorcodeapi.javac.utils.InternalWrapperCreator;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.sun.tools.javac.tree.JCTree;

public class IfStatement implements IIfStatement, IJavacStatement<JCTree.JCIf> {
    private final IJavacExpression<?> comparison;
    private final IJavacStatement<?> ifPassStatement;
    private final IJavacStatement<?> elseStatement;

    public IfStatement(IJavacExpression<?> comparison, IJavacStatement<?> ifPassStatement, IJavacStatement<?> elseStatement) {
        this.comparison = comparison;
        this.ifPassStatement = ifPassStatement;
        this.elseStatement = elseStatement;
    }

    public IfStatement(JavacInternals internals, JCTree.JCIf jcIf) {
        comparison = InternalWrapperCreator.getExpressionFromClass(internals, jcIf.cond);
        ifPassStatement = InternalWrapperCreator.getStatementFromClass(internals, jcIf.thenpart);
        elseStatement = InternalWrapperCreator.getStatementFromClass(internals, jcIf.elsepart);
    }

    @Override
    public JCTree.JCIf getExpression(JavacInternals internals) {
        return internals.getTreeMaker().If(comparison.getExpression(internals), ifPassStatement.getExpression(internals),
                elseStatement.getExpression(internals));
    }
}
