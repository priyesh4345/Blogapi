package com.example.Blogapi.service;

import com.example.Blogapi.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto getCommentById(long id, long postid);


    CommentDto createComment(long postId , CommentDto commentDto);

    List<CommentDto> findCommentByPostId(long postId);

    void deleteCommentById(long postId, long id);

    CommentDto updateComment(long commentid, long postId, CommentDto commentDto);
}
