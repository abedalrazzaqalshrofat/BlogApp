package com.newagetechsoft.BlogApp.services;

import com.newagetechsoft.BlogApp.payload.CommentDto;
import com.newagetechsoft.BlogApp.payload.ResponsePage;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Long postId);

    ResponsePage<CommentDto> getAllCommentsForPost(Long postId, int pageNumber, int pageSize,
                                                   String sortBy, String sortDir);

    CommentDto getCommentById(Long postId, Long commentId);


}
