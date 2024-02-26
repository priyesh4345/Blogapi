package com.example.Blogapi.controller;

import com.example.Blogapi.payload.CommentDto;
import com.example.Blogapi.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Stack;
//http://localhost:8080/api/post
@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentServic){
        this.commentService = commentServic;
    }
    //9
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createcomment(@PathVariable(value = "postId") long postId,
                                                    @RequestBody CommentDto commentDto){

        CommentDto saved = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(saved , HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> findCommentByPostId(@PathVariable(value = "postId") long postId){
        List<CommentDto> dtos = commentService.findCommentByPostId(postId);
        return dtos;
    }
    @DeleteMapping ("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable(value = "postId") long postId
    , @PathVariable (value = "id") long id){
        commentService.deleteCommentById(postId,id);
        return new ResponseEntity<>("comment is deleted",HttpStatus.OK);
    }
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentByid(@PathVariable(value = "id") long id,@PathVariable(value = "postId")long postId){
       CommentDto dto =  commentService.getCommentById(id , postId);
       return new ResponseEntity<>(dto , HttpStatus.OK);
    }
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "id") long commentid,
                                                    @PathVariable(value = "postId")long postId,
                                                    @RequestBody CommentDto commentDto){
        CommentDto updateComment =  commentService.updateComment(commentid , postId , commentDto);
        return new ResponseEntity<>(updateComment , HttpStatus.OK);
    }
}


