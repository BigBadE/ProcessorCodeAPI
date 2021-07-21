package com.bigbade.processorcodeapi.javac.expressions;

import com.bigbade.processorcodeapi.api.code.IClassType;
import com.bigbade.processorcodeapi.api.expressions.ISelectExpression;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.sun.tools.javac.tree.JCTree;

public class SelectVariableExpression implements ISelectExpression, IJavacExpression<JCTree.JCIdent> {
    private final String variable;

    public SelectVariableExpression(String variable) {
        this.variable = variable;
    }

    public SelectVariableExpression(IClassType type) {
        this.variable = type.getQualifiedName();
    }

    public SelectVariableExpression(JCTree.JCIdent identifier) {
        this.variable = identifier.name.toString();
    }

    @Override
    public JCTree.JCIdent getExpression(JavacInternals internals) {
        return internals.getTreeMaker().Ident(internals.getNames().fromString(variable));
    }
}
