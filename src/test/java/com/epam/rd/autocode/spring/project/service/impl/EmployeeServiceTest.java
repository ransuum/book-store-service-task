package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.controller.dto.EmployeeDTO;
import com.epam.rd.autocode.spring.project.model.Employee;
import com.epam.rd.autocode.spring.project.service.base.EmployeeService;
import com.epam.rd.autocode.spring.project.service.repo.EmployeeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.epam.rd.autocode.spring.project.tools.ExampleModelCreator.createTestEmployee;
import static com.epam.rd.autocode.spring.project.tools.ExampleModelCreator.createTestEmployeeDTO;
import static com.epam.rd.autocode.spring.project.tools.MethodChecker.isMethodStartsWithAndIsAssignable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeAll
    static void testsOverrideMethods() {
        Long numberOfFindOverrideMethods = Arrays.stream(EmployeeServiceImpl.class.getDeclaredMethods())
                .filter(val -> isMethodStartsWithAndIsAssignable(val, EmployeeService.class))
                .count();
        assertEquals(5, numberOfFindOverrideMethods,
                "Some methods of EmployeeService has not implemented right");
    }

    @Test
    @DisplayName("Method getAllEmployees launched")
    void getAllEmployees_shouldReturnAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(Collections.singletonList(createTestEmployee("test@goo.com", "1231231233")));
        when(modelMapper.map(any(Employee.class), eq(EmployeeDTO.class))).thenReturn(createTestEmployeeDTO("test@goo.com", "1231231233"));

        List<EmployeeDTO> result = employeeService.getAllEmployees();

        assertEquals(1, result.size(),
                "Method getAllEmployees() return invalid number of items");
    }

    @Test
    @DisplayName("Method getEmployeeById launched")
    void getEmployeeById_shouldReturnEmployeeById() {
        Long employeeId = 1L;
        Employee actual = createTestEmployee("Test@gmail.com", "1231241244");

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(actual));
        when(modelMapper.map(any(Employee.class), eq(EmployeeDTO.class))).thenReturn(createTestEmployeeDTO("Test@gmail.com", "1231231244"));

        EmployeeDTO expected = employeeService.getEmployeeById(employeeId);

        assertNotNull(expected,
                "Method getEmployeeById returns null");
        assertEquals(modelMapper.map(actual, EmployeeDTO.class), expected,
                "Values of actual employee and expected are not the same");

        verify(modelMapper, times(2)).map(actual, EmployeeDTO.class);
    }

    @Test
    @DisplayName("Method addEmployee launched")
    void addEmployee_shouldAddEmployee() {
        EmployeeDTO employeeToSaveDTO = createTestEmployeeDTO("Name@gmail.com", "111111111");
        Employee employeeToSave = createTestEmployee("Name@gmail.com", "111111111");

        when(modelMapper.map(any(), eq(Employee.class))).thenReturn(employeeToSave);
        when(employeeRepository.save(employeeToSave)).thenReturn(employeeToSave);
        when(modelMapper.map(any(), eq(EmployeeDTO.class))).thenReturn(employeeToSaveDTO);

        EmployeeDTO result = employeeService.addEmployee(employeeToSaveDTO);

        assertNotNull(result,
                "Method addEmployee returns null");
        assertEquals("Name", result.getName(),
                "Values of saved employee are not valid");

        verify(modelMapper).map(eq(employeeToSaveDTO), eq(Employee.class));
        verify(modelMapper).map(eq(employeeToSave), eq(EmployeeDTO.class));
    }

    @Test
    @DisplayName("Method deleteEmployee launched")
    void deleteEmployeeById_shouldDeleteEmployeeById() {
        Long employeeIdToDelete = 1L;
        Employee employeeToDelete = createTestEmployee("Test@gmail.com", "1231231231");

        when(employeeRepository.findById(employeeIdToDelete)).thenReturn(Optional.of(employeeToDelete));

        employeeService.deleteEmployeeById(employeeIdToDelete);

        verify(employeeRepository, times(1)).deleteById(employeeIdToDelete);

        assertNull(employeeService.getEmployeeById(employeeIdToDelete),
                "Value should be null. After invoking delete method employee doesn't become deleted");
    }
}
