package com.example.travelshooting.domain.comment.controller;

import com.example.travelshooting.domain.comment.dto.CommentReqDto;
import com.example.travelshooting.domain.comment.dto.CommentResDto;
import com.example.travelshooting.domain.comment.service.CommentService;
import com.example.travelshooting.global.common.CommonListResDto;
import com.example.travelshooting.global.common.CommonResDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posters/{posterId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //댓글 생성
    @PostMapping
    public ResponseEntity<CommonResDto<CommentResDto>> createComment(
            @PathVariable Long posterId,
            @Valid @RequestBody CommentReqDto commentReqDto
    ) {

        CommentResDto commentResDto  = commentService.createComment(posterId, commentReqDto.getContent());

        return new ResponseEntity<>(new CommonResDto<>("댓글 생성 완료", commentResDto), HttpStatus.CREATED);
    }

    // 댓글 전체 조회
    @GetMapping
    public ResponseEntity<CommonListResDto<CommentResDto>> findComments(@PathVariable Long posterId) {

        List<CommentResDto> comments = commentService.findComments(posterId);

        return new ResponseEntity<>(new CommonListResDto<>("댓글 전체 조회 완료", comments), HttpStatus.OK);
    }

    //댓글 수정
    @PatchMapping("/{commentId}")
    public ResponseEntity<CommonResDto<CommentResDto>> updateComment(
            @PathVariable Long posterId,
            @PathVariable Long commentId,
            @Valid @RequestBody CommentReqDto commentReqDto
    ) {

        CommentResDto commentResDto = commentService.updateComment(posterId, commentId, commentReqDto.getContent());

        return new ResponseEntity<>(new CommonResDto<>("댓글 수정 완료", commentResDto), HttpStatus.OK);
    }

    //댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long posterId,
            @PathVariable Long commentId
    ) {

        commentService.deleteComment(posterId, commentId);

        return new ResponseEntity<>("댓글 삭제 완료", HttpStatus.OK);
    }

}
