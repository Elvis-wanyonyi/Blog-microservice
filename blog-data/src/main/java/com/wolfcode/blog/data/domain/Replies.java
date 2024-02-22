package com.wolfcode.blog.data.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "replies")
public class Replies {

    @Id
    @SequenceGenerator(name = "replies_sequence",sequenceName ="replies_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "replies_sequence")
    private Long id;
    private String replyId;
    private LocalDateTime at;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String reply;
    private String commentUuid;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    private Comments comments;

}
