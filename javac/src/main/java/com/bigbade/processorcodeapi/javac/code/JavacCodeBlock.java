package com.bigbade.processorcodeapi.javac.code;

import com.bigbade.processorcodeapi.api.code.ICodeBlock;
import com.bigbade.processorcodeapi.api.statements.IBasicStatement;
import com.bigbade.processorcodeapi.javac.statements.IJavacStatement;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.bigbade.processorcodeapi.javac.utils.ListUtils;
import com.sun.tools.javac.tree.JCTree;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class JavacCodeBlock implements ICodeBlock {
    private final List<JCTree.JCStatement> statements;
    private final JavacInternals internals;

    public JavacCodeBlock(JavacInternals internals) {
        this.internals = internals;
        statements = new ArrayList<>();
    }

    public JavacCodeBlock(JavacInternals internals, JCTree.JCBlock block) {
        this.internals = internals;
        statements = new ArrayList<>(block.getStatements());
    }

    public void addTopStatement(IBasicStatement statement) {
        statements.add(0, ((IJavacStatement<?>) statement).getExpression(internals));
    }

    public void addStatement(IBasicStatement statement) {
        statements.add(((IJavacStatement<?>) statement).getExpression(internals));
    }

    public JCTree.JCBlock getBlock() {
        return internals.getTreeMaker().Block(Modifier.PUBLIC, ListUtils.convertList(statements));
    }
}
