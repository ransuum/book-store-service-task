package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.BookItemDTO;
import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.model.BookItem;
import com.epam.rd.autocode.spring.project.repo.BookRepository;
import com.epam.rd.autocode.spring.project.repo.BooksItemRepo;
import com.epam.rd.autocode.spring.project.service.BooksItemService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BooksItemServiceImpl implements BooksItemService {
    private final BooksItemRepo booksItemRepo;
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;

    public BooksItemServiceImpl(BooksItemRepo booksItemRepo, ModelMapper modelMapper, BookRepository bookRepository) {
        this.booksItemRepo = booksItemRepo;
        this.modelMapper = modelMapper;
        this.bookRepository = bookRepository;
    }

    @Override
    public Page<BookItemDTO> getAllBookItems(Pageable pageable) {
        return booksItemRepo.findAll(pageable)
                .map(booksItem -> modelMapper.map(booksItem, BookItemDTO.class));
    }

    @Override
    public BookItemDTO getBookItemById(Long id) {
        return modelMapper.map(booksItemRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Book item not found")), BookItemDTO.class);
    }

    @Override
    public void deleteBookItemById(Long id) {
        booksItemRepo.delete(booksItemRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Book item not found")));
    }

    @Override
    public BookItemDTO addBookItem(BookItemDTO book) {
        return modelMapper.map(booksItemRepo.save(BookItem.builder()
                .book(bookRepository.findByName(book.getBookName())
                        .orElseThrow(() -> new NotFoundException("Book with name " + book.getBookName() + "not found")))
                .quantity(book.getQuantity())
                .build()), BookItemDTO.class);
    }
}
