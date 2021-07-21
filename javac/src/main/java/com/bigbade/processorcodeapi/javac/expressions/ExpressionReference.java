package com.bigbade.processorcodeapi.javac.expressions;

import com.bigbade.processorcodeapi.api.code.IClassType;
import com.bigbade.processorcodeapi.api.code.IMethodType;
import com.bigbade.processorcodeapi.api.expressions.IBasicExpression;
import com.bigbade.processorcodeapi.api.expressions.IExpressionReference;
import com.bigbade.processorcodeapi.javac.code.JavacClassType;
import com.bigbade.processorcodeapi.javac.code.JavacVersion;
import com.bigbade.processorcodeapi.javac.utils.InternalWrapperCreator;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.bigbade.processorcodeapi.javac.utils.ListUtils;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.List;

import javax.annotation.Nullable;
import java.util.Arrays;

public class ExpressionReference implements IExpressionReference, IJavacExpression<JCTree.JCMethodInvocation> {
    private final IClassType[] genericTypes;
    private final JavacVersion<? extends JCTree.JCExpression> method;
    private final IJavacExpression<?>[] params;

    public ExpressionReference(JavacInternals internals, JCTree.JCMethodInvocation methodInvocation) {
        genericTypes = new IClassType[methodInvocation.typeargs.size()];
        for(int i = 0; i < genericTypes.length; i++) {
            genericTypes[i] = new JavacClassType(internals,
                    methodInvocation.typeargs.get(i));
        }
        method = InternalWrapperCreator.getExpressionFromClass(internals, methodInvocation.meth);
        params = new IJavacExpression<?>[methodInvocation.args.size()];
        for(int i = 0; i < params.length; i++) {
            params[i] = InternalWrapperCreator.getExpressionFromClass(internals, methodInvocation.args.get(i));
        }
    }

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
    public JCTree.JCMethodInvocation getExpression(JavacInternals internals) {
        return internals.getTreeMaker().Apply(
                genericTypes == null ? List.nil() : ListUtils.convertJavacVersions(internals, (JavacClassType[]) genericTypes),
                method.getExpression(internals),
                params.length == 0 ? List.nil() : ListUtils.convertJavacVersions(internals, params));
    }
}
