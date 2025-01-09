package org.lmh.post.repository.entity.post;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lmh.post.domain.Post;
import org.lmh.user.domain.User;

@Entity
@Table(name = "community_user_post_queue")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserPostQueueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long postId;
    private Long authorId;

    public UserPostQueueEntity(User user, Post post) {
        User author = post.getAuthor();
        this.userId = user.getId();
        this.postId = post.getId();
        this.authorId = author.getId();
    }

    public UserPostQueueEntity(Long userId, Long postId, Long authorId) {
        this.userId = userId;
        this.postId = postId;
        this.authorId = authorId;
    }
}
