package com.example.mapping.restapimanytoonedemo.services;

import com.example.mapping.restapimanytoonedemo.exceptions.ResourceNotFoundException;
import com.example.mapping.restapimanytoonedemo.models.Comment;
import com.example.mapping.restapimanytoonedemo.repositories.CommentRepository;
import com.example.mapping.restapimanytoonedemo.repositories.PublicationRepository;
import com.example.mapping.restapimanytoonedemo.services.interfaces.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PublicationRepository publicationRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PublicationRepository publicationRepository) {
        this.commentRepository = commentRepository;
        this.publicationRepository = publicationRepository;
    }

    @Override
    public Comment saveComment(Long publicationId, Comment comment) {
        return publicationRepository.findById(publicationId)
                .map(pub -> {
                    comment.setPublication(pub);

                    return commentRepository.save(comment);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Publication", "id", publicationId));
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
    }

    @Override
    public List<Comment> getAllCommentsByPublicationId(Long publicationId) {
        return commentRepository.findByPublicationId(publicationId);
    }

    @Override
    public Comment updateComment(Long id, Comment comment) {
        return commentRepository.findById(id)
                .map(com -> {
                    com.setContent(comment.getContent());

                    return commentRepository.save(com);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void deleteAllCommentsOfPublication(Long publicationId) {
        if (!publicationRepository.existsById(publicationId))
            throw new ResourceNotFoundException("Publication", "id", publicationId);

        commentRepository.deleteByPublicationId(publicationId);
    }
}