package com.wolfcode.comments.reository;

import com.wolfcode.comments.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {


    Comments findCommentByCommentId(String commentId);


    List<Comments> findCommentsByPostId(String postId);

    void deleteByCommentId(String commentId);
}
