package com.wolfcode.blog.data.repository;

import com.wolfcode.blog.data.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {

    void deleteByPostUuid(String postUuid);

    List<Posts> findPostByCategory(String category);

    Posts findByPostUuid(String postUuid);
}
