package com.bigbade.processorcodeapi.javac.expressions;

import com.bigbade.processorcodeapi.api.code.IClassType;
import com.bigbade.processorcodeapi.api.expressions.IBasicExpression;
import com.bigbade.processorcodeapi.api.expressions.IGetVariableExpression;
import com.bigbade.processorcodeapi.javac.code.JavacClassType;
import com.bigbade.processorcodeapi.javac.code.JavacVersion;
import com.bigbade.processorcodeapi.javac.utils.InternalWrapperCreator;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.sun.tools.javac.tree.JCTree;

public class GetVariableExpression implements IGetVariableExpression, IJavacExpression<JCTree.JCFieldAccess> {
    private final JavacVersion<? extends JCTree.JCExpression> calling;
    private final String name;

    public GetVariableExpression(IBasicExpression variable, String name) {
        this.calling = (IJavacExpression<?>) variable;
        this.name = name;
    }

    public GetVariableExpression(IClassType type, String name) {
        this.calling = (JavacClassType) type;
        this.name = name;
    }

    public GetVariableExpression(JavacInternals internals, JCTree.JCFieldAccess arrayGet) {
        this.calling = InternalWrapperCreator.getExpressionFromClass(internals, arrayGet.selected);
        this.name = arrayGet.name.toString();
    }

    @Override
    public JCTree.JCFieldAccess getExpression(JavacInternals internals) {
        return internals.getTreeMaker().Select(calling.getExpression(internals),
                internals.getNames().fromString(name));
    }
}
