package com.wolfcode.postservice.feignClients;

import com.wolfcode.postservice.model.CommentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "COMMENTS", path = "/comment/")
public interface CommentClient {

    @GetMapping("/count/{postId}")
    public int getTotalComments(@PathVariable("postId") String postId);


    @GetMapping("/{postId}")
    public List<CommentResponse> getCommentsOfAPost(@PathVariable("postId") String postId);
}
