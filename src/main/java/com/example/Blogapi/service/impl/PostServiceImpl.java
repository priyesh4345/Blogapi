package com.example.Blogapi.service.impl;

import com.example.Blogapi.entity.Post;
import com.example.Blogapi.exception.ResourceNotFoundException;
import com.example.Blogapi.payload.PostDto;
import com.example.Blogapi.repository.PostRepository;
import com.example.Blogapi.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createpost(PostDto postDto) {
        //convert dto to entity
        Post post = maptoentity(postDto);
        Post save = postRepository.save(post);
        //convert entity to dto
        PostDto dto = maptodto(save);
        return dto;
    }

    @Override
    public PostDto getPost(long id) {
        Post post = postRepository.findById(id).orElseThrow(

                () -> new ResourceNotFoundException(id)
        );
        PostDto dto = maptodto(post);
        return dto;
    }

/*    @Override
    public List<PostDto> getAllposts() {
        List<Post> posts = postRepository.findAll();

        List<PostDto> postDtos = posts.stream().map(post -> maptodto(post)).collect(Collectors.toList());

        return postDtos;
    }*/

    @Override
    public void deletepost(long id) {
        postRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException(id)
        );
        postRepository.deleteById(id);
    }

    @Override
    public PostDto updatepost(long id, PostDto postDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );
        Post updatecontent = maptoentity(postDto);
        updatecontent.setId(post.getId());
        Post save = postRepository.save(updatecontent);
        return maptodto(save);

    }

    @Override
    public List<PostDto> getAll(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);

        Page<Post> posts = postRepository.findAll(pageable);
        //convert page to list to applied in stream
        List<Post> content = posts.getContent();
        List<PostDto> postDtos = content.stream().map(post -> maptodto(post)).collect(Collectors.toList());

        return postDtos;
    }






    //convert entity to dto
    Post maptoentity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }

    //convert dto to entity
          PostDto maptodto(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());
        return postDto;
    }


}
