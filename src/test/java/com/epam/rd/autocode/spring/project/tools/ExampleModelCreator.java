package com.epam.rd.autocode.spring.project.tools;

import com.epam.rd.autocode.spring.project.controller.dto.*;
import com.epam.rd.autocode.spring.project.model.Book;
import com.epam.rd.autocode.spring.project.model.Client;
import com.epam.rd.autocode.spring.project.model.Employee;
import com.epam.rd.autocode.spring.project.model.Order;
import com.epam.rd.autocode.spring.project.model.enums.AgeGroup;
import com.epam.rd.autocode.spring.project.model.enums.Language;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ExampleModelCreator {
    public static Book createTestBook(String name) {
        Book book = new Book();
        book.setName(name);
        book.setGenre("Genre");
        book.setAgeGroup(AgeGroup.OTHER);
        book.setPrice(BigDecimal.TEN);
        book.setPublicationYear(LocalDate.now());
        book.setAuthor("Tester");
        book.setNumberOfPages(111);
        book.setCharacteristics("Test characteristics");
        book.setDescription("Test description");
        book.setLanguage(Language.OTHER);
        return book;
    }

    public static Client createTestClient(String email) {
        Client client = new Client();
        client.setName("Name");
        client.setBalance(BigDecimal.TEN);
        client.setPassword("Password");
        client.setEmail(email);
        return client;
    }

    public static Employee createTestEmployee(String email, String phone) {
        Employee employee = new Employee();
        employee.setName("Name");
        employee.setPassword("Password");
        employee.setBirthDate(LocalDate.MIN);
        employee.setEmail(email);
        employee.setPhone(phone);
        return employee;
    }

    public static Order createTestOrder() {
        Order order = new Order();
        order.setClient(new Client());
        order.setEmployee(new Employee());
        order.setPrice(BigDecimal.TEN);
        order.setBookItems(new ArrayList<>());
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    public static BookDTO createTestBookDTO(String name) {
        BookDTO book = new BookDTO();
        book.setName(name);
        book.setGenre("Genre");
        book.setAgeGroup(AgeGroup.OTHER);
        book.setPrice(BigDecimal.TEN);
        book.setPublicationYear(LocalDate.now());
        book.setAuthor("Tester");
        book.setNumberOfPages(111);
        book.setCharacteristics("Test characteristics");
        book.setDescription("Test description");
        book.setLanguage(Language.OTHER);
        return book;
    }

    public static ClientDTO createTestClientDTO(String email) {
        ClientDTO client = new ClientDTO();
        client.setName("Name");
        client.setBalance(BigDecimal.TEN);
        client.setPassword("Password");
        client.setEmail(email);
        return client;
    }

    public static EmployeeDTO createTestEmployeeDTO(String email, String phone) {
        EmployeeDTO employee = new EmployeeDTO();
        employee.setName("Name");
        employee.setPassword("Password");
        employee.setBirthDate(LocalDate.MIN);
        employee.setEmail(email);
        employee.setPhone(phone);
        return employee;
    }

    public static OrderDTO createTestOrderDTO() {
        OrderDTO order = new OrderDTO();
        order.setClient(new ClientOrderDTO());
        order.setEmployee(new EmployeeOrderDTO());
        order.setPrice(BigDecimal.TEN);
        order.setBookItems(new ArrayList<>());
        order.setOrderDate(LocalDateTime.now());
        return order;
    }
}
