package com.newagetechsoft.BlogApp.payload;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {

    private long id;
    private String email;
    private String contentComment;
    private LocalDateTime createdAt;
    private long postId;

}
