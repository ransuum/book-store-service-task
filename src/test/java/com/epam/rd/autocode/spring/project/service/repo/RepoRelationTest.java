package com.epam.rd.autocode.spring.project.service.repo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RepoRelationTest {

    private static Stream<Arguments> casesForRepos() {
        return Stream.of(
                Arguments.of(BookRepository.class),
                Arguments.of(ClientRepository.class),
                Arguments.of(EmployeeRepository.class),
                Arguments.of(OrderRepository.class)
        );
    }

    @ParameterizedTest
    @MethodSource("casesForRepos")
    @DisplayName("All the classes implements JpaRepository")
    void testImplements(Class<?> classes) {
        assertTrue(Arrays.stream(classes.getInterfaces()).anyMatch(i -> i == JpaRepository.class),
                classes.getSimpleName() + " should implements JpaRepository");
    }
}
