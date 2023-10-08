package com.project.app_java.themes.services;

import com.project.app_java.shared.exceptions.AlreadyExistsHttpException;
import com.project.app_java.shared.exceptions.BadRequestHttpException;
import com.project.app_java.shared.exceptions.NotFoundHttpException;
import com.project.app_java.shared.utils.Convertors;
import com.project.app_java.themes.models.Theme;
import com.project.app_java.themes.repository.ThemeRepository;
import com.project.app_java.themes.utils.Validators;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {
    //TODO: make the responses or es or en depending on the language of the user first using the theme

    // repository
    private ThemeRepository themeRepository;
    // validators
    private Validators validators;
    // constructor
    public ThemeService(ThemeRepository themeRepository, Validators validators) {
        this.themeRepository = themeRepository;
        this.validators = validators;
    }
    /**
     * method to create a theme
     * @param theme
     * @return String
     */
    public void createTheme(Theme theme) throws BadRequestHttpException, AlreadyExistsHttpException {
        String themeNameCamelCase = Convertors.convertToCamelCase(theme.getName());
        validators.validateIfIsHexColor(theme.getColor());
        validators.validateIfExistsThemeByNameOrFail(themeNameCamelCase);
        theme.setName(themeNameCamelCase);
        themeRepository.save(theme);
    }

    /**
     * method for get all themes
     * @return List<Theme>
     * */
    public List<Theme> getAllThemesWithoutPaginate() {
        return themeRepository.findAll();
    }

    /**
     * method for get theme by uuid
     * @param uuid
     * @return Theme
     * */
    public Theme getThemeByUuid(String uuid) throws NotFoundHttpException {
        Optional<Theme> theme = themeRepository.findByUUID(uuid);
        if (theme.isPresent()) {
            return theme.get();
        }
        throw new NotFoundHttpException("Theme");
    }
}
