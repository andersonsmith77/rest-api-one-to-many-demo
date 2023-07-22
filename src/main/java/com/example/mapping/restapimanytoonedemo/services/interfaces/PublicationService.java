package com.example.mapping.restapimanytoonedemo.services.interfaces;

import com.example.mapping.restapimanytoonedemo.models.Publication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PublicationService {
    Publication savePublication(Publication publication);
    Publication getPublicationById(Long id);
    List<Publication> getAllPublications();
    List<Publication> getPublicationByTitleContaining(String title);
    Publication updatePublication(Long id, Publication publication);
    void deletePublication(Long id);
    void deleteAllPublications();
}
