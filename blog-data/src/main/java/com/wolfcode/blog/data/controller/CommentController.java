package com.wolfcode.blog.data.controller;

import com.wolfcode.blog.data.domain.Comments;
import com.wolfcode.blog.data.model.comment.CommentDetails;
import com.wolfcode.blog.data.model.comment.CommentRequest;
import com.wolfcode.blog.data.model.comment.CommentResponse;
import com.wolfcode.blog.data.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/{postId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public String addComment(@PathVariable("postId") Long postId,
                             @RequestBody CommentRequest commentRequest) {
        commentService.addComment(postId,commentRequest);
        return "Sent!";
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDetails> getAllComments() {

        return commentService.getAllComments();
    }

    public Comments getCommentsById(@PathVariable("id")Long id){
        Comments comments = commentService.getCommentsById(id);
        return comments;
    }

    @GetMapping("details/{commentUuid}")
    @ResponseStatus(HttpStatus.OK)
    public CommentResponse getCommentById(@PathVariable String commentUuid) {
        return commentService.getCommentById(commentUuid);
    }

    @GetMapping("/{postUuid}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentResponse> getCommentsOfAPost(@PathVariable("postUuid") String postUuid) {

        return commentService.getCommentsOfAPost(postUuid);
    }

    @GetMapping("/count/{postUuid}")
    @ResponseStatus(HttpStatus.OK)
    public int getTotalComments(@PathVariable("postUuid") String postUuid) {

        return commentService.getTotalComments(postUuid);
    }

    @DeleteMapping("/delete/{commentUuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable("commentUuid") String commentUuid) {
        commentService.deleteComment(commentUuid);
    }
}
