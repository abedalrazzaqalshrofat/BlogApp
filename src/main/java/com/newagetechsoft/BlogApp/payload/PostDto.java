package com.newagetechsoft.BlogApp.payload;

import com.newagetechsoft.BlogApp.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private long id;
    private String title;
    private String description;
    private String content;
    private LocalDateTime createdAt;
    private Set<CommentDto> comments = new HashSet<>();
}
