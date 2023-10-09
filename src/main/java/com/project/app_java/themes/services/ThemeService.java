package com.project.app_java.themes.services;

import com.project.app_java.shared.exceptions.AlreadyExistsHttpException;
import com.project.app_java.shared.exceptions.BadRequestHttpException;
import com.project.app_java.shared.exceptions.NotFoundHttpException;
import com.project.app_java.shared.utils.Convertors;
import com.project.app_java.themes.models.Theme;
import com.project.app_java.themes.repository.ThemeRepository;
import com.project.app_java.themes.utils.Validators;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {
    //TODO: make the responses or es or en depending on the language of the user
    //TODO: Implement the internazionalization of the app

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
     * method to update a theme
     * @param theme
     * @return Theme
     */
    public Theme updateTheme(String uuid, Theme theme) throws BadRequestHttpException, AlreadyExistsHttpException, NotFoundHttpException {
        String themeNameCamelCase = Convertors.convertToCamelCase(theme.getName());
        validators.validateIfIsHexColor(theme.getColor());
        Theme foundedTheme = validators.getThemeByUuidOrFail(uuid);
        foundedTheme.setName(themeNameCamelCase);
        foundedTheme.setColor(theme.getColor());
        return themeRepository.save(theme);
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

    /**
     * method for delete theme by uuid
     * @param uuid
     * */
    public void deleteThemeByUuid(String uuid) throws NotFoundHttpException {
        Optional<Theme> theme = themeRepository.findByUUID(uuid);
        if(theme.isEmpty()) throw new NotFoundHttpException("Theme");
        themeRepository.delete(theme.get());
    }

    /**
     * method for get all themes with paginate
     * @param page
     * @param size
     * @param sortBy
     * @param sortDirection
     * @return List<Theme>
     * */
    public List<Theme> getAllThemes(int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();
        // CREATE A PAGEABLE INSTANCE
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Theme> themes = themeRepository.findAll(pageable);
        // GET CONTENT OF PAGE
        List<Theme> themesList = themes.getContent();
        return themesList;
    }
}





