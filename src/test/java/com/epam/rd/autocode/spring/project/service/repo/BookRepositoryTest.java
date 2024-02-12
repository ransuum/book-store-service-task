package com.epam.rd.autocode.spring.project.service.repo;

import com.epam.rd.autocode.spring.project.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.epam.rd.autocode.spring.project.tools.ExampleModelCreator.createTestBook;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookRepositoryTest {

    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        bookRepository = mock(BookRepository.class);
    }

    @Test
    @DisplayName("Tests BookRepository Save [1]")
    public void testSaveBookOnce() {
        Book book = createTestBook("Name");

        mockSaveBehavior();

        Book saved = bookRepository.save(book);

        assertEquals(1L, saved.getId(),
                "Actual Id should be set correctly after saving the book.");

        verify(bookRepository, times(1)).save(any(Book.class));

    }

    @Test
    @DisplayName("Tests BookRepository Save [2]")
    public void testSaveBookTwice() {
        Book book = createTestBook("name");

        mockSaveBehavior();

        Book saved = bookRepository.save(book);

        when(bookRepository.save((any(Book.class))))
                .thenThrow(DataIntegrityViolationException.class);

        assertThrows(DataIntegrityViolationException.class, () -> bookRepository.save(book));

        verify(bookRepository, times(2)).save(any(Book.class));

        assertNotNull(saved.getId(),
                "Id cannot be null while saving the book");
    }


    private void mockSaveBehavior() {
        when(bookRepository.save(any(Book.class)))
                .thenAnswer(invocationOnMock -> {
                    Book saved = invocationOnMock.getArgument(0);
                    saved.setId(1L);
                    return saved;
                });
    }

    @Test
    @DisplayName("Tests BookRepository findById")
    public void testFindById() {
        Long bookId = 1L;
        Book expectedBook = createTestBook("Name");
        expectedBook.setId(bookId);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(expectedBook));

        Optional<Book> book = bookRepository.findById(bookId);

        assertTrue(book.isPresent(),
                "Book should exist");

        assertEquals(expectedBook, book.get(),
                "Actual and expected books are not the same");

        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    @DisplayName("Tests BookRepository findAll")
    public void testFindAllBooks() {
        List<Book> expected = Arrays.asList(
                createTestBook("Name 1"),
                createTestBook("Name 2"),
                createTestBook("Name 3")
        );

        when(bookRepository.findAll()).thenReturn(expected);

        List<Book> actual = bookRepository.findAll();

        assertEquals(expected, actual,
                "Expected list and actual are not the same");

        verify(bookRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Tests BookRepository deleteById")
    public void testDeleteBook() {
        Long bookId = 1L;

        bookRepository.deleteById(bookId);

        verify(bookRepository, times(1)).deleteById(bookId);
    }
}
