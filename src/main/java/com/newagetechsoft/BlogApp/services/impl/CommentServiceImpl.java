package com.newagetechsoft.BlogApp.services.impl;

import com.newagetechsoft.BlogApp.model.Comment;
import com.newagetechsoft.BlogApp.model.Post;
import com.newagetechsoft.BlogApp.payload.CommentDto;
import com.newagetechsoft.BlogApp.payload.ResponsePage;
import com.newagetechsoft.BlogApp.repositories.CommentRepository;
import com.newagetechsoft.BlogApp.services.CommentService;
import com.newagetechsoft.BlogApp.util.MapDtoEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService, MapDtoEntity<Comment,CommentDto> {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public ResponsePage<CommentDto> getAllCommentsForPost(Long id) {
        ResponsePage<CommentDto> responsePage = new ResponsePage<>();
        return responsePage;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId) {
        Comment comment = commentRepository.save(mapDtoToEntity(commentDto));
        return Optional.of(comment).map(this::mapEntityToDto).orElseGet(CommentDto::new);
    }

    @Override
    public Comment mapDtoToEntity(CommentDto dto) {
        Comment comment = new Comment();
        return Optional.ofNullable(dto)
                .map(it -> {
                   comment.setId(dto.getId());
                   Post post = new Post();
                   post.setId(dto.getPostId());
                   comment.setPost(post);
                   comment.setEmail(dto.getEmail());
                   comment.setCreatedAt(dto.getCreatedAt());
                   comment.setContentComment(dto.getContentComment());
                   return comment;
                }).orElseGet(Comment::new);
    }

    @Override
    public CommentDto mapEntityToDto(Comment entity) {
        CommentDto commentDto = new CommentDto();
        return Optional.ofNullable(entity)
                .map(it -> {
                    commentDto.setId(entity.getId());
                    commentDto.setPostId(entity.getPost().getId());
                    commentDto.setEmail(entity.getEmail());
                    commentDto.setCreatedAt(entity.getCreatedAt());
                    commentDto.setContentComment(entity.getContentComment());
                    return commentDto;
                }).orElseGet(CommentDto::new);
    }
}
