package com.newagetechsoft.BlogApp.repositories;

import com.newagetechsoft.BlogApp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


public interface PostRepository extends JpaRepository<Post,Long> {


}
