package com.example.mapping.restapimanytoonedemo.controllers;

import com.example.mapping.restapimanytoonedemo.models.Publication;
import com.example.mapping.restapimanytoonedemo.services.interfaces.PublicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/publications")
public class PublicationController {

    private PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @PostMapping
    public ResponseEntity<Publication> savePublication(@Valid @RequestBody Publication publication) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(publicationService.savePublication(publication));
    }

    @GetMapping("{id}")
    public ResponseEntity<Publication> getPublicationById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(publicationService.getPublicationById(id));
    }

    @GetMapping
    public ResponseEntity<List<Publication>> getAllPublications(@RequestParam(required = false) String title) {
        List<Publication> publications = new ArrayList<>();

        if (title == null)
            publications.addAll(publicationService.getAllPublications());
        else
            publications.addAll(publicationService.getPublicationByTitleContaining(title));

        if (publications.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();

        return ResponseEntity.ok(publications);
    }

    @PutMapping("{id}")
    public ResponseEntity<Publication> updatePublication(@PathVariable Long id, @Valid @RequestBody Publication publication) {
        return ResponseEntity.ok(publicationService.updatePublication(id, publication));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePublication(@PathVariable Long id) {
        publicationService.deletePublication(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllPublications() {
        publicationService.deleteAllPublications();

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}