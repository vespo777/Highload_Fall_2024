package com.example.ass2.controllers;

import com.example.ass2.models.Comment;
import com.example.ass2.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/posts/{postId}/comments/recent")
    public List<Comment> getRecentComments(@PathVariable Long postId) {
        return commentService.getRecentComments(postId);
    }
}
