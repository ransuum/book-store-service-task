package com.epam.rd.autocode.spring.project.model;

import com.epam.rd.autocode.spring.project.model.enums.AgeGroup;
import com.epam.rd.autocode.spring.project.model.enums.Language;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnumTest {

    private static Stream<Arguments> casesForLanguage() {
        return Stream.of(
                Arguments.of("ENGLISH"),
                Arguments.of("GERMAN"),
                Arguments.of("FRENCH"),
                Arguments.of("SPANISH"),
                Arguments.of("JAPANESE"),
                Arguments.of("UKRAINIAN"),
                Arguments.of("OTHER")
        );
    }

    @ParameterizedTest
    @DisplayName("Constants of Language tested")
    @MethodSource("casesForLanguage")
    void testLanguageConstants(String expected) {
        assertTrue(isEnumConstantExists(Language.class, expected),
                String.format("Constant: %s should exist inside %s", expected, Language.class.getSimpleName()));
    }

    private static Stream<Arguments> casesForAgeGroup() {
        return Stream.of(
                Arguments.of("CHILD"),
                Arguments.of("TEEN"),
                Arguments.of("ADULT"),
                Arguments.of("OTHER")
        );
    }

    @ParameterizedTest
    @DisplayName("Constants of AgeGroup tested")
    @MethodSource("casesForAgeGroup")
    void testAgeGroupConstants(String expected) {
        assertTrue(isEnumConstantExists(AgeGroup.class, expected),
                String.format("Constant: %s should exist inside %s", expected, AgeGroup.class.getSimpleName()));
    }

    private boolean isEnumConstantExists(Class<?> enumClass, String constant) {
        return Arrays.stream(enumClass.getDeclaredFields())
                .anyMatch(field -> field.getName().equals(constant));
    }
}
