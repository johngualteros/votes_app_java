package com.project.app_java;

import com.project.app_java.shared.i18n.Translator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class MainController {

    @GetMapping()
    public String getMessage(@RequestParam("msg") String msg) {
        return Translator.toLocale(msg);
    }
}