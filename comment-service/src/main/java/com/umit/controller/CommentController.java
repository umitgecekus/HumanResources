package com.umit.controller;

import com.umit.dto.request.AddCommentRequestDto;
import com.umit.dto.response.BasicResponse;
import com.umit.entity.Comment;
import com.umit.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.umit.constants.RestApiUrls.*;

@RestController
@RequestMapping(COMMENT)
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * Managerin comment eklemesi icin method
     * @param dto
     * @return
     */
    @PostMapping(ADD_COMMENT)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> addComment(@RequestBody AddCommentRequestDto dto){
        commentService.addComment(dto);
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Comment created")
                .data(true)
                .build());
    }

    /**
     * Yapilan yorumlari goruntulemek icin method
     * @return
     */

    @GetMapping(GET_ALL_COMMENTS)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<List<Comment>>> getAllComments(){
        return ResponseEntity.ok(BasicResponse.<List<Comment>>builder()
                .status(200)
                .message("Comments get")
                .data(commentService.getAllComments())
                .build());

    }

}
