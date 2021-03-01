package com.bigbade.minecraftplugindevelopment.api;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import java.util.List;

@SuppressWarnings("unused")
public interface TypeUtils {
    boolean isType(Element testing, Element target);

    boolean isType(TypeMirror testing, TypeMirror target);

    /**
     * Gets the element from the TypeMirror
     * @param mirror Mirror to get type of
     * @return Element of mirror
     */
    Element getElement(TypeMirror mirror);

    boolean typeEquals(Element first, Element second);

    boolean typeEquals(TypeMirror first, TypeMirror second);

    List<TypeMirror> getSuperclasses(Element element);

    List<TypeMirror> getSuperclasses(TypeMirror type);
}

