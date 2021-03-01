package com.bigbade.processorcodeapi.javac.expressions;

import com.bigbade.processorcodeapi.api.expressions.IBasicExpression;
import com.bigbade.processorcodeapi.javac.code.JavacVersion;
import com.sun.tools.javac.tree.JCTree;

public interface IJavacExpression<T extends JCTree.JCExpression> extends IBasicExpression, JavacVersion<T> {

}
