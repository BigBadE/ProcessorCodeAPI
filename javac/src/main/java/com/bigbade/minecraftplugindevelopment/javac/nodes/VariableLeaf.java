package com.bigbade.minecraftplugindevelopment.javac.nodes;

import com.bigbade.minecraftplugindevelopment.api.leaf.IVariableLeaf;
import com.bigbade.minecraftplugindevelopment.javac.utils.JavacInternals;
import com.sun.tools.javac.tree.JCTree;
import lombok.Getter;

import javax.lang.model.element.VariableElement;

@SuppressWarnings("unused")
public class VariableLeaf implements IVariableLeaf {
    @Getter
    private final VariableElement variableElement;

    private final JCTree.JCVariableDecl variableDecl;
    private final JavacInternals internals;

    public VariableLeaf(VariableElement element, JavacInternals internals) {
        this.variableElement = element;
        this.internals = internals;
        variableDecl = (JCTree.JCVariableDecl) internals.getTrees().getTree(element);
    }
}
