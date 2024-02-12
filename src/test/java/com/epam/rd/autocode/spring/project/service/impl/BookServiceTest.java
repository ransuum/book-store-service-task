package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.controller.dto.BookDTO;
import com.epam.rd.autocode.spring.project.model.Book;
import com.epam.rd.autocode.spring.project.service.base.BookService;
import com.epam.rd.autocode.spring.project.service.repo.BookRepository;
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

import static com.epam.rd.autocode.spring.project.tools.MethodChecker.isMethodStartsWithAndIsAssignable;
import static com.epam.rd.autocode.spring.project.tools.ExampleModelCreator.createTestBook;
import static com.epam.rd.autocode.spring.project.tools.ExampleModelCreator.createTestBookDTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeAll
    static void testsOverrideMethods() {
        long numberOfFindOverrideMethods = Arrays.stream(BookServiceImpl.class.getDeclaredMethods())
                .filter(val -> isMethodStartsWithAndIsAssignable(val, BookService.class))
                .count();
        assertEquals(5, numberOfFindOverrideMethods,
                "Some methods of BookService has not implemented right");
    }

    @Test
    @DisplayName("Method getAllBooks launched")
    void getAllBooks_shouldReturnAllBooks() {
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(createTestBook("Test 1")));
        when(modelMapper.map(any(Book.class), eq(BookDTO.class))).thenReturn(createTestBookDTO("Test 1"));

        List<BookDTO> result = bookService.getAllBooks();

        assertEquals(1, result.size(),
                "Method getAllBooks() return invalid number of items");
    }

    @Test
    @DisplayName("Method getBookById launched")
    void getBookById_shouldReturnBookById() {
        long bookId = 1L;
        Book actual = createTestBook("Test 1");

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(actual));
        when(modelMapper.map(any(Book.class), eq(BookDTO.class))).thenReturn(createTestBookDTO("Test 1"));

        BookDTO expected = bookService.getBookById(bookId);

        assertNotNull(expected,
                "Method getBookById returns null");
        assertEquals(modelMapper.map(actual, BookDTO.class), expected,
                "Values of actual book and expected are not the same");

        verify(modelMapper, times(2)).map(actual, BookDTO.class);
    }

    @Test
    @DisplayName("Method addBook launched")
    void addBook_shouldAddBook() {
        BookDTO bookToSaveDTO = createTestBookDTO("Name");
        Book bookToSave = createTestBook("Name");

        when(modelMapper.map(any(), eq(Book.class))).thenReturn(bookToSave);
        when(bookRepository.save(bookToSave)).thenReturn(bookToSave);
        when(modelMapper.map(any(), eq(BookDTO.class))).thenReturn(bookToSaveDTO);

        BookDTO result = bookService.addBook(bookToSaveDTO);

        assertNotNull(result,
                "Method addBook returns null");
        assertEquals("Name", result.getName(),
                "Values of saved book are not valid");

        verify(modelMapper).map(eq(bookToSaveDTO), eq(Book.class));
        verify(modelMapper).map(eq(bookToSave), eq(BookDTO.class));
    }

    @Test
    @DisplayName("Method deleteBook launched")
    void deleteBookById_shouldDeleteBookById() {
        long bookIdToDelete = 1L;
        Book bookToDelete = createTestBook("Test Book");

        when(bookRepository.findById(bookIdToDelete)).thenReturn(Optional.of(bookToDelete));

        bookService.deleteBookById(bookIdToDelete);

        verify(bookRepository, times(1)).deleteById(bookIdToDelete);

        assertNull(bookService.getBookById(bookIdToDelete),
                "Value should be null. After invoking delete method book doesn't become deleted");
    }
}
