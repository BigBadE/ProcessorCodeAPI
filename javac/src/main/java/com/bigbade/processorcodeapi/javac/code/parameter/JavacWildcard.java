package com.bigbade.processorcodeapi.javac.code.parameter;

import com.bigbade.processorcodeapi.api.code.GenericConstraint;
import com.bigbade.processorcodeapi.api.code.IClassType;
import com.bigbade.processorcodeapi.api.code.IWildcard;
import com.bigbade.processorcodeapi.javac.code.JavacClassType;
import com.bigbade.processorcodeapi.javac.code.JavacVersion;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.sun.tools.javac.code.BoundKind;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import lombok.Getter;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class JavacWildcard implements IWildcard, JavacVersion<JCTree.JCWildcard> {
    @Getter
    private final GenericConstraint constraint;
    @Getter
    private final IClassType classBound;

    public JavacWildcard() {
        this(null, GenericConstraint.UNBOUND);
    }

    public JavacWildcard(@Nullable IClassType bound) {
        this(bound, GenericConstraint.UNBOUND);
    }

    public JavacWildcard(@Nullable IClassType bound, GenericConstraint constraint) {
        this.classBound = bound;
        this.constraint = constraint;
    }

    @Override
    public JCTree.JCWildcard getExpression(JavacInternals internals) {
        TreeMaker treeMaker = internals.getTreeMaker();
        return treeMaker.Wildcard(treeMaker.TypeBoundKind(BoundKind.values()[constraint.ordinal()]),
                classBound == null ? null : ((JavacClassType) classBound).getExpression(internals));
    }

    @Override
    public List<IClassType> getClassBounds() {
        return Collections.singletonList(classBound);
    }
}
