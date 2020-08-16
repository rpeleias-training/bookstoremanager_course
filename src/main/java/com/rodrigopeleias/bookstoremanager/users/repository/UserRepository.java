package com.rodrigopeleias.bookstoremanager.users.repository;

import com.rodrigopeleias.bookstoremanager.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
