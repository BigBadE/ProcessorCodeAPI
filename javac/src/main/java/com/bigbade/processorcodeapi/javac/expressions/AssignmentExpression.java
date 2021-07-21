package com.bigbade.processorcodeapi.javac.expressions;

import com.bigbade.processorcodeapi.api.expressions.IAssignmentExpression;
import com.bigbade.processorcodeapi.javac.utils.InternalWrapperCreator;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.sun.tools.javac.tree.JCTree;

public class AssignmentExpression implements IAssignmentExpression, IJavacExpression<JCTree.JCAssign> {
    private final IJavacExpression<?> target;
    private final IJavacExpression<?> value;

    public AssignmentExpression(JavacInternals internals, JCTree.JCAssign assign) {
        this.target = InternalWrapperCreator.getExpressionFromClass(internals, assign.lhs);
        this.value = InternalWrapperCreator.getExpressionFromClass(internals, assign.rhs);
    }

    public AssignmentExpression(IJavacExpression<?> target, IJavacExpression<?> value) {
        this.target = target;
        this.value = value;
    }

    @Override
    public JCTree.JCAssign getExpression(JavacInternals data) {
        return data.getTreeMaker().Assign(target.getExpression(data),
                value.getExpression(data));
    }
}
