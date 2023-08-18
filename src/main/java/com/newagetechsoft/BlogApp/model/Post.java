package com.newagetechsoft.BlogApp.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "post", uniqueConstraints = { })
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "content", nullable = false)
    private String content;
    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY,cascade = {CascadeType.ALL},orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", comments=" + comments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        if (!super.equals(o)) return false;
        Post post = (Post) o;
        return Objects.equals(title, post.title) && Objects.equals(description, post.description)
                && Objects.equals(content, post.content) && Objects.equals(comments, post.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, description, content, comments);
    }
}
