package com.epam.rd.autocode.spring.project.service.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionRelationTest {
    @Test
    @DisplayName("AlreadyExistException and NotFoundException extends RuntimeException")
    void testExtends() {
        assertEquals(RuntimeException.class, AlreadyExistException.class.getSuperclass(),
                "AlreadyExistException should extends RuntimeException");
        assertEquals(RuntimeException.class, NotFoundException.class.getSuperclass(),
                "NotFoundException should extends RuntimeException");
    }
}
