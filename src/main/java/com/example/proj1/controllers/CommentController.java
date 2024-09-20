package com.example.proj1.controllers;

import com.example.proj1.models.Comment;
import com.example.proj1.models.CommentRequest;
import com.example.proj1.models.Post;
import com.example.proj1.repositories.PostRepository;
import com.example.proj1.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addComment(@RequestBody CommentRequest commentRequest) {
        Optional<Post> postOptional = postRepository.findById(commentRequest.getPostId());
        if (!postOptional.isPresent()) {
            return ResponseEntity.status(404).body("Post not found");
        }

        Comment comment = new Comment();
        comment.setText(commentRequest.getText());
        comment.setPost(postOptional.get());
        comment.setAuthor(commentRequest.getAuthor()); // Assume you set this from authenticated user details

        commentService.addComment(comment);
        return ResponseEntity.ok("Comment added successfully");
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (!postOptional.isPresent()) {
            return ResponseEntity.status(404).body(null);
        }

        List<Comment> comments = commentService.getCommentsForPost(postOptional.get());
        return ResponseEntity.ok(comments);
    }
}

