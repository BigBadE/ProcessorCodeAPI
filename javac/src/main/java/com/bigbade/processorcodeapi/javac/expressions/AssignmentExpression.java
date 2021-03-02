package com.bigbade.processorcodeapi.javac.expressions;

import com.bigbade.processorcodeapi.api.expressions.IAssignmentExpression;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.sun.tools.javac.tree.JCTree;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AssignmentExpression implements IAssignmentExpression, IJavacExpression<JCTree.JCAssign> {
    private final IJavacExpression<?> target;
    private final IJavacExpression<?> value;

    @Override
    public JCTree.JCAssign getExpression(JavacInternals data) {
        return data.getTreeMaker().Assign(target.getExpression(data),
                value.getExpression(data));
    }
}
