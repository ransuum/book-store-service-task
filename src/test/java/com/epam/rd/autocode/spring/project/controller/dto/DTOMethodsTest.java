package com.epam.rd.autocode.spring.project.controller.dto;

import com.epam.rd.autocode.spring.project.model.sub.BookItem;
import com.epam.rd.autocode.spring.project.tools.MethodChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DTOMethodsTest {
    @ParameterizedTest
    @DisplayName("Constructors exist within the scope of each class")
    @ValueSource(classes = {BookDTO.class, OrderDTO.class, ClientDTO.class, EmployeeDTO.class, BookItem.class, EmployeeOrderDTO.class, ClientOrderDTO.class})
    void testClassesConstructors(Class<?> clazz) {
        assertEquals(2, clazz.getDeclaredConstructors().length,
                String.format("Class [%s]. Some constructors are missed or there are more than expected.", clazz.getSimpleName()));
    }

    private static Stream<Arguments> dtoMethodsSourceStream() {
        return Stream.of(
                Arguments.of(BookDTO.class, 11),
                Arguments.of(EmployeeDTO.class, 6),
                Arguments.of(ClientDTO.class, 5),
                Arguments.of(BookItemDTO.class, 2),
                Arguments.of(OrderDTO.class, 6),
                Arguments.of(ClientOrderDTO.class, 2),
                Arguments.of(EmployeeOrderDTO.class, 2)
        );
    }

    @ParameterizedTest
    @DisplayName("Getters exist within the scope of each class")
    @MethodSource("dtoMethodsSourceStream")
    void testClassesGetters(Class<?> clazz, int size) {
        assertEquals(size, Arrays.stream(clazz.getDeclaredMethods())
                        .filter(MethodChecker::isMethodGetter)
                        .count(),
                String.format("Class [%s]. Some getters are missed or there are more than expected.", clazz.getSimpleName()));
    }

    @ParameterizedTest
    @DisplayName("Setters exist within the scope of each class")
    @MethodSource("dtoMethodsSourceStream")
    void testClassesSetters(Class<?> clazz, int size) {
        assertEquals(size, Arrays.stream(clazz.getDeclaredMethods())
                        .filter(MethodChecker::isMethodSetter)
                        .count(),
                String.format("Class [%s]. Some setters are missed or there are more than expected.", clazz.getSimpleName()));
    }
}
