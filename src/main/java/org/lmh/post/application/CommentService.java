package org.lmh.post.application;

import org.lmh.post.application.dto.CreateCommentRequestDto;
import org.lmh.post.application.dto.LikeRequestDto;
import org.lmh.post.application.dto.UpdateCommentRequestDto;
import org.lmh.post.application.interfaces.CommentRepository;
import org.lmh.post.application.interfaces.LikeRepository;
import org.lmh.post.domain.Post;
import org.lmh.post.domain.comment.Comment;
import org.lmh.user.application.UserService;
import org.lmh.user.domain.User;

public class CommentService {

    private final UserService userService;
    private final PostService postService;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    public CommentService(UserService userService, PostService postService, CommentRepository commentRepository, LikeRepository likeRepository) {
        this.userService = userService;
        this.postService = postService;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }

    public Comment getComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Comment not found"));
    }

    public Comment createComment(CreateCommentRequestDto dto) {
        Post post = postService.getPost(dto.postId());
        User user = userService.getUser(dto.userId());

        Comment comment = Comment.createComment(post, user, dto.content());
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long id, UpdateCommentRequestDto dto) {
        Comment comment = getComment(dto.commentId());
        User user = userService.getUser(dto.userId());

        comment.updateComment(user, dto.content());
        return commentRepository.save(comment);
    }

    public void likeComment(LikeRequestDto dto) {
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(comment, user)) {
            return;
        }

        comment.like(user);
        likeRepository.like(comment, user);
    }

    public void unlikeComment(LikeRequestDto dto) {
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(comment, user)) {
            comment.unlike();
            likeRepository.unlike(comment, user);
        }
    }
}
