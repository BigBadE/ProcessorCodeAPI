package com.bigbade.processorcodeapi.javac.code.parameter;

import com.bigbade.processorcodeapi.api.code.IAnnotation;
import com.bigbade.processorcodeapi.api.code.parameter.IParameter;
import com.bigbade.processorcodeapi.api.code.parameter.IParameterType;
import com.bigbade.processorcodeapi.javac.code.JavacVersion;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.bigbade.processorcodeapi.javac.utils.ListUtils;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import lombok.Getter;

import java.util.Arrays;

public class Parameter implements IParameter, JavacVersion<JCTree.JCVariableDecl> {
    @Getter
    private final IParameterType type;
    @Getter
    private final String name;

    private final IAnnotation[] annotations;

    public Parameter(IParameterType type, String name, IAnnotation... annotations) {
        this.type = type;
        this.name = name;
        this.annotations = annotations;
    }

    @SuppressWarnings("unchecked")
    @Override
    public JCTree.JCVariableDecl getExpression(JavacInternals internals) {
        TreeMaker treeMaker = internals.getTreeMaker();

        JCTree.JCExpression variableType = treeMaker.Ident(
                internals.getNames().fromString(type.getSimpleName()));
        if(annotations.length > 0) {
            variableType = treeMaker.AnnotatedType(
                    ListUtils.convertJavacVersions(internals, Arrays.asList(annotations)), variableType);
        }
        return treeMaker.VarDef(internals.getTreeMaker().Modifiers(0,
                ListUtils.convertJavacVersions(internals, (JavacVersion<JCTree.JCAnnotation>[]) annotations)),
                internals.getNames().fromString(name), variableType,
                null);
    }
}
