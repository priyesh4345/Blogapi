package com.example.Blogapi.repository;

import com.example.Blogapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post , Long> {
}
