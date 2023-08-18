package com.newagetechsoft.BlogApp.payload;

import com.newagetechsoft.BlogApp.model.Comment;
import com.newagetechsoft.BlogApp.validation.TitleConstrain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private long id;

    @NotNull(message = "the description of post should not be empty or blank spaces")
    @NotBlank
    @TitleConstrain(message = "Title should be one of the following topics")
    private String title;

    @NotNull
    @NotBlank(message = "the description of post should not be empty or blank spaces")
    private String description;

    @NotNull
    @NotBlank(message = "the description of post should not be empty or blank spaces")
    private String content;

    private LocalDateTime createdAt;
    
    private Set<CommentDto> comments = new HashSet<>();
}
