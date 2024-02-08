package com.wolfcode.comments.reository;

import com.wolfcode.comments.domain.Replies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepliesRepository extends JpaRepository<Replies, Long> {
    Replies findByReplyId(String replyId);


    void deleteByReplyId(String replyId);
}
