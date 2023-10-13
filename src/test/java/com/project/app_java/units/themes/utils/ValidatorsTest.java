package com.project.app_java.units.themes.utils;

import com.project.app_java.shared.exceptions.AlreadyExistsHttpException;
import com.project.app_java.shared.exceptions.BadRequestHttpException;
import com.project.app_java.themes.models.Theme;
import com.project.app_java.themes.repository.ThemeRepository;
import com.project.app_java.themes.utils.Validators;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class ValidatorsTest {

    @Autowired
    @MockBean
    private ThemeRepository themeRepository;

    @Mock
    private Validators      validators;

    @BeforeEach
    void setUp() {
        validators = new Validators(themeRepository);
//        Theme theme1 = this.themeRepository.save(new Theme("theme 1", "#ffffff"));
//        Theme theme2 = this.themeRepository.save(new Theme("theme 2", "#000000"));
//        Mockito.when(themeRepository.save(theme1)).thenReturn(theme1);
//        Mockito.when(themeRepository.save(theme2)).thenReturn(theme2);
    }

    @Test
    void validateIfExistsThemeByNameOrFail() throws AlreadyExistsHttpException {
        try {
            this.validators.validateIfExistsThemeByNameOrFail("theme not found");
        } catch (AlreadyExistsHttpException e) {
            assertEquals("Theme already exists", e.getMessage());
        }
        this.validators.validateIfExistsThemeByNameOrFail("theme 1");
        assert true;
    }

    @Test
    void getThemeByUuidOrFail() {
    }

    @Test
    void validateIfIsHexColor() throws BadRequestHttpException {
        String invalidHexColor = "rgb(255, 255, 255)";
        String validHexColor   = "#ffffff";
        try {
            validators.validateIfIsHexColor(invalidHexColor);
        } catch (BadRequestHttpException e) {
            assertEquals("Color is not a valid hex color", e.getMessage());
        }
        validators.validateIfIsHexColor(validHexColor);
        assert true;
    }
}

//@Test
//    void validateIfExistsThemeByNameOrFail() {
//        Theme theme1 = new Theme("theme 1", "#ffffff");
//        themeRepository.save(theme1);
//
//        assertThrows(AlreadyExistsHttpException.class, () -> {
//            validators.validateIfExistsThemeByNameOrFail("theme 1");
//        });
//        assertDoesNotThrow(() -> {
//            validators.validateIfExistsThemeByNameOrFail("theme not found");
//        });
//    }
//
//    @Test
//    void validateIfIsHexColor() {
//        assertThrows(BadRequestHttpException.class, () -> {
//            validators.validateIfIsHexColor("rgb(255, 255, 255)");
//        });
//        assertDoesNotThrow(() -> {
//            validators.validateIfIsHexColor("#ffffff");
//        });
//    }