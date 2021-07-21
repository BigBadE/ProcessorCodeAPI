package com.bigbade.processorcodeapi.javac.expressions;

import com.bigbade.processorcodeapi.api.expressions.IArrayGetExpression;
import com.bigbade.processorcodeapi.javac.utils.InternalWrapperCreator;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.sun.tools.javac.tree.JCTree;


public class ArrayGetExpression implements IArrayGetExpression, IJavacExpression<JCTree.JCArrayAccess> {
    public final IJavacExpression<?> array;
    public final IJavacExpression<?> index;

    public ArrayGetExpression(JavacInternals internals, JCTree.JCArrayAccess arrayGet) {
        this.array = InternalWrapperCreator.getExpressionFromClass(internals, arrayGet.indexed);
        this.index = InternalWrapperCreator.getExpressionFromClass(internals, arrayGet.index);
    }

    public ArrayGetExpression(IJavacExpression<?> array, IJavacExpression<?> index) {
        this.array = array;
        this.index = index;
    }

    @Override
    public JCTree.JCArrayAccess getExpression(JavacInternals internals) {
        return internals.getTreeMaker().Indexed(array.getExpression(internals), index.getExpression(internals));
    }
}
