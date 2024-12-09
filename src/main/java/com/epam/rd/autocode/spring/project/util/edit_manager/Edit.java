package com.epam.rd.autocode.spring.project.util.edit_manager;

public interface Edit<T, D> {
    D edit(String email, T t);
}
