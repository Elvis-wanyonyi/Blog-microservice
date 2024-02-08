package com.wolfcode.postservice.repository;

import com.wolfcode.postservice.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {
//    Optional<Posts> findByPostId(String postId);

    void deleteByPostId(String postId);

    List<Posts> findPostByCategory(String category);

    Posts findByPostId(String postId);
}
