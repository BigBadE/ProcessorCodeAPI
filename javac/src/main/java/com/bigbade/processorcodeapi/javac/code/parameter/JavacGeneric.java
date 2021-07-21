package com.bigbade.processorcodeapi.javac.code.parameter;

import com.bigbade.processorcodeapi.api.code.IAnnotation;
import com.bigbade.processorcodeapi.api.code.IClassType;
import com.bigbade.processorcodeapi.api.code.IGeneric;
import com.bigbade.processorcodeapi.javac.code.JavacVersion;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.bigbade.processorcodeapi.javac.utils.ListUtils;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class JavacGeneric implements IGeneric, JavacVersion<JCTree.JCTypeParameter> {
    @Getter
    private final String letter;
    @Getter
    private final List<IClassType> classBounds;
    private final List<IAnnotation> annotations;

    @Override
    public JCTree.JCTypeParameter getExpression(JavacInternals internals) {
        TreeMaker treeMaker = internals.getTreeMaker();
        return treeMaker.TypeParameter(internals.getNames().fromString(letter),
                ListUtils.convertJavacVersions(internals, classBounds),
                ListUtils.convertJavacVersions(internals, annotations));
    }
}
