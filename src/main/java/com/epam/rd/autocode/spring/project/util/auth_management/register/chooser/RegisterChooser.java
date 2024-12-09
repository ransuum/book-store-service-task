package com.epam.rd.autocode.spring.project.util.auth_management.register.chooser;

import com.epam.rd.autocode.spring.project.dto.ClientDTO;
import com.epam.rd.autocode.spring.project.dto.EmployeeDTO;
import com.epam.rd.autocode.spring.project.model.request.register.ClientReq;
import com.epam.rd.autocode.spring.project.model.request.register.EmployeeReq;
import com.epam.rd.autocode.spring.project.util.auth_management.register.manager.RegisterManager;

public interface RegisterChooser {
    RegisterManager<ClientReq, ClientDTO> clientRegisterManager();
    RegisterManager<EmployeeReq, EmployeeDTO> employeeRegisterManager();
}
