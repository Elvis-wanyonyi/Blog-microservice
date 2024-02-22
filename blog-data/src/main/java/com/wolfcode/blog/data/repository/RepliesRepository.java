package com.wolfcode.blog.data.repository;

import com.wolfcode.blog.data.domain.Replies;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepliesRepository extends JpaRepository<Replies, Long> {
    Replies findByReplyId(String replyId);


    @Transactional
    void deleteByReplyId(String replyId);

    List<Replies> findByCommentUuid(String commentUuid);
}
