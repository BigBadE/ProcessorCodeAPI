package com.bigbade.minecraftplugindevelopment.javac.expressions;

import com.bigbade.minecraftplugindevelopment.api.expressions.IAssignmentExpression;
import com.bigbade.minecraftplugindevelopment.api.expressions.ISelectVariableExpression;
import com.bigbade.minecraftplugindevelopment.javac.utils.JavacInternals;
import com.sun.tools.javac.tree.JCTree;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AssignmentExpression implements IAssignmentExpression, IJavacExpression<JCTree.JCAssign> {
    private final ISelectVariableExpression variable;
    private final IJavacExpression<JCTree.JCIdent> value;

    @Override
    public JCTree.JCAssign getExpression(JavacInternals data) {
        return data.getTreeMaker().Assign(((SelectVariableExpression) variable).getExpression(data),
                value.getExpression(data));
    }
}
