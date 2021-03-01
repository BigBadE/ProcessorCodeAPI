package com.bigbade.minecraftplugindevelopment.javac.expressions;

import com.bigbade.minecraftplugindevelopment.api.expressions.IBasicExpression;
import com.bigbade.minecraftplugindevelopment.javac.code.JavacVersion;
import com.sun.tools.javac.tree.JCTree;

public interface IJavacExpression<T extends JCTree.JCExpression> extends IBasicExpression, JavacVersion<T> {

}
