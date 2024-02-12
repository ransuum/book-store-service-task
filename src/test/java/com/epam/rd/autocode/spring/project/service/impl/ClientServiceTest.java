package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.controller.dto.ClientDTO;
import com.epam.rd.autocode.spring.project.model.Client;
import com.epam.rd.autocode.spring.project.service.base.ClientService;
import com.epam.rd.autocode.spring.project.service.repo.ClientRepository;
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

import static com.epam.rd.autocode.spring.project.tools.ExampleModelCreator.createTestClient;
import static com.epam.rd.autocode.spring.project.tools.ExampleModelCreator.createTestClientDTO;
import static com.epam.rd.autocode.spring.project.tools.MethodChecker.isMethodStartsWithAndIsAssignable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeAll
    static void testsOverrideMethods() {
        long numberOfFindOverrideMethods = Arrays.stream(ClientServiceImpl.class.getDeclaredMethods())
                .filter(val -> isMethodStartsWithAndIsAssignable(val, ClientService.class))
                .count();
        assertEquals(5, numberOfFindOverrideMethods,
                "Some methods of ClientService has not implemented right");
    }

    @Test
    @DisplayName("Method getAllClients launched")
    void getAllClients_shouldReturnAllClients() {
        when(clientRepository.findAll()).thenReturn(Collections.singletonList(createTestClient("test@gmail.com")));
        when(modelMapper.map(any(Client.class), eq(ClientDTO.class))).thenReturn(createTestClientDTO("test@gmail.com"));

        List<ClientDTO> result = clientService.getAllClients();

        assertEquals(1, result.size(),
                "Method getAllClients() return invalid number of items");
    }

    @Test
    @DisplayName("Method getClientById launched")
    void getClientById_shouldReturnClientById() {
        long clientId = 1L;
        Client actual = createTestClient("test@gmail.com");

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(actual));
        when(modelMapper.map(any(Client.class), eq(ClientDTO.class))).thenReturn(createTestClientDTO("Test 1"));

        ClientDTO expected = clientService.getClientById(clientId);

        assertNotNull(expected,
                "Method getClientById returns null");
        assertEquals(modelMapper.map(actual, ClientDTO.class), expected,
                "Values of actual client and expected are not the same");

        verify(modelMapper, times(2)).map(actual, ClientDTO.class);
    }

    @Test
    @DisplayName("Method addClient launched")
    void addClient_shouldAddClient() {
        ClientDTO clientToSaveDTO = createTestClientDTO("name@test.com");
        Client clientToSave = createTestClient("name@test.com");

        when(modelMapper.map(any(), eq(Client.class))).thenReturn(clientToSave);
        when(clientRepository.save(clientToSave)).thenReturn(clientToSave);
        when(modelMapper.map(any(), eq(ClientDTO.class))).thenReturn(clientToSaveDTO);

        ClientDTO result = clientService.addClient(clientToSaveDTO);

        assertNotNull(result,
                "Method addClient returns null");
        assertEquals("Name", result.getName(),
                "Values of saved client are not valid");

        verify(modelMapper).map(eq(clientToSaveDTO), eq(Client.class));
        verify(modelMapper).map(eq(clientToSave), eq(ClientDTO.class));
    }

    @Test
    @DisplayName("Method deleteClient launched")
    void deleteClientById_shouldDeleteClientById() {
        long clientIdToDelete = 1L;
        Client clientToDelete = createTestClient("name@test.com");

        when(clientRepository.findById(clientIdToDelete)).thenReturn(Optional.of(clientToDelete));

        clientService.deleteClientById(clientIdToDelete);

        verify(clientRepository, times(1)).deleteById(clientIdToDelete);

        assertNull(clientService.getClientById(clientIdToDelete),
                "Value should be null. After invoking delete method client doesn't become deleted");
    }
}
