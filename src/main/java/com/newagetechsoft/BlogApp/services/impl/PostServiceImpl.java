
package com.newagetechsoft.BlogApp.services.impl;

import com.newagetechsoft.BlogApp.exception.ResourceNotFoundException;
import com.newagetechsoft.BlogApp.model.Comment;
import com.newagetechsoft.BlogApp.model.Post;
import com.newagetechsoft.BlogApp.payload.CommentDto;
import com.newagetechsoft.BlogApp.payload.PostDto;
import com.newagetechsoft.BlogApp.payload.ResponsePage;
import com.newagetechsoft.BlogApp.repositories.PostRepository;
import com.newagetechsoft.BlogApp.services.PostService;
import com.newagetechsoft.BlogApp.util.MapDtoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService<PostDto,Long>, MapDtoEntity<Post,PostDto> {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapDtoToEntity(postDto);
        Post result = postRepository.save(post);
        PostDto response = new PostDto();
        response.setTitle(result.getTitle());
        response.setDescription(result.getDescription());
        response.setContent(result.getContent());

        return mapEntityToDto(result);
    }

    @Override
    public PostDto getPostById(Long postId) {
       Post post = postRepository.findById(postId)
               .orElseThrow(() -> new ResourceNotFoundException("Post","postId",String.valueOf(postId)));
       return mapEntityToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto dto, Long id) {
        Post post = postRepository.save(Optional.ofNullable(dto).map(this::mapDtoToEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Post","Post Id",String.valueOf(id))));
        return Optional.of(post).map(this::mapEntityToDto).orElse(dto);
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public ResponsePage<PostDto> getAllPost(int pageNumber, int pageSize, String sortBy, String sortDirection) {

        Sort sort = Sort.Direction.valueOf(sortDirection.toUpperCase()).isAscending() ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> page = postRepository.findAll(pageable);
        return setResponsePageContent(page);
    }

    private ResponsePage<PostDto> setResponsePageContent(Page<Post> page){
        ResponsePage<PostDto> responsePage = new ResponsePage<>();

        List<Post> postList = page.getContent();

        List<PostDto> postDtoList = postList.stream().map(this::mapEntityToDto)
                .collect(Collectors.toList());

        responsePage.setContentResponse(postDtoList);
        responsePage.setPageNumber(page.getNumber());
        responsePage.setPageSize(page.getSize());
        responsePage.setTotalPages(page.getTotalPages());
        responsePage.setTotalElement(page.getTotalElements());
        responsePage.setLast(page.isLast());
        return responsePage;
    }

    @Override
    public PostDto mapEntityToDto(Post post){
        return Optional.ofNullable(post)
                .map(it -> {
                    PostDto postDto = new PostDto();
                    postDto.setId(it.getId());
                    postDto.setTitle(it.getTitle());
                    postDto.setContent(it.getContent());
                    postDto.setDescription(it.getDescription());
                    postDto.setCreatedAt(it.getCreatedAt());
                    postDto.setComments(it.getComments().stream()
                            .map(com -> new CommentDto(com.getId(),com.getEmail(),
                                    com.getContentComment(),com.getCreatedAt(), com.getPost().getId()))
                            .collect(Collectors.toSet()));
                    return postDto;
                }).orElse(new PostDto());
    }

    @Override
    public Post mapDtoToEntity(PostDto postDto){
        return Optional.ofNullable(postDto)
                .map(it -> {
                    Post post = new Post();
                    post.setId(it.getId());
                    post.setTitle(it.getTitle());
                    post.setContent(it.getContent());
                    post.setDescription(it.getDescription());
                    post.setCreatedAt(it.getCreatedAt());
                    return post;
                }).orElse(new Post());
    }
}
