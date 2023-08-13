package com.newagetechsoft.BlogApp.services.impl;

import com.newagetechsoft.BlogApp.exception.ResourceNotFoundException;
import com.newagetechsoft.BlogApp.model.Post;
import com.newagetechsoft.BlogApp.payload.PostDto;
import com.newagetechsoft.BlogApp.repositories.PostRepository;
import com.newagetechsoft.BlogApp.services.BasicService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements BasicService<PostDto,Long> {

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
        response.setTitle(result.getTitle());
        response.setDescription(result.getDescription());
        response.setContent(result.getContent());

        return response;
    }

    @Override
    public PostDto getPostById(Long postId) {
       Post post = postRepository.findById(postId)
               .orElseThrow(() -> new ResourceNotFoundException("Post","postId",String.valueOf(postId)));
       return mapPostToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto dto, Long id) {
        Post post = postRepository.save(Optional.ofNullable(dto).map(it ->
             new Post(id,dto.getContent(),dto.getDescription(),dto.getTitle())
        ).orElseThrow(() -> new ResourceNotFoundException("Post","Post Id",String.valueOf(id))));
        return Optional.of(post).map(it -> new PostDto(post.getContent(), post.getTitle(), post.getDescription())).orElse(dto);
    }

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream().map(post -> {
            PostDto postDto = new PostDto();
            postDto.setTitle(post.getTitle());
            postDto.setDescription(post.getDescription());
            postDto.setContent(post.getContent());
            return postDto;
        }).collect(Collectors.toList());
    }

    private PostDto mapPostToDto(Post post){
        return Optional.ofNullable(post)
                .map(it -> {
                    PostDto postDto = new PostDto();
                    postDto.setTitle(it.getTitle());
                    postDto.setContent(it.getContent());
                    postDto.setDescription(it.getDescription());
                    return postDto;
                }).orElse(new PostDto());
    }

    private Post mapPostDtoToPost(PostDto postDto){
        return Optional.ofNullable(postDto)
                .map(it -> {
                    Post post = new Post();
                    post.setTitle(it.getTitle());
                    post.setContent(it.getContent());
                    post.setDescription(it.getDescription());
                    return post;
                }).orElse(new Post());
    }
}
