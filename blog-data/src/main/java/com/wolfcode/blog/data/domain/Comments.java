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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "comments")
public class Comments {

    @Id
    @SequenceGenerator(name = "comments_sequence",sequenceName ="comments_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comments_sequence")
    private Long id;
    private String commentUuid;
    private LocalDateTime at;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    @Column(nullable = false)
    private String postUuid;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "postsId", referencedColumnName = "id")
    private Posts posts;

    @OneToMany(mappedBy = "comments")
    private List<Replies> replies;
}
