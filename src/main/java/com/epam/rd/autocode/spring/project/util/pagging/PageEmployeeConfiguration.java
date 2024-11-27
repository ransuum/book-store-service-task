package com.epam.rd.autocode.spring.project.util.pagging;

import com.epam.rd.autocode.spring.project.dto.EmployeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PageEmployeeConfiguration implements PageConfig<EmployeeDTO>{
    @Override
    public Map<String, Object> response(Page<EmployeeDTO> pages) {
        Map<String, Object> map = new HashMap<>();
        map.put("employees", pages.getContent());
        map.put("totalElements", pages.getTotalElements());
        map.put("totalPages", pages.getTotalPages());
        map.put("current_page", pages.getNumber());
        return map;
    }
}
