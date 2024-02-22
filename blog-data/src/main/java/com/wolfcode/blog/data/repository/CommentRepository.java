package com.wolfcode.blog.data.repository;

import com.wolfcode.blog.data.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {


    Comments findCommentByCommentUuid(String commentUuid);


    List<Comments> findCommentsByPostUuid(String postUuid);

    void deleteByCommentUuid(String commentUuid);
}
