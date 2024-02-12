package com.epam.rd.autocode.spring.project.service.repo;

import com.epam.rd.autocode.spring.project.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.epam.rd.autocode.spring.project.tools.ExampleModelCreator.createTestClient;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClientRepositoryTest {

    private ClientRepository clientRepository;

    @BeforeEach
    public void setUp() {
        clientRepository = mock(ClientRepository.class);
    }

    @Test
    @DisplayName("Tests ClientRepository Save [1]")
    public void testSaveClientOnce() {
        Client client = createTestClient("test@test.com");

        mockSaveBehavior();

        Client saved = clientRepository.save(client);

        assertEquals(1L, saved.getId(),
                "Actual Id should be set correctly after saving the client.");

        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    @DisplayName("Tests ClientRepository Save [2]")
    public void testSaveClientTwice() {
        Client client = createTestClient("test@test.com");

        mockSaveBehavior();

        Client saved = clientRepository.save(client);

        when(clientRepository.save((any(Client.class))))
                .thenThrow(DataIntegrityViolationException.class);

        assertThrows(DataIntegrityViolationException.class, () -> clientRepository.save(client));

        verify(clientRepository, times(2)).save(any(Client.class));

        assertNotNull(saved.getId(),
                "Id cannot be null while saving the client");
    }



    private void mockSaveBehavior() {
        when(clientRepository.save(any(Client.class)))
                .thenAnswer(invocationOnMock -> {
                    Client saved = invocationOnMock.getArgument(0);
                    saved.setId(1L);
                    return saved;
                });
    }

    @Test
    @DisplayName("Tests ClientRepository findById")
    public void testFindById() {
        Long clientId = 1L;
        Client expectedClient = createTestClient("test@gmail.com");
        expectedClient.setId(clientId);

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(expectedClient));

        Optional<Client> book = clientRepository.findById(clientId);

        assertTrue(book.isPresent(),
                "Client should exist");

        assertEquals(expectedClient, book.get(),
                "Actual and expected clients are not the same");

        verify(clientRepository, times(1)).findById(clientId);
    }

    @Test
    @DisplayName("Tests ClientRepository findAll")
    public void testFindAllClients() {
        List<Client> expected = Arrays.asList(
                createTestClient("test@test.com"),
                createTestClient("tes1t@test.com"),
                createTestClient("tes5t@test.com")
        );

        when(clientRepository.findAll()).thenReturn(expected);

        List<Client> actual = clientRepository.findAll();

        assertEquals(expected, actual,
                "Expected list and actual are not the same");

        verify(clientRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Tests ClientRepository deleteById")
    public void testDeleteClient() {
        Long clientId = 1L;

        clientRepository.deleteById(clientId);

        verify(clientRepository, times(1)).deleteById(clientId);
    }
}
