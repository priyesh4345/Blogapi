package com.example.Blogapi.service.impl;

import com.example.Blogapi.entity.Comment;
import com.example.Blogapi.entity.Post;
import com.example.Blogapi.exception.ResourceNotFoundException;
import com.example.Blogapi.payload.CommentDto;
import com.example.Blogapi.repository.CommentRepository;
import com.example.Blogapi.repository.PostRepository;
import com.example.Blogapi.service.CommentService;
import javafx.geometry.Pos;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {


    private PostRepository postRepo;

    private CommentRepository commentRepo;

    public CommentServiceImpl(PostRepository postRepo,CommentRepository commentRepo){
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }


    @Override
    public CommentDto getCommentById(long id, long postId) {
        Post post = postRepo.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException(postId)
        );
        Comment comment = commentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );
        CommentDto commentDto = maptodto(comment);
        return commentDto;
    }

    //http://localhost.8080/api/posts/1/comments
    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(postId)
        );
        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getName());
        comment.setPost(post);
        Comment saveComment = commentRepo.save(comment);
        CommentDto dto = new CommentDto();
        dto.setId(saveComment.getId());
        dto.setName(saveComment.getName());
        dto.setEmail(saveComment.getEmail());
        dto.setBody(saveComment.getBody());
        return dto;
    }
    public List<CommentDto> findCommentByPostId(long postId){

        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(postId)
        );

        List<Comment> comments = commentRepo.findByPostId(postId);
        return comments.stream().map(comment -> maptodto(comment)).collect(Collectors.toList());
    }

    @Override
    public void deleteCommentById(long postId, long id) {
       Post post = postRepo.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException(postId)
        );
        Comment comment = commentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );
    commentRepo.deleteById(id);
    }

    @Override
    public CommentDto updateComment(long commentid, long postId, CommentDto commentDto) {
        Post post = postRepo.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException(postId)
        );
        Comment comment = commentRepo.findById(commentid).orElseThrow(
                () -> new ResourceNotFoundException(commentid)
        );
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment updateComment = commentRepo.save(comment);
        return maptodto(updateComment);
    }


    Comment maptoentity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }
    CommentDto maptodto(Comment comment){
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());
        dto.setBody(comment.getBody());
        return dto;
    }


}


