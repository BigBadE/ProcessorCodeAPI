package com.bigbade.minecraftplugindevelopment.javac.code.parameter;

import com.bigbade.minecraftplugindevelopment.api.code.IAnnotation;
import com.bigbade.minecraftplugindevelopment.api.code.parameter.IParameter;
import com.bigbade.minecraftplugindevelopment.api.code.parameter.IParameterType;
import com.bigbade.minecraftplugindevelopment.javac.code.JavacVersion;
import com.bigbade.minecraftplugindevelopment.javac.utils.JavacInternals;
import com.bigbade.minecraftplugindevelopment.javac.utils.ListUtils;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import lombok.Getter;

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
        return treeMaker.VarDef(internals.getTreeMaker().Modifiers(0,
                ListUtils.convertJavacVersions(internals, (JavacVersion<JCTree.JCAnnotation>[]) annotations)),
                internals.getNames().fromString(name), treeMaker.Ident(
                        internals.getNames().fromString(type.getSimpleName())),
                null);
    }
}
