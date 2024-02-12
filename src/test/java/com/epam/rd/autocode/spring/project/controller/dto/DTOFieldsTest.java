package com.epam.rd.autocode.spring.project.controller.dto;

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

public class DTOFieldsTest {

    private static Stream<Arguments> dtoFieldsSourceStream() {
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
    @DisplayName("Fields exist within the scope of each class")
    @MethodSource("dtoFieldsSourceStream")
    void testClassesFields(Class<?> clazz, int size) {
        assertEquals(size, clazz.getDeclaredFields().length,
                String.format("Class [%s]. Some fields are missed or there are more than expected.", clazz.getSimpleName()));
    }

    @ParameterizedTest
    @DisplayName("Fields have private access within the scope of each class")
    @ValueSource(classes = {BookDTO.class, OrderDTO.class, ClientDTO.class, EmployeeDTO.class, BookItem.class, EmployeeOrderDTO.class, ClientOrderDTO.class})
    void testFieldsPrivacy(Class<?> clazz) {
        assertTrue(Arrays.stream(clazz.getDeclaredFields())
                        .allMatch(field -> Modifier.isPrivate(field.getModifiers())),
                String.format("Class [%s]. Some fields are not private", clazz.getSimpleName()));
    }
}
