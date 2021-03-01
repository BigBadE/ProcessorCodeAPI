package com.bigbade.processorcodeapi.javac.expressions;

import com.bigbade.processorcodeapi.api.expressions.ISelectVariableExpression;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
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
