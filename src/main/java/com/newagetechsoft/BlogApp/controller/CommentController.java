package com.newagetechsoft.BlogApp.controller;

import com.newagetechsoft.BlogApp.payload.CommentDto;
import com.newagetechsoft.BlogApp.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts/")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments/create")
    public ResponseEntity<CommentDto> createCommentForPost(CommentDto commentDto, Long postId){
        return new ResponseEntity<>(commentService.createComment(commentDto,postId), HttpStatus.CREATED);
    }
}
