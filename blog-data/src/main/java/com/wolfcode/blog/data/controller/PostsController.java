package com.wolfcode.blog.data.controller;

import com.wolfcode.blog.data.domain.Posts;
import com.wolfcode.blog.data.model.post.PostAndComments;
import com.wolfcode.blog.data.model.post.PostDetails;
import com.wolfcode.blog.data.model.post.PostRequest;
import com.wolfcode.blog.data.model.post.PostResponse;
import com.wolfcode.blog.data.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostsController {

    private final PostService postService;

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public String createPost(@RequestBody PostRequest postRequest) {
        postService.createPost(postRequest);
        return "Posted!!";
    }

    @GetMapping("/{id}")
    public Posts getPostById(@PathVariable("id")Long id){

        return postService.getPostById(id);
    }

    @GetMapping("/home")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> getAllPosts() {
        return postService.getAllPosts();
    }

    @PutMapping("/update/{postUuid}")
    @ResponseStatus(HttpStatus.OK)
    public PostRequest updatePost(@PathVariable("postUuid") String postUuid, @RequestBody PostRequest postRequest) {
        return postService.updatePost(postUuid, postRequest);
    }

    @DeleteMapping("/delete/{postUuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deletePost(@PathVariable String postUuid) {
        postService.deletePost(postUuid);
        return "No Content!";
    }

    @GetMapping("withComments/{postUuid}")
    @ResponseStatus(HttpStatus.OK)
    public PostAndComments getPostByPostUuid(@PathVariable String postUuid) {
        return postService.getPostByPostUuid(postUuid);
    }

    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDetails> getPostDetails() {
        return postService.getPostDetails();
    }

    @GetMapping("category/{category}")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> getByCategory(@PathVariable String category) {
        return postService.getByCategory(category);
    }


}
