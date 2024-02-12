package com.epam.rd.autocode.spring.project.controller.error;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ErrorHandlerTest {

    @Test
    @DisplayName("Error Handler is annotated with ControllerAdvice")
    public void testAnnotationControllerAdviceExistence() {
        assertTrue(ErrorHandler.class.isAnnotationPresent(ControllerAdvice.class),
                String.format("%s should have annotation : [@ControllerAdvice]", ErrorHandler.class.getSimpleName()));
    }

    @Test
    @DisplayName("Error Handler has some amount of basic exceptionHandler's methods")
    public void testMethodsExistence() {
        assertNotEquals(0, ErrorHandler.class.getDeclaredMethods().length,
                "Error handler should have methods to catch errors");

        assertTrue(Arrays.stream(ErrorHandler.class.getDeclaredMethods())
                .allMatch(method -> method.isAnnotationPresent(ExceptionHandler.class)), "Some methods miss annotation @ExceptionHandler");
    }

}
