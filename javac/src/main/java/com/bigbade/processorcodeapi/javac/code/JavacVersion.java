package com.bigbade.processorcodeapi.javac.code;

import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.sun.tools.javac.tree.JCTree;

public interface JavacVersion<T extends JCTree> {
    T getExpression(JavacInternals internals);
}
