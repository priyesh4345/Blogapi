package com.example.Blogapi.service;

import com.example.Blogapi.payload.PostDto;

import java.util.List;

public interface PostService {
    //this is method for create post
    public PostDto createpost(PostDto postDto);
    // this is methof to find by id
    PostDto getPost(long id);/**/

  /*  List<PostDto> getAllposts();*/

    void deletepost(long id);

    PostDto updatepost(long id, PostDto postDto);

    List<PostDto> getAll(int pageNo , int pageSize, String sortBy, String sortDir);
}
