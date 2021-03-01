package com.bigbade.minecraftplugindevelopment.javac.expressions;

import com.bigbade.minecraftplugindevelopment.api.expressions.ISelectVariableExpression;
import com.bigbade.minecraftplugindevelopment.javac.utils.JavacInternals;
import com.sun.tools.javac.tree.JCTree;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SelectVariableExpression implements ISelectVariableExpression, IJavacExpression<JCTree.JCIdent> {
    private final String variable;

    @Override
    public JCTree.JCIdent getExpression(JavacInternals internals) {
        return internals.getTreeMaker().Ident(internals.getNames().fromString(variable));
    }
}
