package com.example.proj1.controllers;

import com.example.proj1.models.Post;
import com.example.proj1.models.PostDTO;
import com.example.proj1.models.User;
import com.example.proj1.repositories.PostRepository;
import com.example.proj1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    // View to list all blog posts
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return ResponseEntity.ok(posts);
    }

    // View to display a single blog post by ID
    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            return ResponseEntity.ok(post.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Show the form for creating a new post
    private boolean isAuthenticated(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && passwordMatches(password, user.getPassword());
    }
    private boolean passwordMatches(String rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostDTO postDTO,
                                           @RequestParam String username,
                                           @RequestParam String password) {
        if (!isAuthenticated(username, password)) {
            return ResponseEntity.status(403).body("Unauthorized to create post");
        }

        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setAuthor(postDTO.getAuthor());

        Post savedPost = postRepository.save(post);
        return ResponseEntity.status(200).body("Ok");
    }

    // Update Post
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id,
                                             @RequestBody PostDTO postDTO,
                                             @RequestParam String username,
                                             @RequestParam String password) {
        if (!isAuthenticated(username, password)) {
            return ResponseEntity.status(403).body("Unauthorized to edit post");
        }

        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isPresent() ) {
            existingPost.get().setTitle(postDTO.getTitle());
            existingPost.get().setContent(postDTO.getContent());
            postRepository.save(existingPost.get());
            return ResponseEntity.ok("Post updated successfully");
        }

        return ResponseEntity.status(404).body("Post not found or unauthorized");
    }

    // Delete Post
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id,
                                             @RequestParam String username,
                                             @RequestParam String password) {
        if (!isAuthenticated(username, password)) {
            return ResponseEntity.status(403).body("Unauthorized to delete post");
        }

        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isPresent() ) {
            postRepository.delete(existingPost.get());
            return ResponseEntity.ok("Post deleted successfully");
        }

        return ResponseEntity.status(404).body("Post not found or unauthorized");
    }
}
