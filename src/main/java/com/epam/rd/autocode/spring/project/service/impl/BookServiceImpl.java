package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.BookDTO;
import com.epam.rd.autocode.spring.project.exception.AlreadyExistException;
import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.model.Book;
import com.epam.rd.autocode.spring.project.repo.BookRepository;
import com.epam.rd.autocode.spring.project.service.BookService;
import com.epam.rd.autocode.spring.project.util.checking_validator.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final Validator<Book, BookDTO> validator;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper,
                           Validator<Book, BookDTO> validator) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public Page<BookDTO> getAllBooks(Pageable pageable) {
        return this.bookRepository.findAll(pageable)
                .map(book -> modelMapper.map(book, BookDTO.class));
    }

    @Override
    public BookDTO getBookByName(String name) {
        return modelMapper.map(this.bookRepository.findByName(name)
                .orElseThrow(()
                        -> new NotFoundException("Book with this name: " + name + " not found")), BookDTO.class);
    }

    @Override
    public BookDTO updateBookByName(String name, BookDTO book) {
        Book book1 = bookRepository.findByName(name)
                .orElseThrow(()
                        -> new NotFoundException("Book with this name: " + name + " not found"));
        return modelMapper.map(bookRepository.save(validator.validate(book1, book)), BookDTO.class);
    }

    @Override
    public void deleteBookByName(String name) {
        bookRepository.delete(bookRepository.findByName(name)
                .orElseThrow(()
                        -> new NotFoundException("Book with this name: " + name + " not found")));
    }

    @Override
    public BookDTO addBook(BookDTO book) {
        bookRepository.findByName(book.getName()).ifPresent(b -> {
            throw new AlreadyExistException("Book with this name: " + book.getName() + " already exist");
        });

        return modelMapper.map(bookRepository.save(Book.builder()
                .ageGroup(book.getAgeGroup())
                .author(book.getAuthor())
                .characteristics(book.getCharacteristics())
                .description(book.getDescription())
                .genre(book.getGenre())
                .language(book.getLanguage())
                .name(book.getName())
                .price(book.getPrice())
                .publicationDate(book.getPublicationDate())
                .pages(book.getPages())
                .build()), BookDTO.class);
    }
}
