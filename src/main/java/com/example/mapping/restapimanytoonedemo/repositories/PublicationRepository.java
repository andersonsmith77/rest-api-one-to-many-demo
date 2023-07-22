package com.example.mapping.restapimanytoonedemo.repositories;

import com.example.mapping.restapimanytoonedemo.models.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    List<Publication> findByTitleContaining(String title);
}
