package com.publishment_management.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.publishment_management.backend.models.Publishes;

@Repository
public interface PublishesRepository extends JpaRepository<Publishes, Integer>{

}
