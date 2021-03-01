package com.bigbade.processorcodeapi.javac.statements;

import com.bigbade.processorcodeapi.api.statements.IBasicStatement;
import com.bigbade.processorcodeapi.javac.code.JavacVersion;
import com.sun.tools.javac.tree.JCTree;

public interface IJavacStatement<T extends JCTree.JCStatement> extends IBasicStatement, JavacVersion<T> {

}
