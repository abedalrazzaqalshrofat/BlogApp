package com.newagetechsoft.BlogApp.controller;

import com.newagetechsoft.BlogApp.payload.CommentDto;
import com.newagetechsoft.BlogApp.payload.ResponsePage;
import com.newagetechsoft.BlogApp.services.CommentService;
import com.newagetechsoft.BlogApp.util.AppConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments/create")
    public ResponseEntity<CommentDto> createCommentForPost(
            @RequestBody CommentDto commentDto, @PathVariable("postId") Long postId){
        return new ResponseEntity<>(commentService.createComment(commentDto,postId), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<ResponsePage<CommentDto>> getAllCommentsForPost(
           @PathVariable("postId") Long postsId,
           @RequestParam(name = "pageNumber", required = false, defaultValue = AppConstant.DEFAULT_START_PAGE_NUMBER) int pageNumber,
           @RequestParam(name = "pageSize", required = false, defaultValue = AppConstant.DEFAULT_PAGE_SIZE) int pageSize,
           @RequestParam(name = "sortBy", required = false, defaultValue = AppConstant.DEFAULT_SORT_BY_PROPERTY) String sortBy,
           @RequestParam(name = "sortDir",required = false, defaultValue = AppConstant.DEFAULT_SORT_DIRECTION) String sortDir) {
        return new ResponseEntity<>(
                commentService.getAllCommentsForPost(postsId, pageNumber, pageSize, sortBy, sortDir),HttpStatus.OK
        );
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(
            @PathVariable("postId")    Long postId,
            @PathVariable("commentId") Long commentId){
        return new ResponseEntity<>(commentService.getCommentById(commentId,postId),HttpStatus.OK);
    }
}
