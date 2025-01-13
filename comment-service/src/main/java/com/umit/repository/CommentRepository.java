package com.umit.repository;

import com.umit.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    Optional<Comment> findOptionalById(Long id);

    Optional<Comment> findOptionalByManagerId(Long managerId);

}
