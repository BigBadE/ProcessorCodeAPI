package com.bigbade.processorcodeapi.api.code;

/**
 * All operations applicable to a single primitive.
 * Non-bitwise NOT is only applicable to booleans.
 * The rest are only applicable to numbers (chars are treated as numbers).
 */
public enum UnaryOperations {
    POSITIVE,                        // +var
    NEGATIVE,                        // -var
    NOT,                             // !var
    BITWISE_NOT,                     // ~var
    INCREMENT_AND_GET,               // ++var
    DECREMENT_AND_GET,               // --var
    GET_AND_INCREMENT,               // var++
    GET_AND_DECREMENT,               // var--
}
