package com.bigbade.processorcodeapi.api.code;

/**
 * All operations that can be done on two primitives.
 * Non-bitwise OR and AND, EQUAL, and NOT_EQUAL are applicable to booleans.
 * OR and AND are only applicable to booleans.
 * The rest are only applicable to numbers (chars are treated as numbers).
 *
 * Every operator other than the comparisons (EQUAL, NOT_EQUAL, LESS_THAN, GREATER_THAN, LESS_OR_EQUAL,
 * GREATER_OR_EQUAL) can be used with assignments
 */
public enum BinaryOperation {
    // ||
    OR,
    // &&
    AND,
    // |
    BITWISE_OR,
    // ^
    BITWISE_XOR,
    // &
    BITWISE_AND,
    // ==
    EQUAL,
    // !=
    NOT_EQUAL,
    // <
    LESS_THAN,
    // >
    GREATER_THAN,
    // <=
    LESS_OR_EQUAL,
    // >=
    GREATER_OR_EQUAL,
    // <<
    SHIFT_LEFT,
    // >>
    SHIFT_RIGHT,
    // >>>
    UNSIGNED_SHIFT_RIGHT,
    // +
    PLUS,
    // -
    MINUS,
    // *
    MULTIPLY,
    // /
    DIVIDE,
    // %
    MODULO,
}
