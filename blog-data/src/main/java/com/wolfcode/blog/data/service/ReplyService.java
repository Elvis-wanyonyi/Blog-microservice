package com.wolfcode.blog.data.service;

import com.wolfcode.blog.data.domain.Comments;
import com.wolfcode.blog.data.domain.Replies;
import com.wolfcode.blog.data.model.comment.RepliesRequest;
import com.wolfcode.blog.data.model.comment.RepliesResponse;
import com.wolfcode.blog.data.repository.CommentRepository;
import com.wolfcode.blog.data.repository.RepliesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final RepliesRepository repliesRepository;
    private final CommentRepository commentRepository;


    @Transactional
    public void addReplyOnComment(Long commentId, RepliesRequest repliesRequest) {
        Comments comments = commentRepository.findById(commentId).get();
        Replies replies = Replies.builder()
                .replyId(UUID.randomUUID().toString().substring(6))
                .reply(repliesRequest.getReply())
                .comments(comments)
                .build();
        repliesRepository.save(replies);
    }

    public void deleteReply(String replyId) {
        Replies result = repliesRepository.findByReplyId(replyId);

        if (result != null){
            repliesRepository.deleteByReplyId(replyId);
        }else throw new IllegalArgumentException("Reply not found");
    }

    public List<RepliesResponse> getCommentReplies(String commentUuid) {
        List<Replies> replies = repliesRepository.findByCommentUuid(commentUuid);

        return replies.stream().map(this::mapToRepliesResponse).toList();
    }

    private RepliesResponse mapToRepliesResponse(Replies replies1) {
        return RepliesResponse.builder()
                .reply(replies1.getReply())
                .at(replies1.getAt())
                .build();
    }
}
