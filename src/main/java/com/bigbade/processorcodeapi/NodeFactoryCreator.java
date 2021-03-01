package com.bigbade.processorcodeapi;

import com.bigbade.processorcodeapi.api.factories.INodeFactory;
import com.bigbade.processorcodeapi.eclipse.EclipseNodeFactory;
import com.bigbade.processorcodeapi.javac.factories.JavacNodeFactory;

import javax.annotation.processing.ProcessingEnvironment;

@SuppressWarnings("unused")
public final class NodeFactoryCreator {
    private static INodeFactory factory = null;

    public static INodeFactory getNodeFactory(ProcessingEnvironment environment) {
        if(factory != null) {
            return factory;
        }
        if (JavacNodeFactory.isCorrectType(environment)) {
            factory = new JavacNodeFactory(environment);
        } else if (EclipseNodeFactory.isCorrectType(environment)) {
            factory = new EclipseNodeFactory(environment);
        } else {
            throw new IllegalStateException("Could not find api for processing class: "
                    + environment.getClass().getName() + " (compatibility error)");
        }
        return factory;
    }

    private NodeFactoryCreator() {}
}
