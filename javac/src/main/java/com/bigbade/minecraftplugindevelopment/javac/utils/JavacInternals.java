package com.bigbade.minecraftplugindevelopment.javac.utils;

import com.sun.source.util.Trees;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Names;
import lombok.Getter;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.util.Elements;

/**
 * Class used to pass down needed Javac internals through dependency injection.
 */
@Getter
public class JavacInternals {
    /**
     * TreeMaker is used to instantiate JCTree classes
     */
    private final TreeMaker treeMaker;
    /**
     * Messager is used to send messages to the user
     */
    private final Messager messager;
    /**
     * Trees is used to get internal versions of elements
     * @see javax.lang.model.element.Element
     */
    private final Trees trees;
    /**
     * Names is used to get references to names in the class file from strings.
     */
    private final Names names;
    /**
     * Types is used to fetch types.
     */
    private final Types types;
    /**
     * Elements is used to get Elements from Strings.
     */
    private final Elements elements;

    /**
     * Bundle of internals gotten from the ProcessingEnvironment
     * @param environment Annotation processor environment used to get internals
     * @see JavacProcessingEnvironment
     */
    public JavacInternals(ProcessingEnvironment environment) {
        JavacProcessingEnvironment processingEnvironment = (JavacProcessingEnvironment) environment;
        treeMaker = TreeMaker.instance(processingEnvironment.getContext());
        names = Names.instance(processingEnvironment.getContext());
        messager = environment.getMessager();
        trees = Trees.instance(environment);
        types = Types.instance(processingEnvironment.getContext());
        elements = environment.getElementUtils();
    }
}
