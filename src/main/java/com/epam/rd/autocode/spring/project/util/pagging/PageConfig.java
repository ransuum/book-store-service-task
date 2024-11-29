package com.epam.rd.autocode.spring.project.util.pagging;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PageConfig<T> {

    public Map<String, Object> response(Page<T> page) {
        Map<String, Object> result = new HashMap<>();
        List<T> list = page.getContent();
        result.put(list.get(0).getClass().getSimpleName().toLowerCase().replace("dto", ""), list);
        result.put("totalElements", page.getTotalElements());
        result.put("totalPages", page.getTotalPages());
        result.put("current_page", page.getNumber());

        return result;
    }
}
