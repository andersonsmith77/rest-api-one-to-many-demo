package com.example.mapping.restapimanytoonedemo.repositories;

import com.example.mapping.restapimanytoonedemo.models.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPublicationId(Long publicationId);
    @Transactional
    void deleteByPublicationId(Long publicationId);
}