package com.rodrigopeleias.bookstoremanager.publishers.repository;

import com.rodrigopeleias.bookstoremanager.publishers.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
