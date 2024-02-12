package com.epam.rd.autocode.spring.project.controller.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DTOAnnotationsTest {
    private static Stream<Arguments> casesForComponent() {
        return Stream.of(
                Arguments.of(BookDTO.class),
                Arguments.of(OrderDTO.class),
                Arguments.of(EmployeeDTO.class),
                Arguments.of(ClientDTO.class),
                Arguments.of(BookItemDTO.class),
                Arguments.of(ClientOrderDTO.class),
                Arguments.of(EmployeeOrderDTO.class)
        );
    }

    @ParameterizedTest
    @DisplayName("Annotation [Component] exist within the scope of each class")
    @MethodSource("casesForComponent")
    void testEntity(Class<?> clazz) {
        assertTrue(clazz.isAnnotationPresent(Component.class),
                String.format("Class [%s]. [@Component] is missed.", clazz.getSimpleName()));
    }
}
