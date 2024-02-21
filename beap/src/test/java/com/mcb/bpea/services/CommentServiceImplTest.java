package com.mcb.bpea.services;

import com.mcb.bpea.converter.CommentConverter;
import com.mcb.bpea.dto.CommentDto;
import com.mcb.bpea.entities.Comment;
import com.mcb.bpea.entities.PropertyValuation;
import com.mcb.bpea.entities.User;
import com.mcb.bpea.repository.CommentRepository;
import com.mcb.bpea.repository.PropertyValuationRepository;
import com.mcb.bpea.repository.UserRepository;
import com.mcb.bpea.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PropertyValuationRepository valuationRepository;

    @Mock
    private CommentConverter commentConverter;

    @InjectMocks
    private CommentServiceImpl subject;


    @Test
    void saveComment_whenCommentsIsValid_thenReturnSavedComment() {

        //Given
        CommentDto commentDto = createSampleCommentDto();
        User user = createSampleUser();
        PropertyValuation propertyValuation = createSamplePropertyValuation();
        Comment comment = mock(Comment.class);

        //When
        when(userRepository.findByUsername(commentDto.getUsername())).thenReturn(Optional.of(user));
        when(valuationRepository.findById(commentDto.getPropertyValuationId())).thenReturn(Optional.of(propertyValuation));
        when(commentConverter.toComment(commentDto)).thenReturn(comment);
        when(commentRepository.save(comment)).thenReturn(comment);
        when(commentConverter.toCommentDto(comment)).thenReturn(commentDto);


        //Then
        CommentDto savedCommentDto = subject.saveComment(commentDto);

        assertNotNull(savedCommentDto);
        assertEquals(commentDto, savedCommentDto);

        verify(userRepository, times(1)).findByUsername(commentDto.getUsername());
        verify(valuationRepository, times(1)).findById(commentDto.getPropertyValuationId());
        verify(commentConverter, times(1)).toComment(commentDto);
        verify(comment, times(1)).setUser(user);
        verify(comment, times(1)).setPropertyValuation(propertyValuation);
        verify(commentRepository, times(1)).save(comment);
        verify(commentConverter, times(1)).toCommentDto(comment);
    }

    @Test
    void getCommentById_whenCommentExists_thenReturnComment() {

        //Given
        Long commentId = 1L;
        CommentDto commentDto = createSampleCommentDto();
        Comment comment = createSampleComment();

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));
        when(commentConverter.toCommentDto(comment)).thenReturn(commentDto);

        //When
        CommentDto retrievedCommentDto = subject.getCommentById(commentId);

        //Then
        assertNotNull(retrievedCommentDto);
        assertEquals(commentDto, retrievedCommentDto);

        verify(commentRepository, times(1)).findById(commentId);
        verify(commentConverter, times(1)).toCommentDto(comment);
    }

    @Test
    void getAllComments_whenCommentsExist_thenReturnAllComments() {

        //Given
        int page = 0;
        int size = 10;
        Comment comment = createSampleComment();
        CommentDto commentDto = createSampleCommentDto();
        Page<Comment> commentPage = new PageImpl<>(Collections.singletonList(comment));

        when(commentRepository.findAll(any(Pageable.class))).thenReturn(commentPage);
        when(commentConverter.toCommentDto(comment)).thenReturn(commentDto);

        //When
        List<CommentDto> result = subject.getAllComments(page, size);

        //Then
        assertEquals(1, result.size());
        assertEquals(commentDto, result.get(0));

        verify(commentRepository, times(1)).findAll(any(Pageable.class));
        verify(commentConverter, times(1)).toCommentDto(comment);
    }

    @Test
    void getAllCommentsByUser_whenCommentsExists_thenReturnComments() {

        //Given
        String username = "testUser";
        Comment comment = createSampleComment();
        CommentDto commentDto = createSampleCommentDto();

        when(commentRepository.findAllByUserUsername(username)).thenReturn(Collections.singletonList(comment));
        when(commentConverter.toCommentDto(comment)).thenReturn(commentDto);

        //When and Then
        assertEquals(1, subject.getAllCommentsByUser(username).size());

        verify(commentRepository, times(1)).findAllByUserUsername(username);
        verify(commentConverter, times(1)).toCommentDto(comment);
    }

    @Test
    void deleteComment_whenCommentsExists_thenReturnComment() {

        //Given
        Long commentId = 1L;
        Comment comment = createSampleComment();

        when(commentRepository.existsById(commentId)).thenReturn(true);

        //When and Then
        subject.deleteComment(commentId);

        verify(commentRepository, times(1)).existsById(commentId);
        verify(commentRepository, times(1)).deleteById(commentId);
    }

    private CommentDto createSampleCommentDto() {
        return CommentDto.builder()
                .id(1L)
                .date(LocalDateTime.now())
                .username("testUser")
                .commentText("Test comment text")
                .propertyValuationId(123L)
                .build();
    }

    private User createSampleUser() {
        return User.builder().id(1L).username("testUser").build();
    }

    private PropertyValuation createSamplePropertyValuation() {
        return PropertyValuation.builder().id(123L).build();
    }

    private Comment createSampleComment() {
        return Comment.builder()
                .id(1L)
                .date(LocalDateTime.now())
                .user(createSampleUser())
                .commentText("Test comment text")
                .propertyValuation(createSamplePropertyValuation())
                .build();
    }
}
