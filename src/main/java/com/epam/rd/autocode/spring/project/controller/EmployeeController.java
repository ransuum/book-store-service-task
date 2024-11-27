package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.EmployeeDTO;
import com.epam.rd.autocode.spring.project.service.EmployeeService;
import com.epam.rd.autocode.spring.project.util.pagging.PageConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final PageConfiguration pageConfiguration;

    public EmployeeController(EmployeeService employeeService, PageConfiguration pageConfiguration) {
        this.employeeService = employeeService;
        this.pageConfiguration = pageConfiguration;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getEmployees(@RequestParam Integer page,
                                                            @RequestParam Integer size) {
        return ResponseEntity.ok(pageConfiguration.response(employeeService.getAllEmployees(PageRequest.of(page, size))));
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity<EmployeeDTO> getEmployeeByEmail(@PathVariable String email) {
        return ResponseEntity.ok(employeeService.getEmployeeByEmail(email));
    }

    @PutMapping("/{email}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable String email,
                                                      @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.updateEmployeeByEmail(email, employeeDTO));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteEmployeeByEmail(@PathVariable String email) {
        employeeService.deleteEmployeeByEmail(email);
        return ResponseEntity.ok("DELETED");
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(employeeService.addEmployee(employeeDTO), HttpStatus.CREATED);
    }
}
