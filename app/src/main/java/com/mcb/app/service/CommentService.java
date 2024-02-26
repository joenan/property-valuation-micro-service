package com.mcb.app.service;

import com.mcb.commons.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto saveComment(CommentDto comment);
    CommentDto getCommentById(Long id);
    void deleteComment(Long id);
    List<CommentDto> getAllComments(int page, int size);
    List<CommentDto> getAllCommentsByUser(String username);
    List<CommentDto> getCommentByPropertyId(Long propertyId);
}