package com.wolfcode.comments.controller;

import com.wolfcode.comments.model.RepliesRequest;
import com.wolfcode.comments.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/replies")
public class RepliesController {

    private final ReplyService replyService;

    @PostMapping("/add")
    public String addReplyOnComment(@RequestBody RepliesRequest repliesRequest) {
        replyService.addReplyOnComment(repliesRequest);
        return "Done...";
    }

    @DeleteMapping("/delete/{replyId}")
    public void deleteReply(@PathVariable("replyId") String replyId) {
        replyService.deleteReply(replyId);
    }


}
