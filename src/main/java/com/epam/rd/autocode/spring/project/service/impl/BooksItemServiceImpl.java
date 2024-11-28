package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.BookItemDTO;
import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.repo.BooksItemRepo;
import com.epam.rd.autocode.spring.project.service.BooksItemService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class BooksItemServiceImpl implements BooksItemService {
    private final BooksItemRepo booksItemRepo;
    private final ModelMapper modelMapper;

    public BooksItemServiceImpl(BooksItemRepo booksItemRepo, ModelMapper modelMapper) {
        this.booksItemRepo = booksItemRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<BookItemDTO> getAllBookItems(Pageable pageable) {
        return booksItemRepo.findAll(pageable)
                .map(booksItem -> modelMapper.map(booksItem, BookItemDTO.class));
    }

    @Override
    public BookItemDTO getBookItemById(Long id) {
        return modelMapper.map(booksItemRepo.findById(id)
                .orElseThrow(()
                        -> new NotFoundException("Book item not found")), BookItemDTO.class);
    }

    @Override
    public void deleteBookItemById(Long id) {
        booksItemRepo.delete(booksItemRepo.findById(id)
                .orElseThrow(()
                        -> new NotFoundException("Book item not found")));
    }

    @Override
    public BookItemDTO addBookItem(BookItemDTO book) {
        return null;
    }
}
