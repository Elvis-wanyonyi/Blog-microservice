package com.wolfcode.postservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDetails {

    private String postId;
    private String title;
    private String content;
    private LocalDateTime postedAt;
    private String category;
}
