package com.newagetechsoft.BlogApp.services.impl;

import com.newagetechsoft.BlogApp.model.Comment;
import com.newagetechsoft.BlogApp.model.Post;
import com.newagetechsoft.BlogApp.payload.CommentDto;
import com.newagetechsoft.BlogApp.payload.ResponsePage;
import com.newagetechsoft.BlogApp.repositories.CommentRepository;
import com.newagetechsoft.BlogApp.repositories.PostRepository;
import com.newagetechsoft.BlogApp.services.CommentService;
import com.newagetechsoft.BlogApp.util.MapDtoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService, MapDtoEntity<Comment,CommentDto> {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }
    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId) {
        Post post = postRepository.findById(postId).orElse(new Post());
        commentDto.setPostId(postId);
        Comment comment = mapDtoToEntity(commentDto);
        comment = commentRepository.save(comment);
        return Optional.of(comment).map(this::mapEntityToDto).orElseGet(CommentDto::new);
    }

    @Override
    public ResponsePage<CommentDto> getAllCommentsForPost(Long postId,int pageNumber,
                                                          int pageSize,String sortBy, String sortDir) {
        Sort sort = Sort.Direction.ASC.name().equals(sortBy.toUpperCase())
                ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber,pageSize, sort);
        Page<Comment> page = commentRepository.findAll(pageable);

        return Optional.of(prepareResponsePage(page))
                .orElse(new ResponsePage<>());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        return mapEntityToDto(commentRepository.findById(commentId).orElse(new Comment()));
    }

    private ResponsePage<CommentDto> prepareResponsePage(Page<Comment> comments){
        List<CommentDto> content = comments
                .getContent().stream().map(this::mapEntityToDto).collect(Collectors.toList());
        ResponsePage<CommentDto> responsePage = new ResponsePage<>();
        responsePage.setPageNumber(comments.getNumber());
        responsePage.setPageSize(comments.getSize());
        responsePage.setTotalPages(comments.getTotalPages());
        responsePage.setContentResponse(content);
        responsePage.setLast(comments.isLast());
        return responsePage;
    }


    @Override
    public Comment mapDtoToEntity(CommentDto dto) {
        Comment comment = new Comment();
        return Optional.ofNullable(dto)
                .map(it -> {
                   Post post = postRepository.findById(dto.getPostId()).orElse(new Post());
                   comment.setPost(post);
                   comment.setId(dto.getId());
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
