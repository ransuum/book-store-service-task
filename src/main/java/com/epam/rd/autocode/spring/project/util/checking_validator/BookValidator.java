package com.epam.rd.autocode.spring.project.util.checking_validator;

import com.epam.rd.autocode.spring.project.dto.BookDTO;
import com.epam.rd.autocode.spring.project.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookValidator implements Validator<Book, BookDTO> {

    @Override
    public Book validate(Book o1, BookDTO o2) {
        if (!o1.getName().equals(o2.getName())) o2.setName(o1.getName());
        if (!o1.getAuthor().equals(o2.getAuthor())) o2.setAuthor(o1.getAuthor());
        if (!o1.getGenre().equals(o2.getGenre())) o2.setGenre(o1.getGenre());
        if (!o1.getCharacteristics().equals(o2.getCharacteristics())) o1.setCharacteristics(o1.getCharacteristics());
        if (!o1.getDescription().equals(o2.getDescription())) o2.setDescription(o1.getDescription());
        if (!o1.getPrice().equals(o2.getPrice())) o2.setPrice(o1.getPrice());
        if (!o1.getAgeGroup().equals(o2.getAgeGroup())) o2.setAgeGroup(o1.getAgeGroup());
        if (!o1.getLanguage().equals(o2.getLanguage())) o2.setLanguage(o1.getLanguage());
        if (!o1.getPages().equals(o2.getPages())) o2.setPages(o1.getPages());

        return o1;
    }
}
