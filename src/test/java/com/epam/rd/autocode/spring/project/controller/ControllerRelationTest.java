package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.controller.base.*;
import com.epam.rd.autocode.spring.project.controller.impl.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ControllerRelationTest {

    private static Stream<Arguments> casesForControllers() {
        return Stream.of(
                Arguments.of(BookController.class, BookControllerImpl.class),
                Arguments.of(OrderCartController.class, OrderCartControllerImpl.class),
                Arguments.of(OrderController.class, OrderControllerImpl.class),
                Arguments.of(ClientController.class, ClientControllerImpl.class),
                Arguments.of(EmployeeController.class, EmployeeControllerImpl.class),
                Arguments.of(HomeController.class, HomeControllerImpl.class)
        );
    }

    @ParameterizedTest
    @MethodSource("casesForControllers")
    @DisplayName("All the classes implements their relative interfaces")
    public void testServiceImplementation(Class<?> interfaze, Class<?> clazz) {
        assertTrue(Arrays.stream(clazz.getInterfaces()).anyMatch(var -> var == interfaze),
                String.format("%s should implements %s", clazz.getSimpleName(), interfaze.getSimpleName()));
    }
}
