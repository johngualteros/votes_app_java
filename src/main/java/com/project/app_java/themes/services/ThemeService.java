package com.project.app_java.themes.services;

import com.project.app_java.shared.utils.Convertors;
import com.project.app_java.shared.utils.Validators;
import com.project.app_java.themes.models.Theme;
import com.project.app_java.themes.repository.ThemeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThemeService {
    // repository
    private ThemeRepository themeRepository;
    // constructor
    public ThemeService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }
    /**
     * method to create a theme
     * @param theme
     * @return String
     */
    public String createTheme(Theme theme) {
        Optional<String> isHexValid = validateIfIsHexColor(theme.getColor());
        if(isHexValid.isPresent()) {
            return isHexValid.get();
        }
        String themeNameCamelCase = Convertors.convertToCamelCase(theme.getName());
        Theme themeFound = themeRepository.findByName(themeNameCamelCase);
        if(themeFound != null) {
            return "Theme already exists";
        }
        theme.setName(themeNameCamelCase);
        themeRepository.save(theme);
        return "Theme created successfully";
    }

    /**
     * method to update a theme
     * @param uuid
     * @param theme
     * @return String
     * */
    public String updateTheme(String uuid, Theme theme) {
        Theme themeFound = themeRepository.findByUUID(uuid);
        if(themeFound == null) {
            return "Theme not found";
        }
        Optional<String> isHexValid = validateIfIsHexColor(theme.getColor());
        if(isHexValid.isPresent()) {
            return isHexValid.get();
        }
        String themeNameCamelCase = Convertors.convertToCamelCase(theme.getName());
        Theme themeFoundByName = themeRepository.findByName(themeNameCamelCase);
        if(themeFoundByName != null) {
            return "Theme already exists";
        }
        themeFound.setName(themeNameCamelCase);
        themeFound.setColor(theme.getColor());
        themeRepository.save(themeFound);
        return "Theme updated successfully";
    }

    /**
     * method for validate if is a hex color
     * @param color
     * @return Optional<String>
     */
    public Optional<String> validateIfIsHexColor(String color) {
        boolean isHexValid = Validators.isHexColor(color);
        if(!isHexValid) {
            return Optional.of("Invalid hex color");
        }
        return Optional.empty();
    }
}
