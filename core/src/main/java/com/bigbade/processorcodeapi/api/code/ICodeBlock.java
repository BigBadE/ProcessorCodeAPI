package com.bigbade.processorcodeapi.api.code;

import com.bigbade.processorcodeapi.api.statements.IBasicStatement;

public interface ICodeBlock {
    void addStatement(IBasicStatement statement);
}
