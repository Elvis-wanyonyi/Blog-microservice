package com.wolfcode.blog.data.model.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    private String category;
    private String title;
    private String content;
    private LocalDateTime postedAt;

    private int comments;
}
