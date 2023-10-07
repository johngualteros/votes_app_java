package com.project.app_java.themes.services;

import com.project.app_java.shared.exceptions.AlreadyExistsHttpException;
import com.project.app_java.shared.exceptions.BadRequestHttpException;
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
    public String createTheme(Theme theme) throws BadRequestHttpException, AlreadyExistsHttpException {
        String themeNameCamelCase = Convertors.convertToCamelCase(theme.getName());
        validateIfIsHexColor(theme.getColor());
        this.validateIfExistsThemeByNameOrFail(themeNameCamelCase);
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
    /*public String updateTheme(String uuid, Theme theme) {
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
    }*/

    /**
     * method for validate if exists theme by name or fail
     * @param name
     * @return void
     * */
    protected void validateIfExistsThemeByNameOrFail(String name) throws AlreadyExistsHttpException {
        Theme theme = themeRepository.findByName(name);
        if(theme != null) {
            throw new AlreadyExistsHttpException("Theme already exists");
        }
    }

    /**
     * method for validate if is a hex color
     * @param color
     * @return Optional<String>
     */
    public void validateIfIsHexColor(String color) throws BadRequestHttpException {
        boolean isHexValid = Validators.isHexColor(color);
        if(!isHexValid) {
            throw new BadRequestHttpException("Color is not a valid hex color");
        }
    }
}
