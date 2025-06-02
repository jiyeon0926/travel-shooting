package com.example.travelshooting.domain.comment.dto;

import com.example.travelshooting.domain.comment.entity.Comment;
import com.example.travelshooting.global.common.BaseDtoDataType;
import lombok.Getter;

@Getter
public class CommentResDto implements BaseDtoDataType {

    private Long id;

    private String content;

    public CommentResDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
    }
}
