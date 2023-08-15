package com.newagetechsoft.BlogApp.repositories;

import com.newagetechsoft.BlogApp.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
