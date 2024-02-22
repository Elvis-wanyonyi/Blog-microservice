package com.wolfcode.blog.data.model.comment;

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
    private String commentUuid;
    private LocalDateTime at;
    private String content;
    private String postUuid;

}
