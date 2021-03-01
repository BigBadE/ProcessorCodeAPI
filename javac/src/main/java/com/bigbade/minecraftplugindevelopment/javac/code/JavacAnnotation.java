package com.bigbade.minecraftplugindevelopment.javac.code;

import com.bigbade.minecraftplugindevelopment.api.code.IAnnotation;
import com.bigbade.minecraftplugindevelopment.api.code.IClassType;
import com.bigbade.minecraftplugindevelopment.javac.expressions.AssignmentExpression;
import com.bigbade.minecraftplugindevelopment.javac.utils.JavacInternals;
import com.bigbade.minecraftplugindevelopment.javac.utils.MethodUtils;
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
