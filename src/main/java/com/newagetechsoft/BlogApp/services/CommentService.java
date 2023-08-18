package com.newagetechsoft.BlogApp.services;

import com.newagetechsoft.BlogApp.payload.CommentDto;
import com.newagetechsoft.BlogApp.payload.ResponsePage;

public interface CommentService {

    ResponsePage<CommentDto> getAllCommentsForPost(Long id);

    CommentDto createComment(CommentDto commentDto, Long postId);

}
