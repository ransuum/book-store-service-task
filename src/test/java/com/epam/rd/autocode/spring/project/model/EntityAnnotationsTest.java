package com.epam.rd.autocode.spring.project.model;

import com.epam.rd.autocode.spring.project.model.sub.BookItem;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntityAnnotationsTest {
    private static Stream<Arguments> casesForEntity() {
        return Stream.of(
                Arguments.of(Book.class),
                Arguments.of(Order.class),
                Arguments.of(Employee.class),
                Arguments.of(Client.class),
                Arguments.of(BookItem.class)
        );
    }

    private static Stream<Arguments> casesForId() {
        return Stream.of(
                Arguments.of(Book.class),
                Arguments.of(Order.class),
                Arguments.of(User.class),
                Arguments.of(BookItem.class)
        );
    }


    @ParameterizedTest
    @DisplayName("Annotation [Entity] exist within the scope of each class")
    @MethodSource("casesForEntity")
    void testEntity(Class<?> clazz) {
        assertTrue(clazz.isAnnotationPresent(Entity.class),
                String.format("Class [%s]. [@Entity] is missed.", clazz.getSimpleName()));
    }

    @ParameterizedTest
    @DisplayName("Annotation [Id] exist within the scope of each class")
    @MethodSource("casesForId")
    void testId(Class<?> clazz) {
        assertTrue(Arrays.stream(clazz.getDeclaredFields())
                        .anyMatch((val) -> val.isAnnotationPresent(Id.class)),
                String.format("Class [%s]. [@Id] is missed.", clazz.getSimpleName()));
    }
}
