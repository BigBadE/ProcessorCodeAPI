package com.bigbade.minecraftplugindevelopment.javac.code;

import com.bigbade.minecraftplugindevelopment.javac.utils.JavacInternals;
import com.sun.tools.javac.tree.JCTree;

public interface JavacVersion<T extends JCTree> {
    T getExpression(JavacInternals internals);
}
