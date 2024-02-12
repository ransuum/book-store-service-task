package com.epam.rd.autocode.spring.project.service.repo;

import com.epam.rd.autocode.spring.project.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.epam.rd.autocode.spring.project.tools.ExampleModelCreator.createTestEmployee;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeRepositoryTest {

    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp() {
        employeeRepository = mock(EmployeeRepository.class);
    }

    @Test
    @DisplayName("Tests EmployeeRepository Save [1]")
    public void testSaveEmployeeOnce() {
        Employee employee = createTestEmployee("ggg@g.com", "1111111111");

        mockSaveBehavior();

        Employee saved = employeeRepository.save(employee);

        assertEquals(1L, saved.getId(),
                "Actual Id should be set correctly after saving the employee.");

        verify(employeeRepository, times(1)).save(any(Employee.class));

    }

    @Test
    @DisplayName("Tests EmployeeRepository Save [2]")
    public void testSaveBookTwice() {
        Employee employee = createTestEmployee("ggg@g.com", "1111111111");

        mockSaveBehavior();

        Employee saved = employeeRepository.save(employee);

        when(employeeRepository.save((any(Employee.class))))
                .thenThrow(DataIntegrityViolationException.class);

        assertThrows(DataIntegrityViolationException.class, () -> employeeRepository.save(employee));

        verify(employeeRepository, times(2)).save(any(Employee.class));

        assertNotNull(saved.getId(),
                "Id cannot be null while saving the employee");
    }



    private void mockSaveBehavior() {
        when(employeeRepository.save(any(Employee.class)))
                .thenAnswer(invocationOnMock -> {
                    Employee saved = invocationOnMock.getArgument(0);
                    saved.setId(1L);
                    return saved;
                });
    }

    @Test
    @DisplayName("Tests EmployeeRepository findById")
    public void testFindById() {
        Long employeeId = 1L;
        Employee expectedEmployee = createTestEmployee("ggg@g.com", "1111111111");
        expectedEmployee.setId(employeeId);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(expectedEmployee));

        Optional<Employee> book = employeeRepository.findById(employeeId);

        assertTrue(book.isPresent(),
                "Employee should exist");

        assertEquals(expectedEmployee, book.get(),
                "Actual and expected employees are not the same");

        verify(employeeRepository, times(1)).findById(employeeId);
    }

    @Test
    @DisplayName("Tests EmployeeRepository findAll")
    public void testFindAllEmployees() {
        List<Employee> expected = Arrays.asList(
                createTestEmployee("ggg@g.com", "1111111111"),
                createTestEmployee("ggq@g.com", "1131111111"),
                createTestEmployee("13s@g.com", "1141111111")
        );

        when(employeeRepository.findAll()).thenReturn(expected);

        List<Employee> actual = employeeRepository.findAll();

        assertEquals(expected, actual,
                "Expected list and actual are not the same");

        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Tests EmployeeRepository deleteById")
    public void testDeleteBook() {
        Long employeeId = 1L;

        employeeRepository.deleteById(employeeId);

        verify(employeeRepository, times(1)).deleteById(employeeId);
    }
}
