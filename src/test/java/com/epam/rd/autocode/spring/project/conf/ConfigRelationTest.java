package com.epam.rd.autocode.spring.project.conf;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConfigRelationTest {

    @ParameterizedTest
    @DisplayName("Annotation [Configuration] exist within the scope of each class")
    @ValueSource(classes = {BaseConfig.class, SecurityConfig.class})
    public void testsConfigAnnotation(Class<?> clazz) {
        assertTrue(clazz.isAnnotationPresent(Configuration.class),
                String.format("Class [%s]. [@Configuration] is missed.", clazz.getSimpleName()));
    }
}
