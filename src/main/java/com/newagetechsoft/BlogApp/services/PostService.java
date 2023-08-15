package com.newagetechsoft.BlogApp.services;

import com.newagetechsoft.BlogApp.payload.ResponsePage;

public interface PostService<T, S> {

    T createPost(T postDto);
    T getPostById(S s);
    T updatePost(T dto, S s);
    void deleteById(S id);
    ResponsePage<T> getAllPost(int pageNumber, int pageSize, String sortBy, String sortDirection);
}
