package com.epam.rd.autocode.spring.project.service;

import com.epam.rd.autocode.spring.project.service.base.*;
import com.epam.rd.autocode.spring.project.service.impl.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServiceRelationTest {

    private static Stream<Arguments> casesForServices() {
        return Stream.of(
                Arguments.of(BookService.class, BookServiceImpl.class),
                Arguments.of(BookOrderService.class, BookOrderServiceImpl.class),
                Arguments.of(OrderService.class, OrderServiceImpl.class),
                Arguments.of(ClientService.class, ClientServiceImpl.class),
                Arguments.of(EmployeeService.class, EmployeeServiceImpl.class)
        );
    }

    @ParameterizedTest
    @MethodSource("casesForServices")
    @DisplayName("All the classes implements their relative interfaces")
    public void testServiceImplementation(Class<?> interfaze, Class<?> clazz) {
        assertTrue(Arrays.stream(clazz.getInterfaces()).anyMatch(var -> var == interfaze),
                String.format("%s should implements %s", clazz.getSimpleName(), interfaze.getSimpleName()));
    }
}
