package com.wolfcode.postservice.service;

import com.wolfcode.postservice.domain.Posts;
import com.wolfcode.postservice.feignClients.CommentClient;
import com.wolfcode.postservice.model.*;
import com.wolfcode.postservice.repository.PostRepository;
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
    private final CommentClient commentClient;


    public void createPost(PostRequest postRequest) {
//        Posts post = Posts.builder()
//                .postId(UUID.randomUUID().toString().substring(1,8).toUpperCase())
//                .postedAt(LocalDateTime.now())
//                .category(postRequest.getCategory())
//                .title(postRequest.getTitle())
//                .content(postRequest.getContent())
//                .build();

        Posts post = new Posts();
        post.setPostId(UUID.randomUUID().toString().substring(1,8).toUpperCase());
        post.setCategory(postRequest.getCategory());
        post.setPostedAt(LocalDateTime.now());
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());

        postRepository.save(post);
        log.info("post created with id: "+ post.getPostId());

    }

    public List<PostResponse> getAllPosts() {
        List<Posts> post = postRepository.findAll();

        return post.stream()
               .map(posts -> mapToPostResponse(posts, commentClient.getTotalComments(posts.getPostId())))
               .toList();
    }

    private PostResponse mapToPostResponse(Posts post, int totalComments) {
        return PostResponse.builder()
                .category(post.getCategory())
                .title(post.getTitle())
                .postedAt(post.getPostedAt())
                .content(post.getContent())
                .comments(totalComments)
                .build();
    }

    public PostRequest updatePost(String postId) {

        return null;
    }

    public PostAndComments getPostById(String postId) {

        Posts posts = postRepository.findByPostId(postId);
        List<CommentResponse>comments = commentClient.getCommentsOfAPost(postId);

        return PostAndComments.builder()
                .category(posts.getCategory())
                .title(posts.getTitle())
                .content(posts.getContent())
                .postedAt(posts.getPostedAt())
                .comments(comments)
                .build();

    }

    @Transactional
    public void deletePost(String postId) {
        Posts result = postRepository.findByPostId(postId);
        if (result != null) {
            postRepository.deleteByPostId(postId);
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
                .postId(posts1.getPostId())
                .category(posts1.getCategory())
                .title(posts1.getTitle())
                .content(posts1.getContent())
                .postedAt(posts1.getPostedAt())
                .build();
    }

}
//
//@Service
//public class YourService {
//    private final CommentServiceClient commentServiceClient;
//    private final PostRepository postRepository;
//
//    @Autowired
//    public YourService(CommentServiceClient commentServiceClient, PostRepository postRepository) {
//        this.commentServiceClient = commentServiceClient;
//        this.postRepository = postRepository;
//    }
//
//    public List<PostResponse> getAllPosts() {
//        List<Posts> posts = postRepository.findAll();
//        return posts.stream()
//                .map(post -> mapToPostResponse(post, commentServiceClient.getTotalComments(post.getId())))
//                .toList();
//    }
//
//    private PostResponse mapToPostResponse(Posts post, int commentCount) {
//        return PostResponse.builder()
//                .category(post.getCategory())
//                .title(post.getTitle())
//                .postedAt(post.getPostedAt())
//                .content(post.getContent())
//                .commentCount(commentCount)  // Add comment count to the response
//                .build();
//    }
//}
