package com.bigbade.processorcodeapi.javac.expressions;

import com.bigbade.processorcodeapi.api.code.BinaryOperation;
import com.bigbade.processorcodeapi.api.expressions.IBinaryOperationExpression;
import com.bigbade.processorcodeapi.javac.utils.InternalWrapperCreator;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.sun.tools.javac.tree.JCTree;

public class BinaryOperationExpression implements IBinaryOperationExpression, IJavacExpression<JCTree.JCBinary> {
    private final IJavacExpression<?> leftSide;
    private final IJavacExpression<?> rightSide;
    private final JCTree.Tag operation;

    public BinaryOperationExpression(JavacInternals internals, JCTree.JCBinary assign) {
        this.leftSide = InternalWrapperCreator.getExpressionFromClass(internals, assign.lhs);
        this.rightSide = InternalWrapperCreator.getExpressionFromClass(internals, assign.rhs);
        this.operation = assign.getTag();
    }

    public BinaryOperationExpression(IJavacExpression<?> target, IJavacExpression<?> value, BinaryOperation operation) {
        this.leftSide = target;
        this.rightSide = value;
        this.operation = AssignmentOperationExpression.OPERATION_TAGS[operation.ordinal()];
    }

    @Override
    public JCTree.JCBinary getExpression(JavacInternals data) {
        return data.getTreeMaker().Binary(operation, leftSide.getExpression(data),
                rightSide.getExpression(data));
    }
}
