package com.newagetechsoft.BlogApp.services;

import com.newagetechsoft.BlogApp.payload.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Long postId);

}
