package com.bigbade.minecraftplugindevelopment.javac.expressions;

import com.bigbade.minecraftplugindevelopment.api.code.IClassType;
import com.bigbade.minecraftplugindevelopment.api.code.IMethodType;
import com.bigbade.minecraftplugindevelopment.api.expressions.IBasicExpression;
import com.bigbade.minecraftplugindevelopment.api.expressions.IExpressionReference;
import com.bigbade.minecraftplugindevelopment.javac.code.JavacClassType;
import com.bigbade.minecraftplugindevelopment.javac.code.JavacVersion;
import com.bigbade.minecraftplugindevelopment.javac.utils.JavacInternals;
import com.bigbade.minecraftplugindevelopment.javac.utils.ListUtils;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.List;

import javax.annotation.Nullable;
import java.util.Arrays;

public class ExpressionReference implements IExpressionReference, IJavacExpression<JCTree.JCExpression> {
    private final IClassType[] genericTypes;
    private final JavacVersion<? extends JCTree.JCExpression> method;
    private final IJavacExpression<?>[] params;

    @SuppressWarnings("unchecked")
    public ExpressionReference(@Nullable IClassType[] generics, IMethodType method, IBasicExpression... params) {
        this(generics, (JavacVersion<? extends JCTree.JCExpression>) method, params);
    }

    @SuppressWarnings("unchecked")
    public ExpressionReference(@Nullable IClassType[] generics, IBasicExpression method, IBasicExpression... params) {
        this(generics, (JavacVersion<? extends JCTree.JCExpression>) method, params);
    }

    //SonarLint apparently thinks this is unused?
    @SuppressWarnings("unused")
    private ExpressionReference(@Nullable IClassType[] generics, JavacVersion<? extends JCTree.JCExpression> method,
                                IBasicExpression... params) {
        genericTypes = generics;
        this.method = method;
        this.params = Arrays.copyOf(params, params.length, IJavacExpression[].class);
    }

    @Override
    public JCTree.JCExpression getExpression(JavacInternals internals) {
        return internals.getTreeMaker().Apply(
                genericTypes == null ? List.nil() : ListUtils.convertJavacVersions(internals, (JavacClassType[]) genericTypes),
                method.getExpression(internals),
                params.length == 0 ? List.nil() : ListUtils.convertJavacVersions(internals, params));
    }
}
