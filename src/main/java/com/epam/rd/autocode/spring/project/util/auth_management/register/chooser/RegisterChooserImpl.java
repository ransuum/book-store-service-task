package com.epam.rd.autocode.spring.project.util.auth_management.register.chooser;

import com.epam.rd.autocode.spring.project.dto.ClientDTO;
import com.epam.rd.autocode.spring.project.dto.EmployeeDTO;
import com.epam.rd.autocode.spring.project.model.request.register.ClientReq;
import com.epam.rd.autocode.spring.project.model.request.register.EmployeeReq;
import com.epam.rd.autocode.spring.project.util.auth_management.register.manager.RegisterManager;
import org.springframework.stereotype.Service;

@Service
public class RegisterChooserImpl implements RegisterChooser {
    private final RegisterManager<ClientReq, ClientDTO> clientReqRegisterManager;
    private final RegisterManager<EmployeeReq, EmployeeDTO> employeeReqRegisterManager;

    public RegisterChooserImpl(RegisterManager<ClientReq, ClientDTO> clientReqRegisterManager,
                               RegisterManager<EmployeeReq, EmployeeDTO> employeeReqRegisterManager) {
        this.clientReqRegisterManager = clientReqRegisterManager;
        this.employeeReqRegisterManager = employeeReqRegisterManager;
    }

    @Override
    public RegisterManager<ClientReq, ClientDTO> clientRegisterManager() {
        return clientReqRegisterManager;
    }

    @Override
    public RegisterManager<EmployeeReq, EmployeeDTO> employeeRegisterManager() {
        return employeeReqRegisterManager;
    }
}
