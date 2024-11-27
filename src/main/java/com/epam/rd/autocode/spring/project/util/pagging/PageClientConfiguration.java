package com.epam.rd.autocode.spring.project.util.pagging;

import com.epam.rd.autocode.spring.project.dto.ClientDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PageClientConfiguration implements PageConfig<ClientDTO>{
    @Override
    public Map<String, Object> response(Page<ClientDTO> pages) {
        Map<String, Object> config = new HashMap<>();
        config.put("clients", pages.getContent());
        config.put("totalElements", pages.getTotalElements());
        config.put("totalPages", pages.getTotalPages());
        config.put("current_page", pages.getNumber());
        return config;
    }
}
