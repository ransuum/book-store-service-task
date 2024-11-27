package com.epam.rd.autocode.spring.project.util.checking_validator;

import com.epam.rd.autocode.spring.project.dto.EmployeeDTO;
import com.epam.rd.autocode.spring.project.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeValidator implements Validator<Employee, EmployeeDTO> {

    @Override
    public Employee validate(Employee o1, EmployeeDTO o2) {
        if (o2.getName() != null && !o2.getName().equals(o1.getName())) o1.setName(o2.getName());
        if (o2.getEmail() != null && !o2.getEmail().equals(o1.getEmail())) o1.setEmail(o2.getEmail());
        if (o2.getPhone() != null && !o2.getPhone().equals(o1.getPhone())) o1.setPhone(o2.getPhone());
        if (o2.getBirthDate() != null) o2.setBirthDate(o1.getBirthDate());

        return o1;
    }
}
