package com.bigbade.minecraftplugindevelopment.javac.expressions;

import com.bigbade.minecraftplugindevelopment.api.code.IClassType;
import com.bigbade.minecraftplugindevelopment.api.expressions.IBasicExpression;
import com.bigbade.minecraftplugindevelopment.api.expressions.INewInstanceExpression;
import com.bigbade.minecraftplugindevelopment.javac.code.JavacClassType;
import com.bigbade.minecraftplugindevelopment.javac.utils.JavacInternals;
import com.bigbade.minecraftplugindevelopment.javac.utils.ListUtils;
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

    @Override
    public JCTree.JCNewClass getExpression(JavacInternals internals) {
        return internals.getTreeMaker().NewClass(null,
                genericTypes == null ? List.nil() : ListUtils.convertJavacVersions(internals, (JavacClassType[]) genericTypes),
                ((JavacClassType) clazz).getExpression(internals),
                params.length == 0 ? List.nil() : ListUtils.convertJavacVersions(internals, params), null);
    }
}
