package com.rodrigopeleias.bookstoremanager.books.service;

import com.rodrigopeleias.bookstoremanager.author.service.AuthorService;
import com.rodrigopeleias.bookstoremanager.books.mapper.BookMapper;
import com.rodrigopeleias.bookstoremanager.books.repository.BookRepository;
import com.rodrigopeleias.bookstoremanager.publishers.service.PublisherService;
import com.rodrigopeleias.bookstoremanager.users.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookService {

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    private BookRepository bookRepository;

    private UserService userService;

    private AuthorService authorService;

    private PublisherService publisherService;

}
