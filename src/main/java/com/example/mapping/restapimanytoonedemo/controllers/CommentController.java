package com.example.mapping.restapimanytoonedemo.controllers;

import com.example.mapping.restapimanytoonedemo.models.Comment;
import com.example.mapping.restapimanytoonedemo.services.interfaces.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/publications/{publicationId}/comments")
    public ResponseEntity<Comment> saveComment(@PathVariable("publicationId") Long publicationId, @RequestBody Comment comment) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commentService.saveComment(publicationId, comment));
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getCommentsById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @GetMapping("/publications/{publicationId}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsByPublicationId(@PathVariable("publicationId") Long publicationId) {
        return ResponseEntity.ok(commentService.getAllCommentsByPublicationId(publicationId));
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") Long id, @RequestBody Comment comments) {
        return ResponseEntity.ok(commentService.updateComment(id, comments));
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @DeleteMapping("/publications/{publicationId}/comments")
    public ResponseEntity<String> deleteAllCommentsOfPublication(@PathVariable Long publicationId) {
        commentService.deleteAllCommentsOfPublication(publicationId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}