package com.rodrigopeleias.bookstoremanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @GetMapping
    public String hello() {
        return "Hello Bookstore Manager, I am running an example with PR!!";
    }
}
