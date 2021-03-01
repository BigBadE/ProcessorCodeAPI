package com.bigbade.minecraftplugindevelopment.javac.expressions;

import com.bigbade.minecraftplugindevelopment.api.code.IClassType;
import com.bigbade.minecraftplugindevelopment.api.expressions.IThisExpression;
import com.bigbade.minecraftplugindevelopment.javac.utils.JavacInternals;
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
