package com.bigbade.minecraftplugindevelopment.javac.nodes;

import com.bigbade.minecraftplugindevelopment.api.nodes.IMethodNode;
import com.bigbade.minecraftplugindevelopment.api.pools.VariablePool;
import com.bigbade.minecraftplugindevelopment.javac.pools.JavacVariablePool;
import com.bigbade.minecraftplugindevelopment.javac.utils.JavacInternals;
import com.sun.tools.javac.tree.JCTree;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.lang.model.element.ExecutableElement;

@SuppressWarnings("unused")
@RequiredArgsConstructor
public class MethodNode implements IMethodNode {
    private final VariablePool<VariableLeaf> variablePool;

    @Getter
    private final ExecutableElement methodElement;

    private final JCTree.JCMethodDecl methodDecl;
    private final JavacInternals internals;

    /**
     * Instantiates a method node from an element and internals.
     * This should only be done through the method pool
     * @param element Element representing the method
     * @param internals Javac internals bundle
     * @see com.bigbade.minecraftplugindevelopment.javac.pools.JavacMethodPool
     */
    public MethodNode(ExecutableElement element, JavacInternals internals) {
        this.methodElement = element;
        this.internals = internals;
        methodDecl = (JCTree.JCMethodDecl) internals.getTrees().getTree(element);

        variablePool = new JavacVariablePool(internals);
    }
}
