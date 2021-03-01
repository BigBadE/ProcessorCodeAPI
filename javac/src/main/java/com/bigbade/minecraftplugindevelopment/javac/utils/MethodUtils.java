package com.bigbade.minecraftplugindevelopment.javac.utils;

import com.bigbade.minecraftplugindevelopment.api.code.IClassType;
import com.bigbade.minecraftplugindevelopment.javac.expressions.AssignmentExpression;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;

import java.util.ArrayList;
import java.util.List;

public final class MethodUtils {
    private MethodUtils() {}

    /**
     * Constructs a JCAnnotation with the parameters
     * @param internals Internals used to create the JCAnnotation
     * @param type Annotation type
     * @param args Arguments of the annotation
     * @return JCAnnotation with given parameters
     */
    public static JCTree.JCAnnotation getAnnotation(JavacInternals internals, IClassType type,
                                                    List<AssignmentExpression> args) {
        TreeMaker treeMaker = internals.getTreeMaker();
        List<JCTree.JCExpression> argsList = new ArrayList<>();
        for(AssignmentExpression assignExpression : args) {
            argsList.add(assignExpression.getExpression(internals));
        }
        return treeMaker.Annotation(treeMaker.Ident(internals.getNames().fromString(type.getQualifiedName())),
                ListUtils.convertList(argsList));
    }
}
