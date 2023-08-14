package com.newagetechsoft.BlogApp.controller;

import com.newagetechsoft.BlogApp.model.Post;
import com.newagetechsoft.BlogApp.payload.PostDto;
import com.newagetechsoft.BlogApp.payload.ResponsePage;
import com.newagetechsoft.BlogApp.services.BasicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final BasicService<PostDto,Long> postService;

    public PostController(BasicService<PostDto,Long> postService) {
        this.postService = postService;
    }

    @PostMapping("createPost")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<ResponsePage<PostDto>> getAllPosts(
            @RequestParam(name = "pageNumber" ,defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize" ,defaultValue = "10") int pageSize
    ){
        return new ResponseEntity<>(postService.getAllPosts(pageNumber, pageSize),HttpStatus.OK);
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
