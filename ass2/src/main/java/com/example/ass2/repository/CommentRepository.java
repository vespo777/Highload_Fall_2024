package com.example.ass2.repository;

import com.example.ass2.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);

    List<Comment> findTop5ByPostIdOrderByCreatedDateDesc(Long postId);
}

