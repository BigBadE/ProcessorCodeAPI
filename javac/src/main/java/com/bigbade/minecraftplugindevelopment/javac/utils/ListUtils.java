package com.bigbade.minecraftplugindevelopment.javac.utils;

import com.bigbade.minecraftplugindevelopment.javac.code.JavacVersion;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.List;

import java.util.ArrayList;

public final class ListUtils {
    private ListUtils() {}

    /**
     * Adds object to a sun internal list
     * @param list Original list
     * @param adding Objects to add
     * @param <T> Type of list/object
     * @return New list with added object
     */
    @SafeVarargs
    public static <T> List<T> addObjectToSunList(List<T> list, T... adding) {
        for(T object : adding) {
            list = list.append(object);
        }
        return list;
    }

    /**
     * Adds object to the start of a sun internal list
     * @param <T> Type of list/object
     * @param list Original list
     * @param adding Objects to add
     * @return New list with added object
     */
    @SafeVarargs
    public static <T> List<T> prependObjectToSunList(List<T> list, T... adding) {
        for(T object : adding) {
            list = list.prepend(object);
        }
        return list;
    }


    /**
     * Removes object to the start of a sun internal list
     * @param <T> Type of list/object
     * @param list Original list
     * @param removing Objects to remove
     * @return New list with added object
     */
    @SafeVarargs
    public static <T> List<T> removeObjectFromSunList(List<T> list, T... removing) {
        java.util.List<T> temp = new ArrayList<>(list);
        for(T object : removing) {
            temp.remove(object);
        }
        return convertList(temp);
    }

    /**
     * Gets the list of Types from a list of JCTrees
     * @param trees Trees to get types of
     * @return Types of those trees
     */
    public static List<Type> getTypes(java.util.List<? extends JCTree> trees) {
        java.util.List<Type> output = new ArrayList<>();
        for (JCTree tree : trees) {
            output.add(tree.type);
        }
        return ListUtils.convertList(output);
    }

    /**
     * Converts a normal list to a sun internal list
     * @param converting List to convert
     * @param <T> Type of list
     * @return New sun list
     */
    public static <T> List<T> convertList(java.util.List<T> converting) {
        return List.from(converting);
    }

    /**
     * Converts a list of JavacVersions
     * @param internals Internals
     * @param converting List of JavacVersions to convert
     * @param <T> JCTree type of list/JavacVersion
     * @return Converted list
     * @see JavacVersion
     */
    @SuppressWarnings("unchecked")
    public static <T extends JCTree> List<T> convertJavacVersions(JavacInternals internals,
                                                                  java.util.List<?> converting) {
        java.util.List<T> found = new ArrayList<>();
        for(JavacVersion<T> version : (List<JavacVersion<T>>) converting) {
            found.add(version.getExpression(internals));
        }
        return convertList(found);
    }

    /**
     * Converts a list of JavacVersions
     * @param internals Internals
     * @param converting List of JavacVersions to convert
     * @param <T> JCTree type of list/JavacVersion
     * @return Converted list
     * @see JavacVersion
     */
    public static <T extends JCTree> List<T> convertJavacVersions(JavacInternals internals,
                                                                  JavacVersion<? extends T>[] converting) {
        java.util.List<T> found = new ArrayList<>();
        for(JavacVersion<? extends T> version : converting) {
            found.add(version.getExpression(internals));
        }
        return convertList(found);
    }
}
