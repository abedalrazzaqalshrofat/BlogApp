package com.newagetechsoft.BlogApp.controller;

import com.newagetechsoft.BlogApp.payload.CommentDto;
import com.newagetechsoft.BlogApp.payload.ResponsePage;
import com.newagetechsoft.BlogApp.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<ResponsePage<CommentDto>> getAllCommentsFor(@PathVariable("postId") Long postId){
        return new ResponseEntity<>(commentService.getAllCommentsForPost(postId),HttpStatus.OK);
    }

    @PostMapping("posts/{postId}/comments/create")
    public ResponseEntity<CommentDto> createCommentForPost(CommentDto commentDto, Long postId){
        return new ResponseEntity<>(commentService.createComment(commentDto,postId), HttpStatus.CREATED);
    }
}
