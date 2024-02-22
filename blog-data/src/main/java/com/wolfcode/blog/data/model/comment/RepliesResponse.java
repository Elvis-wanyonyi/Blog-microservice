package com.wolfcode.blog.data.model.comment;

import com.wolfcode.blog.data.domain.Replies;
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
public class RepliesResponse {
    private LocalDateTime at;
    private String reply;
    private List<Replies>replies;
}
