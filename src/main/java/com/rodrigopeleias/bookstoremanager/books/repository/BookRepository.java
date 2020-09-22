package com.rodrigopeleias.bookstoremanager.books.repository;

import com.rodrigopeleias.bookstoremanager.books.entity.Book;
import com.rodrigopeleias.bookstoremanager.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByNameAndIsbnAndUser(String name, String isbn, User user);
}
