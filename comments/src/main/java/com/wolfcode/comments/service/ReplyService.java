package com.wolfcode.comments.service;

import com.wolfcode.comments.domain.Replies;
import com.wolfcode.comments.model.RepliesRequest;
import com.wolfcode.comments.reository.RepliesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final RepliesRepository repliesRepository;


    public void addReplyOnComment(RepliesRequest repliesRequest) {
        Replies replies = Replies.builder()
                .replyId(UUID.randomUUID().toString().substring(6))
                .reply(repliesRequest.getReply())
                .build();
        repliesRepository.save(replies);
    }

    public void deleteReply(String replyId) {
        Replies result = repliesRepository.findByReplyId(replyId);

        if (result != null){
            repliesRepository.deleteByReplyId(replyId);
        }else throw new IllegalArgumentException("Reply not found");
    }
}
