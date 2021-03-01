package com.bigbade.processorcodeapi.javac.statements;

import com.bigbade.processorcodeapi.api.statements.ICallStatement;
import com.bigbade.processorcodeapi.api.expressions.IExpressionReference;
import com.bigbade.processorcodeapi.javac.expressions.ExpressionReference;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
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
