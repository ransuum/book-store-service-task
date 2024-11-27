package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.BookDTO;
import com.epam.rd.autocode.spring.project.exception.AlreadyExistException;
import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.model.Book;
import com.epam.rd.autocode.spring.project.repo.BookRepository;
import com.epam.rd.autocode.spring.project.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
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

        if (book.getName() != null && !book.getName().equals(book1.getName())) book1.setName(book.getName());
        if (book.getAuthor() != null && !book.getAuthor().equals(book1.getAuthor())) book1.setAuthor(book.getAuthor());
        if (book.getGenre() != null && !book.getGenre().equals(book1.getGenre())) book1.setGenre(book.getGenre());
        if (book.getCharacteristics() != null && !book.getCharacteristics().equals(book1.getCharacteristics()))
            book1.setCharacteristics(book.getCharacteristics());
        if (book.getDescription() != null && !book.getDescription().equals(book1.getDescription())) book1.setDescription(book.getDescription());
        if (book.getPrice() != null && !book.getPrice().equals(book1.getPrice())) book1.setPrice(book.getPrice());
        if (book.getAgeGroup() != null && !book.getAgeGroup().equals(book1.getAgeGroup())) book1.setAgeGroup(book.getAgeGroup());
        if (book.getLanguage() != null && !book.getLanguage().equals(book1.getLanguage())) book1.setLanguage(book.getLanguage());
        if (book.getPages() != null && !book.getPages().equals(book1.getPages())) book1.setPages(book.getPages());

        return modelMapper.map(bookRepository.save(book1), BookDTO.class);
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
