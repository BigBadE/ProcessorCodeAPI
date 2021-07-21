package com.bigbade.processorcodeapi.javac.expressions;

import com.bigbade.processorcodeapi.api.code.IClassType;
import com.bigbade.processorcodeapi.api.expressions.IBasicExpression;
import com.bigbade.processorcodeapi.api.expressions.INewInstanceExpression;
import com.bigbade.processorcodeapi.javac.code.JavacClassType;
import com.bigbade.processorcodeapi.javac.utils.InternalWrapperCreator;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.bigbade.processorcodeapi.javac.utils.ListUtils;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.List;

import javax.annotation.Nullable;
import java.util.Arrays;

public class NewInstanceExpression implements INewInstanceExpression, IJavacExpression<JCTree.JCNewClass> {
    private final IClassType[] genericTypes;
    private final IClassType clazz;
    private final IJavacExpression<?>[] params;

    public NewInstanceExpression(@Nullable IClassType[] generics, IClassType clazz, IBasicExpression... params) {
        this.genericTypes = generics;
        this.clazz = clazz;
        this.params = Arrays.copyOf(params, params.length, IJavacExpression[].class);
    }

    public NewInstanceExpression(JavacInternals internals, JCTree.JCNewClass newClass) {
        this.genericTypes = new IClassType[newClass.typeargs.size()];
        for(int i = 0; i < genericTypes.length; i++) {
            genericTypes[i] = new JavacClassType(internals, (JCTree.JCIdent) newClass.typeargs.get(i));
        }
        this.clazz = new JavacClassType(internals, (JCTree.JCIdent) newClass.clazz);

        this.params = new IJavacExpression[newClass.args.size()];
        for(int i = 0; i < genericTypes.length; i++) {
            params[i] = InternalWrapperCreator.getExpressionFromClass(internals, newClass.args.get(i));
        }
    }

    @Override
    public JCTree.JCNewClass getExpression(JavacInternals internals) {
        return internals.getTreeMaker().NewClass(null,
                genericTypes == null ? List.nil() : ListUtils.convertJavacVersions(internals, (JavacClassType[]) genericTypes),
                ((JavacClassType) clazz).getExpression(internals),
                params.length == 0 ? List.nil() : ListUtils.convertJavacVersions(internals, params), null);
    }
}
