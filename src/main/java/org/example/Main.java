package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // ← esta anotación indica que es la clase principal
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}