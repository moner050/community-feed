package org.lmh.fake;

import org.lmh.post.application.CommentService;
import org.lmh.post.application.PostService;
import org.lmh.post.application.interfaces.CommentRepository;
import org.lmh.post.application.interfaces.LikeRepository;
import org.lmh.post.application.interfaces.PostRepository;
import org.lmh.post.repository.FakeCommentRepository;
import org.lmh.post.repository.FakeLikeRepository;
import org.lmh.post.repository.FakePostRepository;
import org.lmh.user.application.UserRelationService;
import org.lmh.user.application.UserService;
import org.lmh.user.application.interfaces.UserRelationRepository;
import org.lmh.user.application.interfaces.UserRepository;
import org.lmh.user.repository.FakeUserRelationRepository;
import org.lmh.user.repository.FakeUserRepository;

public class FakeObjectFactory {

    private static final UserRepository fakeUserRepository = new FakeUserRepository();
    private static final UserRelationRepository fakeUserRelationRepository = new FakeUserRelationRepository();
    private static final PostRepository fakePostRepository = new FakePostRepository();
    private static final CommentRepository fakeCommentRepository = new FakeCommentRepository();
    private static final LikeRepository fakeLikeRepository = new FakeLikeRepository();

    private static final UserService userService = new UserService(fakeUserRepository);
    private static final UserRelationService userRelationService = new UserRelationService(userService, fakeUserRelationRepository);
    private static final PostService postService = new PostService(userService, fakePostRepository, fakeLikeRepository);
    private static final CommentService commentService = new CommentService(userService, postService, fakeCommentRepository, fakeLikeRepository);

    private FakeObjectFactory() {}

    public static UserService getUserService() {
        return userService;
    }

    public static UserRelationService getUserRelationService() {
        return userRelationService;
    }

    public static PostService getPostService() {
        return postService;
    }

    public static CommentService getCommentService() {
        return commentService;
    }

}
