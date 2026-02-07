package org.thingsboard.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/bonjour")
    public String getBonjour() {
        return getGreeting(LocalTime.now());
    }

    static String getGreeting(LocalTime time) {
        int hour = time.getHour();
        if (hour >= 18)  return "Bonsoir"
        else if (hour >= 11) return "Bonjour"
        else return "Bon matin";
    }

}

