package com.wolfcode.comments.controller;

import com.wolfcode.comments.model.CommentDetails;
import com.wolfcode.comments.model.CommentRequest;
import com.wolfcode.comments.model.CommentResponse;
import com.wolfcode.comments.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/add")
    public String addComment(@RequestBody CommentRequest commentRequest) {
        commentService.addComment(commentRequest);
        return "Sent!";
    }

    @GetMapping("/all")
    public List<CommentDetails> getAllComments() {

        return commentService.getAllComments();
    }

    @GetMapping("details/{commentId}")
    public CommentResponse getCommentById(@PathVariable String commentId) {
        return commentService.getCommentById(commentId);
    }

    @GetMapping("/{postId}")
    public List<CommentResponse> getCommentsOfAPost(@PathVariable("postId") String postId) {

        return commentService.getCommentsOfAPost(postId);
    }

    @GetMapping("/count/{postId}")
    public int getTotalComments(@PathVariable("postId") String postId) {

        return commentService.getTotalComments(postId);
    }

    @DeleteMapping("/delete/{commentId}")
    public void deleteComment(@PathVariable("commentId") String commentId) {
        commentService.deleteComment(commentId);
    }
}
