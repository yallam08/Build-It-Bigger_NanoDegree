package com.example;

import java.util.Random;

public class JokeSupplier {
    private String[] jokes = {
            "Why do Java developers wear glasses? Because they can't C#",
            "The truth is out there. Anybody got the URL?",
            "Some things Man was never meant to know. For everything else, there's Google.",
            "Bugs come in through open Windows.",
            "If at first you don't succeed; call it version 1.0."
    };

    public String getAJoke() {
        return jokes[new Random().nextInt(jokes.length)];
    }
}
