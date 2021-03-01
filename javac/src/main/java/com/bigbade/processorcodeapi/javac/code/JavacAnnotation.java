package com.bigbade.processorcodeapi.javac.code;

import com.bigbade.processorcodeapi.api.code.IAnnotation;
import com.bigbade.processorcodeapi.api.code.IClassType;
import com.bigbade.processorcodeapi.javac.expressions.AssignmentExpression;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.bigbade.processorcodeapi.javac.utils.MethodUtils;
import com.sun.tools.javac.tree.JCTree;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class JavacAnnotation implements IAnnotation, JavacVersion<JCTree.JCAnnotation> {
    private final IClassType type;
    private final List<AssignmentExpression> values;

    @Override
    public JCTree.JCAnnotation getExpression(JavacInternals internals) {
        return MethodUtils.getAnnotation(internals, type, values);
    }
}
