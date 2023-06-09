package com.newagetechsoft.BlogApp.services;

import com.newagetechsoft.BlogApp.payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();
}
