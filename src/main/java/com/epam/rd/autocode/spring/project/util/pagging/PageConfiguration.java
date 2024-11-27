package com.epam.rd.autocode.spring.project.util.pagging;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PageConfiguration {

    public Map<String, Object> response(Page<?> objects) {
        Map<String, Object> response = new HashMap<>();
        response.put(objects.getClass().getName().toLowerCase(), objects.getContent());
        response.put("current_page", objects.getNumber());
        response.put("total_items", objects.getTotalElements());
        response.put("total_pages", objects.getTotalPages());
        return response;
    }
}
