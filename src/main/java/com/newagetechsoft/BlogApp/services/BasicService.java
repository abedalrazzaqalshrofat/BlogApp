package com.newagetechsoft.BlogApp.services;

import com.newagetechsoft.BlogApp.payload.PostDto;

import java.util.List;

public interface BasicService<T, S> {

    T createPost(PostDto postDto);
    T getPostById(S s);
    T updatePost(T dto, S s);
    void deleteById(S id);
    List<T> getAllPosts(int pageNumber, int pageSize);
}
