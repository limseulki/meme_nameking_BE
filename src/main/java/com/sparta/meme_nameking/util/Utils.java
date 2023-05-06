package com.sparta.meme_nameking.util;

import com.sparta.meme_nameking.entity.Comment;
import com.sparta.meme_nameking.entity.Post;
import com.sparta.meme_nameking.entity.User;
import com.sparta.meme_nameking.exception.CustomException;
import com.sparta.meme_nameking.exception.ErrorCode;
import com.sparta.meme_nameking.repository.CommentRepository;
import com.sparta.meme_nameking.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
@Transactional
public class Utils {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    // 게시글 찾기
    public Post findPostById(Long id){
        return postRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.POST_NOT_FOUND));
    }

    // 댓글 찾기
    public Comment findCommentById(Long id){
        return commentRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
    }

    // 따봉킹 찾기
    public User getDdabongKing(){
        User DdabongKing = commentRepository.findAll()
                .stream()
                .max(Comparator.comparing(Comment::getDdabong)).get().getUser();
        return DdabongKing;
    }
}
