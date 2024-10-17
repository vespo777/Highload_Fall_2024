package com.example.ass2.services;

import com.example.ass2.models.Comment;
import com.example.ass2.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository; // Assume you have a CommentRepository

    @Cacheable(value = "recentCommentsCache", key = "#postId")
    public List<Comment> getRecentComments(Long postId) {
        return commentRepository.findTop5ByPostIdOrderByCreatedDateDesc(postId);
    }
}
