package com.wolfcode.postservice.controller;

import com.wolfcode.postservice.model.PostAndComments;
import com.wolfcode.postservice.model.PostDetails;
import com.wolfcode.postservice.model.PostRequest;
import com.wolfcode.postservice.model.PostResponse;
import com.wolfcode.postservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostsController {

    private final PostService postService;

    @PostMapping("/new")
    public String createPost(@RequestBody PostRequest postRequest) {
        postService.createPost(postRequest);
        return "Posted!!";
    }

    @GetMapping
    public List<PostResponse> getAllPosts() {
        return postService.getAllPosts();
    }

    @PutMapping("/update/{postId}")
    public PostRequest updatePost(@PathVariable String postId) {
        return postService.updatePost(postId);
    }

    @DeleteMapping("/delete/{postId}")
    public String deletePost(@PathVariable String postId) {
        postService.deletePost(postId);
        return "No Content!";
    }

    @GetMapping("/{postId}")
    public PostAndComments getPostById(@PathVariable String postId) {
        return postService.getPostById(postId);

    }

    @GetMapping("/posts")
    public List<PostDetails> getPostDetails(){

        return postService.getPostDetails();
    }

    @GetMapping("category/{category}")
    public List<PostResponse> getByCategory(@PathVariable String category) {
        return postService.getByCategory(category);
    }


}
