package com.project.app_java.themes.controller;

import com.project.app_java.shared.exceptions.AlreadyExistsHttpException;
import com.project.app_java.shared.exceptions.BadRequestHttpException;
import com.project.app_java.shared.exceptions.InternalServerHttpException;
import com.project.app_java.shared.exceptions.NotFoundHttpException;
import com.project.app_java.shared.problemDetails.HttpProblemDetails;
import com.project.app_java.themes.models.Theme;
import com.project.app_java.themes.services.ThemeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/theme")
public class ThemeController {
    private ThemeService themeService;
    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @PostMapping
    public ResponseEntity<?> createTheme(@RequestBody Theme theme) throws AlreadyExistsHttpException, BadRequestHttpException, InternalServerHttpException {
        try {
            themeService.createTheme(theme);
            return ResponseEntity.ok().body(ResponseEntity.status(201).body(theme));
        } catch (BadRequestHttpException e) {
            throw new BadRequestHttpException(e.getMessage());
        } catch (AlreadyExistsHttpException e) {
            throw new AlreadyExistsHttpException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerHttpException(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Theme>> getAllWithoutPaginate() {
        return ResponseEntity.ok().body(themeService.getAllThemesWithoutPaginate());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Theme> getThemeByUuid(@PathVariable String uuid) throws NotFoundHttpException, InternalServerHttpException {
        try {
            return ResponseEntity.ok().body(ResponseEntity.status(200).body(themeService.getThemeByUuid(uuid)).getBody());
        } catch (NotFoundHttpException e) {
            throw new NotFoundHttpException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerHttpException(e.getMessage());
        }
    }
}
