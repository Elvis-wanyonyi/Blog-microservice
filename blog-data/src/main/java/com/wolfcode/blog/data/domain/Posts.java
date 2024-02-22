package com.wolfcode.blog.data.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "blog_posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String postUuid;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String title;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String content;
    private LocalDateTime postedAt;
    @Column(nullable = false)
    private String category;

    @OneToMany(mappedBy = "posts")
    private List<Comments> comments;

}
