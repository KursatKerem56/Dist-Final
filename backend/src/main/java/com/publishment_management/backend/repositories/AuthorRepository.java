package com.publishment_management.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.publishment_management.backend.models.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
