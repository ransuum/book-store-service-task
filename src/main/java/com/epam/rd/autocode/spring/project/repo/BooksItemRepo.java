package com.epam.rd.autocode.spring.project.repo;

import com.epam.rd.autocode.spring.project.model.BookItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksItemRepo extends JpaRepository<BookItem, Long> {
}
