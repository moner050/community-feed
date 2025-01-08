package org.lmh.post.application.interfaces;

import org.lmh.post.domain.comment.Comment;

import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);
    Comment findById(Long id);
}
