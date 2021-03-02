package com.bigbade.processorcodeapi.api;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import java.util.List;

/**
 * Contains (per-compiler) utility methods meant to make using types and elements easier by providing methods
 * not found in the default processor API.
 */
@SuppressWarnings("unused")
public interface TypeUtils {
    /**
     * Compares two elements
     * @param testing Element to test
     * @param target Element that should match the tested element
     * @return True if testing is of type target, false if not
     */
    boolean isType(Element testing, Element target);

    /**
     * Compares two types
     * @param testing Type to test
     * @param target Type that should match the tested type
     * @return True if testing is of type target, false if not
     */
    boolean isType(TypeMirror testing, TypeMirror target);

    /**
     * Gets the element from the TypeMirror
     * @param mirror Mirror to get type of
     * @return Element of mirror
     */
    Element getElement(TypeMirror mirror);

    /**
     * Compares two elements
     * @param first First element
     * @param second Second element
     * @return True if first is of the exact same type is second
     */
    boolean typeEquals(Element first, Element second);

    /**
     * Compares two types
     * @param first First type
     * @param second Second type
     * @return True if first is of the exact same type is second
     */
    boolean typeEquals(TypeMirror first, TypeMirror second);

    /**
     * Gets superclasses/superinterfaces of the element
     * @param element Element to get superclasses of
     * @return List of all superclasses and superinterfaces of the element, including the element
     */
    List<TypeMirror> getSuperclasses(Element element);


    /**
     * Gets superclasses/superinterfaces of the type
     * @param type Type to get superclasses of
     * @return List of all superclasses and superinterfaces of the type, including the type
     */
    List<TypeMirror> getSuperclasses(TypeMirror type);
}

