package com.mcb.app.repository;


import com.mcb.commons.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByUserUsername(String username);
    List<Comment> findAllByPropertyValuationId(Long propertyId);
}