package com.example.Blogapi.controller;

import com.example.Blogapi.payload.PostDto;
import com.example.Blogapi.service.PostService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private PostService postService;

    public PostController(PostService postService){
        this.postService=postService;
    }

    // this is use for sent data
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    //http://localhost:8080/api/post
    public ResponseEntity<PostDto> createpost(@RequestBody PostDto postDto){
        PostDto save = postService.createpost(postDto);
        return new ResponseEntity<>(save, HttpStatus.ACCEPTED);
    }

    //this is used for get data

    @GetMapping("/{id}")

    public ResponseEntity<PostDto> getPost(@PathVariable("id") long id){
        PostDto post = postService.getPost(id);
        return new ResponseEntity<>(post , HttpStatus.OK);
    }
    //this is use to get data in list
 /*   @GetMapping
    public List<PostDto> getAllposts(){
        List<PostDto> postdtos = postService.getAllposts();
        return postdtos;
    }*/
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletepost(@PathVariable("id") long id){
        postService.deletepost(id);
        return new ResponseEntity<>("post is deleted",HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatepost(@PathVariable("id") long id,@RequestBody PostDto postDto){
        PostDto dto = postService.updatepost(id, postDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping
    //http://localhost:8080/api/post?pageNo&pageSize&sortBy&sortDir=asc
    public List<PostDto> getAll(
            @RequestParam(value = "pageNo" , defaultValue = "0" , required = false) int pageNo,
            @RequestParam(value = "pageSize" , defaultValue = "5" ,required = false) int pageSize,
            @RequestParam(value = "sortBy" , defaultValue = "id" ,required = false) String sortBy,
            @RequestParam(value = "sortDir" , defaultValue = "asc" ,required = false) String sortDir
                                ){
        List<PostDto> postdto = postService.getAll(pageNo,pageSize,sortBy,sortDir);
        return postdto;
    }


}
