package com.bigbade.processorcodeapi.javac.nodes;

import com.bigbade.processorcodeapi.api.nodes.IMethodNode;
import com.bigbade.processorcodeapi.api.pools.VariablePool;
import com.bigbade.processorcodeapi.javac.code.JavacCodeBlock;
import com.bigbade.processorcodeapi.javac.pools.JavacVariablePool;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.bigbade.processorcodeapi.javac.pools.JavacMethodPool;
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
    @Getter
    private final JavacCodeBlock codeBlock;

    private final JCTree.JCMethodDecl methodDecl;
    private final JavacInternals internals;

    /**
     * Instantiates a method node from an element and internals.
     * This should only be done through the method pool
     * @param element Element representing the method
     * @param internals Javac internals bundle
     * @see JavacMethodPool
     */
    public MethodNode(ExecutableElement element, JavacInternals internals) {
        this.methodElement = element;
        this.internals = internals;
        methodDecl = (JCTree.JCMethodDecl) internals.getTrees().getTree(element);
        codeBlock = new JavacCodeBlock(internals, methodDecl.body);
        variablePool = new JavacVariablePool(internals);
    }
}
