package com.example.Blogapi.repository;

import com.example.Blogapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment , Long> {

     List<Comment> findByPostId(long id);
    //List<Comment> findByPostIdAndEmail(long id, String Email);
    //List<Comment> findByEmail(String email);

   // List<Comment> findByName(String name);
}
