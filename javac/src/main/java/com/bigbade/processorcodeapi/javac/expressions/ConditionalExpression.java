package com.bigbade.processorcodeapi.javac.expressions;

import com.bigbade.processorcodeapi.api.expressions.IConditionalExpression;
import com.bigbade.processorcodeapi.javac.utils.InternalWrapperCreator;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.sun.tools.javac.tree.JCTree;

public class ConditionalExpression implements IConditionalExpression, IJavacExpression<JCTree.JCConditional> {
    private final IJavacExpression<?> condition;
    private final IJavacExpression<?> trueSide;
    private final IJavacExpression<?> falseSide;

    public ConditionalExpression(IJavacExpression<?> condition, IJavacExpression<?> leftSide, IJavacExpression<?> rightSide) {
        this.condition = condition;
        this.trueSide = leftSide;
        this.falseSide = rightSide;
    }

    public ConditionalExpression(JavacInternals internals, JCTree.JCConditional condition) {
        this.condition = InternalWrapperCreator.getExpressionFromClass(internals, condition.cond);
        this.trueSide = InternalWrapperCreator.getExpressionFromClass(internals, condition.truepart);
        this.falseSide = InternalWrapperCreator.getExpressionFromClass(internals, condition.falsepart);
    }

    @Override
    public JCTree.JCConditional getExpression(JavacInternals internals) {
        return internals.getTreeMaker().Conditional(condition.getExpression(internals),
                trueSide.getExpression(internals), falseSide.getExpression(internals));
    }
}
