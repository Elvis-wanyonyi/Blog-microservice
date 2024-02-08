package com.wolfcode.comments.service;

import com.wolfcode.comments.domain.Comments;
import com.wolfcode.comments.model.CommentDetails;
import com.wolfcode.comments.model.CommentRequest;
import com.wolfcode.comments.model.CommentResponse;
import com.wolfcode.comments.reository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;



    @jakarta.transaction.Transactional
    public void addComment(CommentRequest commentRequest) {
//        Comments comment = Comments.builder()
//                .commentId(UUID.randomUUID().toString().substring(0,7))
//                .at(LocalDateTime.now())
//                .postId(commentRequest.getPostId())
//                .content(commentRequest.getContent())
//                .build();
        Comments comment = new Comments();
        comment.setCommentId(UUID.randomUUID().toString().substring(0,7));
        comment.setPostId(commentRequest.getPostId());
        comment.setContent(commentRequest.getContent());
        comment.setAt(LocalDateTime.now());

        commentRepository.save(comment);
        log.info("Comment: {} added to post: {} at: {}",
                comment.getCommentId(), comment.getPostId(), comment.getAt());
    }

    public CommentResponse getCommentById(String commentId) {
        Comments comment = commentRepository.findCommentByCommentId(commentId);
        if (comment != null){
            return CommentResponse.builder()
                    .content(comment.getContent())
                    .at(comment.getAt())
                    .build();
        }else throw new IllegalArgumentException("Comment not Found");

    }

    public List<CommentResponse> getCommentsOfAPost(String postId) {
        List<Comments>comments = commentRepository.findCommentsByPostId(postId);
        return comments.stream().map(this::mapToCommentResponse).toList();

    }

    private CommentResponse mapToCommentResponse(Comments comments) {

        return CommentResponse.builder()
                .content(comments.getContent())
                .at(comments.getAt())
                .build();
    }

    public int getTotalComments(String postId) {
        List<Comments> comments = commentRepository.findCommentsByPostId(postId);
        if (comments == null || comments.isEmpty()) {
            return 0; // Or a suitable default value
        } else {
            return comments.size();
        }
    }


    public void deleteComment(String commentId) {

        commentRepository.deleteByCommentId(commentId);
        log.info("comment: {} has been deleted!!", commentId);
    }


    public List<CommentDetails> getAllComments() {
        List<Comments> comment = commentRepository.findAll();
        //null check
        return comment.stream().map(this::mapAllToCommentsResponse).toList();
    }

    private CommentDetails mapAllToCommentsResponse(Comments comments) {
        return CommentDetails.builder()
                .postId(comments.getPostId())
                .commentId(comments.getCommentId())
                .content(comments.getContent())
                .at(comments.getAt())
                .build();
    }
}
