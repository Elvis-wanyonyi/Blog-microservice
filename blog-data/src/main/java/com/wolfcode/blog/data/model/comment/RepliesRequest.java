package com.wolfcode.blog.data.model.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepliesRequest {

    private String commentUuid;
    private String reply;

}
