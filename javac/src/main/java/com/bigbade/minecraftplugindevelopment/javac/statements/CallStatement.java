package com.bigbade.minecraftplugindevelopment.javac.statements;

import com.bigbade.minecraftplugindevelopment.api.statements.ICallStatement;
import com.bigbade.minecraftplugindevelopment.api.expressions.IExpressionReference;
import com.bigbade.minecraftplugindevelopment.javac.expressions.ExpressionReference;
import com.bigbade.minecraftplugindevelopment.javac.utils.JavacInternals;
import com.sun.tools.javac.tree.JCTree;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CallStatement implements ICallStatement, IJavacStatement<JCTree.JCExpressionStatement> {
    private final IExpressionReference reference;

    @Override
    public JCTree.JCExpressionStatement getExpression(JavacInternals internals) {
        return internals.getTreeMaker().Exec(((ExpressionReference) reference).getExpression(internals));
    }
}
