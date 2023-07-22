package com.example.mapping.restapimanytoonedemo.services.interfaces;

import com.example.mapping.restapimanytoonedemo.models.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    Comment saveComment(Long publicationId, Comment comment);
    Comment getCommentById(Long id);
    List<Comment> getAllCommentsByPublicationId(Long publicationId);
    Comment updateComment(Long id, Comment comment);
    void deleteComment(Long id);
    void deleteAllCommentsOfPublication(Long publicationId);
}