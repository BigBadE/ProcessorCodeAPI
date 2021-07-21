package com.bigbade.processorcodeapi.javac.expressions;

import com.bigbade.processorcodeapi.api.code.BinaryOperation;
import com.bigbade.processorcodeapi.api.expressions.IAssignmentOperationExpression;
import com.bigbade.processorcodeapi.javac.utils.InternalWrapperCreator;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.sun.tools.javac.tree.JCTree;

public class AssignmentOperationExpression implements IAssignmentOperationExpression, IJavacExpression<JCTree.JCAssignOp> {
    public static final JCTree.Tag[] OPERATION_TAGS = new JCTree.Tag[] {JCTree.Tag.OR, JCTree.Tag.AND, JCTree.Tag.BITOR,
            JCTree.Tag.BITAND, JCTree.Tag.BITXOR, JCTree.Tag.EQ, JCTree.Tag.NE, JCTree.Tag.LT, JCTree.Tag.GT, JCTree.Tag.LE,
            JCTree.Tag.GE, JCTree.Tag.SL, JCTree.Tag.SR, JCTree.Tag.USR, JCTree.Tag.PLUS, JCTree.Tag.MINUS, JCTree.Tag.MUL,
            JCTree.Tag.DIV, JCTree.Tag.MOD };

    private final IJavacExpression<?> target;
    private final IJavacExpression<?> value;
    private final JCTree.Tag operation;

    public AssignmentOperationExpression(JavacInternals internals, JCTree.JCAssignOp assign) {
        this.target = InternalWrapperCreator.getExpressionFromClass(internals, assign.lhs);
        this.value = InternalWrapperCreator.getExpressionFromClass(internals, assign.rhs);
        this.operation = assign.getTag();
    }

    public AssignmentOperationExpression(IJavacExpression<?> target, IJavacExpression<?> value, BinaryOperation operation) {
        this.target = target;
        this.value = value;
        this.operation = OPERATION_TAGS[operation.ordinal()];
    }

    @Override
    public JCTree.JCAssignOp getExpression(JavacInternals data) {
        return data.getTreeMaker().Assignop(operation, target.getExpression(data),
                value.getExpression(data));
    }
}
