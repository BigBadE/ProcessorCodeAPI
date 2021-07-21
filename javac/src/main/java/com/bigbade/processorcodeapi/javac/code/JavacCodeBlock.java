package com.bigbade.processorcodeapi.javac.code;

import com.bigbade.processorcodeapi.api.code.ICodeBlock;
import com.bigbade.processorcodeapi.api.statements.IBasicStatement;
import com.bigbade.processorcodeapi.javac.statements.IJavacStatement;
import com.bigbade.processorcodeapi.javac.utils.InternalWrapperCreator;
import com.bigbade.processorcodeapi.javac.utils.JavacInternals;
import com.bigbade.processorcodeapi.javac.utils.ListUtils;
import com.sun.tools.javac.tree.JCTree;

import javax.annotation.Nullable;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class JavacCodeBlock implements ICodeBlock, IJavacStatement<JCTree.JCBlock> {
    //Basic cache, only populated when needed
    private final List<IBasicStatement> wrappedStatements = new ArrayList<>();

    private final List<JCTree.JCStatement> statements;
    private final JavacInternals internals;

    public JavacCodeBlock(JavacInternals internals) {
        this.internals = internals;
        statements = new ArrayList<>();
    }

    public JavacCodeBlock(JavacInternals internals, JCTree.JCBlock block) {
        this.internals = internals;
        statements = new ArrayList<>(block.getStatements());
    }

    public void addTopStatement(IBasicStatement statement) {
        statements.add(0, ((IJavacStatement<?>) statement).getExpression(internals));
        if (!wrappedStatements.isEmpty()) {
            wrappedStatements.add(0, statement);
        }
    }

    @Override
    public void addStatement(IBasicStatement statement) {
        statements.add(((IJavacStatement<?>) statement).getExpression(internals));
        if (!wrappedStatements.isEmpty()) {
            wrappedStatements.add(statement);
        }
    }

    @Override
    public void addStatement(int index, IBasicStatement statement) {
        statements.add(0, ((IJavacStatement<?>) statement).getExpression(internals));
        if (!wrappedStatements.isEmpty()) {
            wrappedStatements.add(0, statement);
        }
    }

    @Override
    public void replaceStatement(int index, IBasicStatement statement) {
        statements.set(0, ((IJavacStatement<?>) statement).getExpression(internals));
        if (!wrappedStatements.isEmpty()) {
            wrappedStatements.set(0, statement);
        }
    }

    @Override
    public IBasicStatement getStatement(int index) {
        if (wrappedStatements.isEmpty()) {
            for (JCTree.JCStatement converting : statements) {
                wrappedStatements.add(InternalWrapperCreator.getStatementFromClass(internals, converting));
            }
        }
        return wrappedStatements.get(index);
    }

    @Override
    public List<Integer> getStatementIndexes(Class<? extends IBasicStatement> statement,
                                             @Nullable Predicate<IBasicStatement> verifier) {
        List<Integer> abiding = new ArrayList<>();
        if (!wrappedStatements.isEmpty()) {
            for (int i = 0; i < wrappedStatements.size(); i++) {
                IBasicStatement found = wrappedStatements.get(i);
                if (statement.equals(found.getClass()) && (verifier == null || verifier.test(found))) {
                    abiding.add(i);
                }
            }
        } else {
            for (int i = 1; i < statements.size(); i++) {
                IBasicStatement found = InternalWrapperCreator.getStatementFromClass(internals, statements.get(i));
                wrappedStatements.add(found);
                if (statement.equals(found.getClass()) && (verifier == null || verifier.test(found))) {
                    abiding.add(i);
                }
            }
        }
        return abiding;
    }

    //Types are checked by the if statement
    @SuppressWarnings("unchecked")
    @Override
    public <T extends IBasicStatement> List<T> getStatements(Class<T> statement,
                                                             @Nullable Predicate<T> verifier) {
        List<T> abiding = new ArrayList<>();
        if (!wrappedStatements.isEmpty()) {
            for (IBasicStatement found : wrappedStatements) {
                if (statement.equals(found.getClass()) && (verifier == null || verifier.test((T) found))) {
                    abiding.add((T) found);
                }
            }
        } else {
            for (JCTree.JCStatement foundStatement : statements) {
                IBasicStatement found = InternalWrapperCreator.getStatementFromClass(internals, foundStatement);
                wrappedStatements.add(found);
                if (statement.equals(found.getClass()) && (verifier == null || verifier.test((T) found))) {
                    abiding.add((T) found);
                }
            }
        }
        return abiding;
    }

    //Types are checked by the if statement
    @SuppressWarnings("unchecked")
    @Override
    public <T extends IBasicStatement> Optional<T> getStatement(Class<T> statement,
                                                                @Nullable Predicate<T> verifier) {
        if (!wrappedStatements.isEmpty()) {
            for (IBasicStatement found : wrappedStatements) {
                if (statement.equals(found.getClass()) && (verifier == null || verifier.test((T) found))) {
                    return Optional.of((T) found);
                }
            }
        } else {
            for (JCTree.JCStatement foundStatement : statements) {
                IBasicStatement found = InternalWrapperCreator.getStatementFromClass(internals, foundStatement);
                if (statement.equals(found.getClass()) && (verifier == null || verifier.test((T) found))) {
                    return Optional.of((T) found);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public JCTree.JCBlock getExpression(JavacInternals internals) {
        return internals.getTreeMaker().Block(Modifier.PUBLIC, ListUtils.convertList(statements));
    }
}
