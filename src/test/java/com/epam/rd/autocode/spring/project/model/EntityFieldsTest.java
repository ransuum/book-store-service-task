package com.epam.rd.autocode.spring.project.model;

import com.epam.rd.autocode.spring.project.model.sub.BookItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntityFieldsTest {

    private static Stream<Arguments> entityFieldsSourceStream() {
        return Stream.of(
                Arguments.of(Book.class, 11),
                Arguments.of(User.class, 4),
                Arguments.of(Employee.class, 2),
                Arguments.of(Client.class, 1),
                Arguments.of(BookItem.class, 4),
                Arguments.of(Order.class, 6)
        );
    }

    @ParameterizedTest
    @DisplayName("Fields exist within the scope of each class")
    @MethodSource("entityFieldsSourceStream")
    void testClassesFields(Class<?> clazz, int size) {
        assertEquals(size, clazz.getDeclaredFields().length,
                String.format("Class [%s]. Some fields are missed or there are more than expected.", clazz.getSimpleName()));
    }

    @ParameterizedTest
    @DisplayName("Fields have private access within the scope of each class")
    @ValueSource(classes = {Book.class, Order.class, User.class, Employee.class, Client.class, BookItem.class})
    void testFieldsPrivacy(Class<?> clazz) {
        assertTrue(Arrays.stream(clazz.getDeclaredFields())
                        .allMatch(field -> Modifier.isPrivate(field.getModifiers())),
                String.format("Class [%s]. Some fields are not private", clazz.getSimpleName()));
    }
}
