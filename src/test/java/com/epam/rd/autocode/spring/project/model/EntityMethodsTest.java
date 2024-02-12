package com.epam.rd.autocode.spring.project.model;

import com.epam.rd.autocode.spring.project.tools.MethodChecker;
import com.epam.rd.autocode.spring.project.model.sub.BookItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityMethodsTest {
    @ParameterizedTest
    @DisplayName("Constructors exist within the scope of each class")
    @ValueSource(classes = {Book.class, Order.class, User.class, Employee.class, Client.class, BookItem.class})
    void testClassesConstructors(Class<?> clazz) {
        assertEquals(2, clazz.getDeclaredConstructors().length,
                String.format("Class [%s]. Some constructors are missed or there are more than expected.", clazz.getSimpleName()));
    }

    private static Stream<Arguments> entityMethodsSourceStream() {
        return Stream.of(
                Arguments.of(Book.class, 11),
                Arguments.of(Order.class, 6),
                Arguments.of(User.class, 4),
                Arguments.of(Employee.class, 2),
                Arguments.of(Client.class, 1),
                Arguments.of(BookItem.class, 4)
        );
    }

    @ParameterizedTest
    @DisplayName("Getters exist within the scope of each class")
    @MethodSource("entityMethodsSourceStream")
    void testClassesGetters(Class<?> clazz, int size) {
        assertEquals(size, Arrays.stream(clazz.getDeclaredMethods())
                        .filter(MethodChecker::isMethodGetter)
                        .count(),
                String.format("Class [%s]. Some getters are missed or there are more than expected.", clazz.getSimpleName()));
    }

    @ParameterizedTest
    @DisplayName("Setters exist within the scope of each class")
    @MethodSource("entityMethodsSourceStream")
    void testClassesSetters(Class<?> clazz, int size) {
        assertEquals(size, Arrays.stream(clazz.getDeclaredMethods())
                        .filter(MethodChecker::isMethodSetter)
                        .count(),
                String.format("Class [%s]. Some setters are missed or there are more than expected.", clazz.getSimpleName()));
    }
}
