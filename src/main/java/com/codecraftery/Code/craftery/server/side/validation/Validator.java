package com.codecraftery.Code.craftery.server.side.validation;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

/**
 * @author Natasa Todorov Markovic
 */
public interface Validator<T> {

    Set<ConstraintViolation<T>> validate(T object);
}
