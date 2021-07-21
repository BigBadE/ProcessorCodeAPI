package com.bigbade.processorcodeapi.javac.utils;

import com.bigbade.processorcodeapi.javac.code.JavacCodeBlock;
import com.bigbade.processorcodeapi.javac.expressions.ArrayGetExpression;
import com.bigbade.processorcodeapi.javac.expressions.AssignmentExpression;
import com.bigbade.processorcodeapi.javac.expressions.AssignmentOperationExpression;
import com.bigbade.processorcodeapi.javac.expressions.BinaryOperationExpression;
import com.bigbade.processorcodeapi.javac.expressions.ConditionalExpression;
import com.bigbade.processorcodeapi.javac.expressions.ErroniusExpression;
import com.bigbade.processorcodeapi.javac.expressions.ExpressionReference;
import com.bigbade.processorcodeapi.javac.expressions.GetVariableExpression;
import com.bigbade.processorcodeapi.javac.expressions.IJavacExpression;
import com.bigbade.processorcodeapi.javac.expressions.LiteralExpression;
import com.bigbade.processorcodeapi.javac.expressions.NewInstanceExpression;
import com.bigbade.processorcodeapi.javac.expressions.SelectVariableExpression;
import com.bigbade.processorcodeapi.javac.statements.CallStatement;
import com.bigbade.processorcodeapi.javac.statements.IJavacStatement;
import com.bigbade.processorcodeapi.javac.statements.IfStatement;
import com.sun.tools.javac.tree.JCTree;

public final class InternalWrapperCreator {
    private InternalWrapperCreator() {}

    //All types are safely checked by the line above the cast
    @SuppressWarnings("unchecked")
    public static <T extends JCTree.JCStatement> IJavacStatement<T> getStatementFromClass(JavacInternals internals, T statement) {
        if (statement instanceof JCTree.JCIf) {
            return (IJavacStatement<T>) new IfStatement(internals, (JCTree.JCIf) statement);
        }
        if(statement instanceof JCTree.JCExpressionStatement) {
            return (IJavacStatement<T>) new CallStatement(internals, (JCTree.JCExpressionStatement) statement);
        }
        if(statement instanceof JCTree.JCBlock) {
            return (IJavacStatement<T>) new JavacCodeBlock(internals, (JCTree.JCBlock) statement);
        }
        throw new IllegalStateException("Unhandled statement " + statement.getClass());
    }

    //All types are safely checked by the line above the cast
    @SuppressWarnings("unchecked")
    public static <T extends JCTree.JCExpression> IJavacExpression<T> getExpressionFromClass(JavacInternals internals, T statement) {
        if (statement instanceof JCTree.JCArrayAccess) {
            return (IJavacExpression<T>) new ArrayGetExpression(internals, (JCTree.JCArrayAccess) statement);
        }
        if (statement instanceof JCTree.JCAssign) {
            return (IJavacExpression<T>) new AssignmentExpression(internals, (JCTree.JCAssign) statement);
        }
        if (statement instanceof JCTree.JCAssignOp) {
            return (IJavacExpression<T>) new AssignmentOperationExpression(internals, (JCTree.JCAssignOp) statement);
        }
        if (statement instanceof JCTree.JCBinary) {
            return (IJavacExpression<T>) new BinaryOperationExpression(internals, (JCTree.JCBinary) statement);
        }
        if (statement instanceof JCTree.JCConditional) {
            return (IJavacExpression<T>) new ConditionalExpression(internals, (JCTree.JCConditional) statement);
        }
        if (statement instanceof JCTree.JCErroneous) {
            return (IJavacExpression<T>) new ErroniusExpression(internals, (JCTree.JCErroneous) statement);
        }
        if (statement instanceof JCTree.JCMethodInvocation) {
            return (IJavacExpression<T>) new ExpressionReference(internals, (JCTree.JCMethodInvocation) statement);
        }
        if (statement instanceof JCTree.JCFieldAccess) {
            return (IJavacExpression<T>) new GetVariableExpression(internals, (JCTree.JCFieldAccess) statement);
        }
        if (statement instanceof JCTree.JCLiteral) {
            return (IJavacExpression<T>) new LiteralExpression<>(((JCTree.JCLiteral) statement).value);
        }
        if (statement instanceof JCTree.JCNewClass) {
            return (IJavacExpression<T>) new NewInstanceExpression(internals, (JCTree.JCNewClass) statement);
        }
        if (statement instanceof JCTree.JCIdent) {
            return (IJavacExpression<T>) new SelectVariableExpression((JCTree.JCIdent) statement);
        }
        throw new IllegalStateException("Unhandled expression " + statement.getClass());
    }
}
