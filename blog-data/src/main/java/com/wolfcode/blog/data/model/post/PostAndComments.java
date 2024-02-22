package com.wolfcode.blog.data.model.post;

import com.wolfcode.blog.data.model.comment.CommentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostAndComments {
    private String category;
    private String title;
    private String content;
    private LocalDateTime postedAt;
    private List<CommentResponse>comments;
}
