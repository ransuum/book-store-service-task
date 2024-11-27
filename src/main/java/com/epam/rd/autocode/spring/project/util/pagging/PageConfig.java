package com.epam.rd.autocode.spring.project.util.pagging;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface PageConfig<E> {
    Map<String, Object> response(Page<E> pages);
}
