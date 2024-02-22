package com.wolfcode.blog.data.service;

import com.wolfcode.blog.data.domain.Comments;
import com.wolfcode.blog.data.domain.Posts;
import com.wolfcode.blog.data.model.comment.CommentDetails;
import com.wolfcode.blog.data.model.comment.CommentRequest;
import com.wolfcode.blog.data.model.comment.CommentResponse;
import com.wolfcode.blog.data.repository.CommentRepository;
import com.wolfcode.blog.data.repository.PostRepository;
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
    private final PostRepository postRepository;



    @jakarta.transaction.Transactional
    public void addComment(Long postId, CommentRequest commentRequest) {
        Posts posts = postRepository.findById(postId).get();
        Comments comment = Comments.builder()
                .commentUuid(UUID.randomUUID().toString().substring(0, 7))
                .at(LocalDateTime.now())
                .posts(posts)
                .postUuid(commentRequest.getPostUuid())
                .content(commentRequest.getContent())
                .build();

        commentRepository.save(comment);
        log.info("Comment: {} added to post: {} at: {}",
                comment.getCommentUuid(), comment.getPostUuid(), comment.getAt());
    }


    public CommentResponse getCommentById(String commentUuid) {
        Comments comment = commentRepository.findCommentByCommentUuid(commentUuid);
        if (comment != null) {
            return CommentResponse.builder()
                    .content(comment.getContent())
                    .at(comment.getAt())
                    .build();
        } else throw new IllegalArgumentException("Comment not Found");

    }

    public List<CommentResponse> getCommentsOfAPost(String postUuid) {
        List<Comments> comments = commentRepository.findCommentsByPostUuid(postUuid);
        return comments.stream().map(this::mapToCommentResponse).toList();

    }

    private CommentResponse mapToCommentResponse(Comments comments) {

        return CommentResponse.builder()
                .content(comments.getContent())
                .at(comments.getAt())
                .build();
    }

    public int getTotalComments(String postUuid) {
        List<Comments> comments = commentRepository.findCommentsByPostUuid(postUuid);
        if (comments == null || comments.isEmpty()) {
            return 0;
        } else {
            return comments.size();
        }
    }


    public void deleteComment(String commentUuid) {

        commentRepository.deleteByCommentUuid(commentUuid);
        log.info("comment: {} has been deleted!!", commentUuid);
    }


    public List<CommentDetails> getAllComments() {
        List<Comments> comment = commentRepository.findAll();
        return comment.stream().map(this::mapAllToCommentsResponse).toList();
    }

    private CommentDetails mapAllToCommentsResponse(Comments comments) {
        return CommentDetails.builder()
                .postUuid(comments.getPostUuid())
                .commentUuid(comments.getCommentUuid())
                .content(comments.getContent())
                .at(comments.getAt())
                .build();
    }

    public Comments getCommentsById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("nNot comment found with that id"));
    }
}
