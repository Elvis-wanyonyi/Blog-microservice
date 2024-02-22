package com.wolfcode.blog.data.controller;

import com.wolfcode.blog.data.model.comment.RepliesRequest;
import com.wolfcode.blog.data.model.comment.RepliesResponse;
import com.wolfcode.blog.data.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/replies")
public class RepliesController {

    private final ReplyService replyService;

    @PostMapping("/{commentId}/replies")
    public String addReplyOnComment(@PathVariable("commentId") Long commentId,
                                    @RequestBody RepliesRequest repliesRequest) {
        replyService.addReplyOnComment(commentId,repliesRequest);
        return "Done...";
    }

    @GetMapping("/{id}")
    public List<RepliesResponse> getCommentReplies(@PathVariable("id") String id){
        return replyService.getCommentReplies(id);
    }

    @DeleteMapping("/delete/{replyId}")
    public void deleteReply(@PathVariable("replyId") String replyId) {
        replyService.deleteReply(replyId);
    }


}
