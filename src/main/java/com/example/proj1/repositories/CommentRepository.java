package com.example.proj1.repositories;

import com.example.proj1.models.Comment;
import com.example.proj1.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}

