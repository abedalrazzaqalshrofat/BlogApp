package com.newagetechsoft.BlogApp.payload;

import com.newagetechsoft.BlogApp.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private long id;
    private String email;
    private String contentComment;
    private LocalDateTime createdAt;
    private Long postId;

    @Override
    public String toString() {
        return "CommentDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", contentComment='" + contentComment + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentDto)) return false;
        CommentDto that = (CommentDto) o;
        return id == that.id && Objects.equals(email, that.email) && Objects.equals(contentComment, that.contentComment) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, contentComment, createdAt);
    }
}
