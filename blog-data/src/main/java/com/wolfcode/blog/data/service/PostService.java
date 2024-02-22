package com.wolfcode.blog.data.service;

import com.wolfcode.blog.data.domain.Posts;
import com.wolfcode.blog.data.model.post.PostAndComments;
import com.wolfcode.blog.data.model.post.PostDetails;
import com.wolfcode.blog.data.model.post.PostRequest;
import com.wolfcode.blog.data.model.post.PostResponse;
import com.wolfcode.blog.data.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final CommentService commentService;



    public void createPost(PostRequest postRequest) {
        Posts post = new Posts();
        post.setPostUuid((UUID.randomUUID().toString().substring(1,8).toUpperCase()));
        post.setCategory(postRequest.getCategory());
        post.setPostedAt(LocalDateTime.now());
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());

        postRepository.save(post);
        log.info("post created with id: "+ post.getPostUuid());

    }

    public List<PostResponse> getAllPosts() {
        List<Posts> post = postRepository.findAll();

        return post.stream()
               .map(this::mapToPostResponse).toList();
    }

    private PostResponse mapToPostResponse(Posts post) {
        return PostResponse.builder()
                .category(post.getCategory())
                .title(post.getTitle())
                .postedAt(post.getPostedAt())
                .content(post.getContent())
                .comments(commentService.getTotalComments(post.getPostUuid()))
                .build();
    }

    public PostRequest updatePost(String postUuid, PostRequest postRequest) {
        Posts post = postRepository.findByPostUuid(postUuid);

        return null;
    }

    public PostAndComments getPostByPostUuid(String postUuid) {

        Posts posts = postRepository.findByPostUuid(postUuid);

        return PostAndComments.builder()
                .category(posts.getCategory())
                .title(posts.getTitle())
                .content(posts.getContent())
                .postedAt(posts.getPostedAt())
                .comments(commentService.getCommentsOfAPost(postUuid))
                .build();

    }

    @Transactional
    public void deletePost(String postId) {
        Posts result = postRepository.findByPostUuid(postId);
        if (result != null) {
            postRepository.deleteByPostUuid(postId);
            log.info("post: {}", postId + " has been deleted" );
        } else throw new IllegalArgumentException("post doesn't exist");

    }

    public List<PostResponse> getByCategory(String category) {
        List<Posts> post = postRepository.findPostByCategory(category);
        return post.stream().map(this::mapPostToPostResponse).toList();
    }

    private PostResponse mapPostToPostResponse(Posts post) {
        return PostResponse.builder()
                .category(post.getCategory())
                .title(post.getTitle())
                .content(post.getContent())
                .postedAt(post.getPostedAt())
                .build();
    }

    public List<PostDetails> getPostDetails() {
        List<Posts>posts = postRepository.findAll();
        return posts.stream().map(this::mapToPostDetails).toList();
    }

    private PostDetails mapToPostDetails(Posts posts1) {
        return PostDetails.builder()
                .postUuid(posts1.getPostUuid())
                .category(posts1.getCategory())
                .title(posts1.getTitle())
                .content(posts1.getContent())
                .postedAt(posts1.getPostedAt())
                .build();
    }

    public Posts getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("post not found"));
    }


}

