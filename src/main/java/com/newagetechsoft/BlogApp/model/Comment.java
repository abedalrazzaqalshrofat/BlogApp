package com.newagetechsoft.BlogApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    private String email;

    @Column
    private String contentComment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        if (!super.equals(o)) return false;
        Comment comment = (Comment) o;
        return Objects.equals(email, comment.email) && Objects.equals(contentComment, comment.contentComment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, contentComment);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "email='" + email + '\'' +
                ", contentComment='" + contentComment + '\'' +
                '}';
    }
}
