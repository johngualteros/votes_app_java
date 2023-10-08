package com.project.app_java.themes.utils;

import com.project.app_java.shared.exceptions.AlreadyExistsHttpException;
import com.project.app_java.shared.exceptions.BadRequestHttpException;
import com.project.app_java.themes.models.Theme;
import com.project.app_java.themes.repository.ThemeRepository;
import org.springframework.stereotype.Service;

@Service
public class Validators {
    private ThemeRepository themeRepository;
    Validators(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }
    /**
     * method for validate if exists theme by name or fail
     * @param name
     * @return void
     * */
    public void validateIfExistsThemeByNameOrFail(String name) throws AlreadyExistsHttpException {
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
        boolean isHexValid = com.project.app_java.shared.utils.Validators.isHexColor(color);
        if(!isHexValid) {
            throw new BadRequestHttpException("Color is not a valid hex color");
        }
    }
}
