package com.bigbade.processorcodeapi.javac.code.parameter;

import com.bigbade.processorcodeapi.api.code.IAnnotation;
import com.bigbade.processorcodeapi.api.code.IClassType;
import com.bigbade.processorcodeapi.api.code.parameter.IGeneric;
import com.bigbade.processorcodeapi.javac.code.JavacVersion;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.bigbade.processorcodeapi.javac.utils.ListUtils;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class JavacGeneric implements IGeneric, JavacVersion<JCTree.JCTypeParameter> {
    private final String identifier;

    private final List<IClassType> boundClass;
    private final List<IAnnotation> annotation;

    @Override
    public String letter() {
        return identifier;
    }

    @Override
    public List<IClassType> boundClasses() {
        return boundClass;
    }

    @Override
    public JCTree.JCTypeParameter getExpression(JavacInternals internals) {
        TreeMaker treeMaker = internals.getTreeMaker();
        return treeMaker.TypeParameter(internals.getNames().fromString(identifier),
                ListUtils.convertJavacVersions(internals, boundClass),
                ListUtils.convertJavacVersions(internals, annotation));
    }
}
