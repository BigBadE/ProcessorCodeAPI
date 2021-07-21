package com.bigbade.processorcodeapi.javac.expressions;

import com.bigbade.processorcodeapi.api.expressions.IErroneousExpression;
import com.bigbade.processorcodeapi.javac.code.JavacVersion;
import com.bigbade.processorcodeapi.javac.utils.InternalWrapperCreator;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.bigbade.processorcodeapi.javac.utils.ListUtils;
import com.sun.tools.javac.tree.JCTree;

import java.util.ArrayList;
import java.util.List;

public class ErroniusExpression implements IErroneousExpression, IJavacExpression<JCTree.JCErroneous> {
    private final List<JavacVersion<?>> errors;

    public ErroniusExpression(List<JavacVersion<?>> errors) {
        this.errors = errors;
    }

    public ErroniusExpression(JavacInternals internals, JCTree.JCErroneous erroneous) {
        this.errors = new ArrayList<>();
        for(JCTree error : erroneous.errs) {
            if(error instanceof JCTree.JCExpression) {
                errors.add(InternalWrapperCreator.getExpressionFromClass(internals, (JCTree.JCExpression) error));
            } else {
                errors.add(InternalWrapperCreator.getStatementFromClass(internals, (JCTree.JCStatement) error));
            }
        }
    }

    @Override
    public JCTree.JCErroneous getExpression(JavacInternals internals) {
        return internals.getTreeMaker().Erroneous(ListUtils.convertJavacVersions(internals, errors));
    }
}
