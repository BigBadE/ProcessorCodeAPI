package com.bigbade.processorcodeapi.javac.utils;

import com.bigbade.processorcodeapi.api.TypeUtils;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Type;
import lombok.RequiredArgsConstructor;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class JavacTypeUtils implements TypeUtils {
    @Override
    public boolean isType(Element testing, Element target) {
        return isType(testing.asType(), target.asType());
    }

    @Override
    public boolean isType(TypeMirror testing, TypeMirror target) {
        for (TypeMirror superClass : getSuperclasses(testing)) {
            if (typeEquals(target, superClass)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Element getElement(TypeMirror mirror) {
        return ((Type) mirror).asElement();
    }

    @Override
    public boolean typeEquals(Element first, Element second) {
        return ((Symbol.TypeSymbol) first).getQualifiedName().equals(((Symbol.TypeSymbol) second).getQualifiedName());
    }

    @Override
    public boolean typeEquals(TypeMirror first, TypeMirror second) {
        return typeEquals(((Type) first).asElement(), ((Type) second).asElement());
    }

    @Override
    public List<TypeMirror> getSuperclasses(Element element) {
        return getSuperclasses(element.asType());
    }

    @Override
    public List<TypeMirror> getSuperclasses(TypeMirror type) {
        List<TypeMirror> elements = new ArrayList<>();
        Type superClass = (Type) type;
        while (superClass.asElement() != null) {
            //Generics have the lowest bounds as a type.
            if(superClass instanceof Type.TypeVar) {
                superClass = superClass.getLowerBound();
            }
            elements.add(superClass);
            TypeElement typeElement = (TypeElement) superClass.asElement();
            for(TypeMirror interfaceType : typeElement.getInterfaces()) {
                elements.addAll(getSuperclasses(interfaceType));
            }
            superClass = (Type) typeElement.getSuperclass();
        }
        return elements;
    }
}
