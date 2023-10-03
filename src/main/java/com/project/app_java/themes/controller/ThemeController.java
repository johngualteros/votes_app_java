package com.project.app_java.themes.controller;

import com.project.app_java.themes.services.ThemeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/theme")
public class ThemeController {
    private ThemeService themeService;
    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }
}
