package org.lmh.post.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.lmh.post.application.interfaces.CommentRepository;
import org.lmh.post.domain.Post;
import org.lmh.post.domain.comment.Comment;
import org.lmh.post.repository.entity.comment.CommentEntity;
import org.lmh.post.repository.jpa.JpaCommentRepository;
import org.lmh.post.repository.jpa.JpaPostRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;
    private final JpaPostRepository jpaPostRepository;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        Post targetPost = comment.getPost();
        CommentEntity commentEntity = new CommentEntity(comment);

        // id 값이 없으면 UPDATE
        if(comment.getId() != null) {
            jpaCommentRepository.updateCommentEntity(commentEntity);
            return commentEntity.toComment();
        }

        commentEntity = jpaCommentRepository.save(commentEntity);
        jpaPostRepository.increaseCommentCount(targetPost.getId());
        return commentEntity.toComment();
    }

    @Override
    public Comment findById(Long id) {
        CommentEntity commentEntity = jpaCommentRepository.findById(id).orElseThrow();
        return commentEntity.toComment();
    }
}
