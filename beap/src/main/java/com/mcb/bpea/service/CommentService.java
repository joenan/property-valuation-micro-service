package com.mcb.bpea.service;

import com.mcb.bpea.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto saveComment(CommentDto comment);
    CommentDto getCommentById(Long id);
    void deleteComment(Long id);
    List<CommentDto> getAllComments(int page, int size);
    List<CommentDto> getAllCommentsByUser(String username);
}