package com.bigbade.minecraftplugindevelopment.javac.statements;

import com.bigbade.minecraftplugindevelopment.api.statements.IIfStatement;
import com.bigbade.minecraftplugindevelopment.javac.expressions.IJavacExpression;
import com.bigbade.minecraftplugindevelopment.javac.utils.JavacInternals;
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
