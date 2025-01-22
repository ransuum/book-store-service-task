package com.epam.rd.autocode.spring.project.util.profile_chooser;

import org.springframework.ui.Model;

import java.util.List;

public interface Profile {
    Integer page = 0;
    Integer size = 50;
    public String showProfile(String email, List<String> roles, Model model);
    public Boolean getName();
}
