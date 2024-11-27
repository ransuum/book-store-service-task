package com.epam.rd.autocode.spring.project.util.pagging;

import com.epam.rd.autocode.spring.project.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PageOrderConfiguration implements PageConfig<OrderDTO> {
    @Override
    public Map<String, Object> response(Page<OrderDTO> pages) {
        Map<String, Object> map = new HashMap<>();
        map.put("orders", pages.getContent());
        map.put("totalElements", pages.getTotalElements());
        map.put("totalPages", pages.getTotalPages());
        map.put("current_page", pages.getNumber());
        return map;
    }
}
