package com.example.ass2.services;

import com.example.ass2.models.Post;
import com.example.ass2.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository; // Assume you have a PostRepository

    @Cacheable(value = "postsCache", key = "'posts'", unless = "#result == null")
    public List<Post> getAllPosts() {
        // Simulate a delay for demonstration purposes
        try {
            Thread.sleep(2000); // Simulate a delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return postRepository.findAll(); // Fetch posts from the database
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }
}

