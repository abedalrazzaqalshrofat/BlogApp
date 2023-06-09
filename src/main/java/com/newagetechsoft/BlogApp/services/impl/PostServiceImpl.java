package com.newagetechsoft.BlogApp.services.impl;

import com.newagetechsoft.BlogApp.model.Post;
import com.newagetechsoft.BlogApp.payload.PostDto;
import com.newagetechsoft.BlogApp.repositories.PostRepository;
import com.newagetechsoft.BlogApp.services.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post result = postRepository.save(post);

        PostDto response = new PostDto();
        response.setId(result.getId());
        response.setTitle(result.getTitle());
        response.setDescription(result.getDescription());
        response.setContent(result.getContent());

        return response;
    }

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream().map(post -> {
            PostDto postDto = new PostDto();
            postDto.setId(post.getId());
            postDto.setTitle(post.getTitle());
            postDto.setDescription(post.getDescription());
            postDto.setContent(post.getContent());
            return postDto;
        }).collect(Collectors.toList());
    }
}
