package com.epam.rd.autocode.spring.project.util.checking_validator;

public interface Validator<E, D> {
    E validate(E o1, D o2);
}
