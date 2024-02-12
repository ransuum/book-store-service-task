package com.epam.rd.autocode.spring.project.model;

import com.epam.rd.autocode.spring.project.model.enums.AgeGroup;
import com.epam.rd.autocode.spring.project.model.enums.Language;
import com.epam.rd.autocode.spring.project.model.sub.BookItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.Serializable;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntityRelationTest {
    @Test
    @DisplayName("Client and Employee extends User")
    void testExtends() {
        assertEquals(User.class, Client.class.getSuperclass(),
                "Client should extends User");
        assertEquals(User.class, Employee.class.getSuperclass(),
                "Employee should extends User");
    }

    private static Stream<Arguments> casesForEntities() {
        return Stream.of(
                Arguments.of(Client.class),
                Arguments.of(Employee.class),
                Arguments.of(Book.class),
                Arguments.of(Order.class),
                Arguments.of(User.class),
                Arguments.of(BookItem.class),
                Arguments.of(AgeGroup.class),
                Arguments.of(Language.class)
        );
    }

    @ParameterizedTest
    @MethodSource("casesForEntities")
    @DisplayName("All the classes implements Serializable")
    void testImplements(Class<?> classes) {
        assertTrue(Arrays.stream(classes.getInterfaces()).anyMatch(i -> i == Serializable.class),
                classes.getSimpleName() + " should implements Serializable");
    }
}
