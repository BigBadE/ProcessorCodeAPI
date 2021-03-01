package com.bigbade.processorcodeapi.javac.expressions;

import com.bigbade.processorcodeapi.api.code.IClassType;
import com.bigbade.processorcodeapi.api.expressions.IThisExpression;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.tree.JCTree;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ThisExpression implements IThisExpression, IJavacExpression<JCTree.JCIdent> {
    private final IClassType type;

    @Override
    public JCTree.JCIdent getExpression(JavacInternals internals) {
        return (JCTree.JCIdent) internals.getTreeMaker().This((Type) type.getElement().asType());
    }
}
