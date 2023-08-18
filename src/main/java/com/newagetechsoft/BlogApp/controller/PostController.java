package com.newagetechsoft.BlogApp.controller;

import com.newagetechsoft.BlogApp.exception.ResourceNotFoundException;
import com.newagetechsoft.BlogApp.payload.PostDto;
import com.newagetechsoft.BlogApp.payload.ResponsePage;
import com.newagetechsoft.BlogApp.services.PostService;
import com.newagetechsoft.BlogApp.util.AppConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService<PostDto,Long> postService;

    public PostController(PostService<PostDto,Long> postService) {
        this.postService = postService;
    }

    @PostMapping("createPost")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<ResponsePage<PostDto>> getAllPosts(
            @RequestParam(name = "pageNumber" ,defaultValue = AppConstant.DEFAULT_START_PAGE_NUMBER) int pageNumber,
            @RequestParam(name = "pageSize" ,defaultValue = AppConstant.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstant.DEFAULT_SORT_BY_PROPERTY) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstant.DEFAULT_SORT_DIRECTION) String sortDirection
    ){
        return new ResponseEntity<>(postService.getAllPost(pageNumber, pageSize,sortBy,sortDirection),HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Long postId){
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto, @PathVariable("postId") Long postId){
        return new ResponseEntity<>(postService.updatePost(postDto,postId),HttpStatus.OK);
    }
}
