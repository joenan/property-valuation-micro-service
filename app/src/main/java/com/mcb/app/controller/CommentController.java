package com.mcb.app.controller;


import com.mcb.app.service.CommentService;
import com.mcb.commons.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @PostMapping
    public CommentDto saveComment(@RequestBody CommentDto comment) {
        return commentService.saveComment(comment);
    }

    @GetMapping("/{id}")
    public CommentDto getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id);
    }

    @GetMapping("/user/{username}")
    public List<CommentDto> getAllCommentsByUser(@PathVariable String username) {
        return commentService.getAllCommentsByUser(username);
    }
    @GetMapping
    public List<CommentDto> getAllComments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return commentService.getAllComments(page, size);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}