package org.lmh.post.application;

import org.lmh.fake.FakeObjectFactory;
import org.lmh.post.application.dto.CreateCommentRequestDto;
import org.lmh.post.application.dto.CreatePostRequestDto;
import org.lmh.post.domain.Post;
import org.lmh.post.domain.content.PostPublicationState;
import org.lmh.user.application.UserService;
import org.lmh.user.application.dto.CreateUserRequestDto;
import org.lmh.user.domain.User;

public class PostApplicationTestTemplate {
    final UserService userService = FakeObjectFactory.getUserService();
    final PostService postService = FakeObjectFactory.getPostService();
    final CommentService commentService = FakeObjectFactory.getCommentService();

    final User user = userService.createUser(new CreateUserRequestDto("test1", ""));
    final User otherUser = userService.createUser(new CreateUserRequestDto("test1", ""));

    CreatePostRequestDto postRequestDto = new CreatePostRequestDto(user.getId(), "this is test content", PostPublicationState.PUBLIC);
    final Post post = postService.createPost(postRequestDto);

    final String commentContentText = "this is test comment";
    CreateCommentRequestDto commentRequestDto = new CreateCommentRequestDto(post.getId(), user.getId(), "this is test comment");
}
