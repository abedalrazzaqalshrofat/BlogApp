package com.newagetechsoft.BlogApp.services;

import com.newagetechsoft.BlogApp.payload.PostDto;
import com.newagetechsoft.BlogApp.payload.ResponsePage;

import java.util.List;

public interface BasicService<T, S> {

    T createPost(PostDto postDto);
    T getPostById(S s);
    T updatePost(T dto, S s);
    void deleteById(S id);
    ResponsePage<T> getAllPosts(int pageNumber, int pageSize, String sortBy);
}
