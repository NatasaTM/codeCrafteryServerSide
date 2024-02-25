package com.codecraftery.Code.craftery.server.side.validation;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

public interface Validator<T> {

    Set<ConstraintViolation<T>> validate(T object);
}
