package com.newagetechsoft.BlogApp.repositories;

import com.newagetechsoft.BlogApp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
