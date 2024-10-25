package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/welcome")
    public String welcome(Locale locale) {
        return messageSource.getMessage("welcome", null, locale);
    }
}

