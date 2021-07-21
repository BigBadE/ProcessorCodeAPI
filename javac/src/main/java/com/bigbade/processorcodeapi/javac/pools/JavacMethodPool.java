package com.bigbade.processorcodeapi.javac.pools;

import com.bigbade.processorcodeapi.api.pools.MethodPool;
import com.bigbade.processorcodeapi.javac.nodes.MethodNode;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.sun.tools.javac.tree.JCTree;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;

/**
 * A pool of all methods, used to allow changes be reflected across processors
 */
public class JavacMethodPool extends MethodPool<MethodNode> {
    private final JavacInternals internals;

    public JavacMethodPool(JCTree.JCClassDecl classDecl, JavacInternals internals) {
        this.internals = internals;
        for(Element element : classDecl.sym.getEnclosedElements()) {
            if(!(element instanceof ExecutableElement)) {
                continue;
            }
            instantiate((ExecutableElement) element);
        }
    }
    /**
     * @param key Key used to instantiate the value
     * @return MethodNode internal node created from the element
     */
    @Override
    protected MethodNode instantiate(ExecutableElement key) {
        return new MethodNode(key, internals);
    }
}
