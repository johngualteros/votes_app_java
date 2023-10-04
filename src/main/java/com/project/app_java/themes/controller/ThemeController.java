package com.project.app_java.themes.controller;

import com.project.app_java.themes.models.Theme;
import com.project.app_java.themes.services.ThemeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/theme")
public class ThemeController {
    private ThemeService themeService;
    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @PostMapping
    public ResponseEntity<String> createTheme(@RequestBody Theme theme) {
        String message = themeService.createTheme(theme);
        return ResponseEntity.ok(message);
    }
}
