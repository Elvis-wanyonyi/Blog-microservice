package com.wolfcode.comments.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDetails {
    private String commentId;
    private LocalDateTime at;
    private String content;
    private String postId;

}
