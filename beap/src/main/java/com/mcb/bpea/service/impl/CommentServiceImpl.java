package com.mcb.bpea.service.impl;

import com.mcb.bpea.converter.CommentConverter;
import com.mcb.bpea.dto.CommentDto;
import com.mcb.bpea.entities.Comment;
import com.mcb.bpea.entities.PropertyValuation;
import com.mcb.bpea.entities.User;
import com.mcb.bpea.exception.ResourceNotFoundException;
import com.mcb.bpea.repository.CommentRepository;
import com.mcb.bpea.repository.PropertyValuationRepository;
import com.mcb.bpea.repository.UserRepository;
import com.mcb.bpea.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final PropertyValuationRepository valuationRepository;

    private final CommentConverter commentConverter;

    @Override
    public CommentDto saveComment(CommentDto commentDto) {
        User user = userRepository.findByUsername(commentDto.getUsername()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        PropertyValuation propertyValuation = valuationRepository.findById(commentDto.getPropertyValuationId()).orElseThrow(()-> new ResourceNotFoundException("No property found"));

        Comment comment = commentConverter.toComment(commentDto);
        comment.setUser(user);
        comment.setPropertyValuation(propertyValuation);

        Comment savedComment = commentRepository.save(comment);
        return commentConverter.toCommentDto(savedComment);
    }

    @Override
    public CommentDto getCommentById(Long id) {
        return commentRepository.findById(id).map(commentConverter::toCommentDto)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
    }

    @Override
    public List<CommentDto> getAllComments(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> customerPage = commentRepository.findAll(pageable);
        return customerPage.getContent()
                .stream()
                .map(commentConverter::toCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getAllCommentsByUser(String username) {
        List<Comment> commentsByUser = commentRepository.findAllByUserUsername(username);
        return commentsByUser.stream()
                .map(commentConverter::toCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Comment with id " + id + " not found");
        }
        commentRepository.deleteById(id);
    }
}
