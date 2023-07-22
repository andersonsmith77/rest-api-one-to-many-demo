package com.example.mapping.restapimanytoonedemo.services;

import com.example.mapping.restapimanytoonedemo.exceptions.ResourceNotFoundException;
import com.example.mapping.restapimanytoonedemo.models.Publication;
import com.example.mapping.restapimanytoonedemo.repositories.PublicationRepository;
import com.example.mapping.restapimanytoonedemo.services.interfaces.PublicationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationServiceImpl implements PublicationService {

    private PublicationRepository publicationRepository;

    public PublicationServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    @Override
    public Publication savePublication(Publication publication) {
        return publicationRepository.save(publication);
    }

    @Override
    public Publication getPublicationById(Long id) {
        return publicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publication", "Id", id));
    }

    @Override
    public List<Publication> getAllPublications() {
        return publicationRepository.findAll();
    }

    @Override
    public List<Publication> getPublicationByTitleContaining(String title) {
        return publicationRepository.findByTitleContaining(title);
    }

    @Override
    public Publication updatePublication(Long id, Publication publication) {
        return publicationRepository.findById(id)
                .map(pub -> {
                    pub.setTitle(publication.getTitle());
                    pub.setContent(publication.getContent());
                    pub.setLikes(publication.getLikes());

                    return publicationRepository.save(pub);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
    }

    @Override
    public void deletePublication(Long id) {
        publicationRepository.deleteById(id);
    }

    @Override
    public void deleteAllPublications() {
        publicationRepository.deleteAll();
    }
}