package com.umit.service;

import com.umit.dto.request.AddCommentRequestDto;
import com.umit.dto.response.ManagerResponseDto;
import com.umit.entity.Comment;
import com.umit.exception.CommentServiceException;
import com.umit.exception.ErrorType;
import com.umit.manager.ManagerManager;
import com.umit.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ManagerManager managerManager;
    public Boolean addComment(AddCommentRequestDto dto){
        ManagerResponseDto managerResponseDto = Optional.ofNullable(managerManager.findByToken(dto.getToken()).getBody())
                .orElseThrow(()->new CommentServiceException(ErrorType.MANAGER_NOT_FOUND));

        Optional<Comment> comment= commentRepository.findOptionalById(managerResponseDto.getId());

        if (comment.isPresent()){
            throw new CommentServiceException(ErrorType.MANAGER_ALREADY_HAVE_COMMENT);
        }
        commentRepository.save(
                Comment.builder()
                        .comment(dto.getComment())
                        .point(dto.getPoint())
                        .managerAvatar(managerResponseDto.getAvatar())
                        .managerName(managerResponseDto.getName())
                        .managerId(managerResponseDto.getId())
                        .managerSurname(managerResponseDto.getSurname())
                        .managerEmail(managerResponseDto.getEmail())
                        .createAt(System.currentTimeMillis())
                        .updateAt(System.currentTimeMillis())
                        .build()
        );

        return true;

    }


    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

}
