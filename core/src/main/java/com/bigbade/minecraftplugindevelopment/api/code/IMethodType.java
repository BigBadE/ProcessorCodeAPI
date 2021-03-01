package com.bigbade.minecraftplugindevelopment.api.code;

import javax.lang.model.element.ExecutableElement;
import java.util.Optional;

public interface IMethodType {
    String getMethodName();

    Optional<ExecutableElement> getElement();
}
