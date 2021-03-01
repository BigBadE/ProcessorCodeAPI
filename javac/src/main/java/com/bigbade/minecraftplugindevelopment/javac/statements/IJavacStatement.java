package com.bigbade.minecraftplugindevelopment.javac.statements;

import com.bigbade.minecraftplugindevelopment.api.statements.IBasicStatement;
import com.bigbade.minecraftplugindevelopment.javac.code.JavacVersion;
import com.sun.tools.javac.tree.JCTree;

public interface IJavacStatement<T extends JCTree.JCStatement> extends IBasicStatement, JavacVersion<T> {

}
