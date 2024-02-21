package com.mcb.bpea.repository;

import com.mcb.bpea.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByUserUsername(String username);
}