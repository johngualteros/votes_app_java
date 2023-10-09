package com.project.app_java.themes.controller;

import com.project.app_java.shared.constants.AppConstants;
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

    @PutMapping("/{uuid}")
    public ResponseEntity<?> updateTheme(@PathVariable String uuid, @RequestBody Theme theme) throws BadRequestHttpException, AlreadyExistsHttpException, NotFoundHttpException, InternalServerHttpException {
        try {
            themeService.updateTheme(uuid, theme);
            return ResponseEntity.ok().body(ResponseEntity.status(202).body(theme));
        } catch (BadRequestHttpException e) {
            throw new BadRequestHttpException(e.getMessage());
        } catch (AlreadyExistsHttpException e) {
            throw new AlreadyExistsHttpException(e.getMessage());
        } catch (NotFoundHttpException e) {
            throw new NotFoundHttpException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerHttpException(e.getMessage());
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteThemeByUuid(@PathVariable String uuid) throws NotFoundHttpException, InternalServerHttpException {
        try {
            themeService.deleteThemeByUuid(uuid);
            return ResponseEntity.ok().body(ResponseEntity.status(202).body("Theme deleted successfully"));

        } catch (NotFoundHttpException e) {
            throw new NotFoundHttpException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerHttpException(e.getMessage());
        }
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

    @GetMapping
    public ResponseEntity<List<Theme>> getAllThemes(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION) String sortDirection
    ) {
        return ResponseEntity.ok().body(themeService.getAllThemes(page, size, sortBy, sortDirection));
    }
}
