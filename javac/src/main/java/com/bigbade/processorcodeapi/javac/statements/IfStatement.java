package com.bigbade.processorcodeapi.javac.statements;

import com.bigbade.processorcodeapi.api.statements.IIfStatement;
import com.bigbade.processorcodeapi.javac.expressions.IJavacExpression;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.sun.tools.javac.tree.JCTree;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IfStatement implements IIfStatement, IJavacStatement<JCTree.JCIf> {
    private final IJavacExpression<?> comparison;
    private final IJavacStatement<?> ifPassStatement;
    private final IJavacStatement<?> elseStatement;

    @Override
    public JCTree.JCIf getExpression(JavacInternals internals) {
        return internals.getTreeMaker().If(comparison.getExpression(internals), ifPassStatement.getExpression(internals),
                elseStatement.getExpression(internals));
    }
}
