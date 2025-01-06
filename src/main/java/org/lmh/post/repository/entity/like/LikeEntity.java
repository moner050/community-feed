package org.lmh.post.repository.entity.like;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lmh.common.repository.entity.TimeBaseEntity;
import org.lmh.post.domain.Post;
import org.lmh.post.domain.comment.Comment;
import org.lmh.user.domain.User;

@Entity
@Table(name = "community_like")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LikeEntity extends TimeBaseEntity {

    @EmbeddedId
    private LikeIdEntity id;

    public LikeEntity(Post post, User likeUser) {
        this.id = new LikeIdEntity(post.getId(), likeUser.getId(), LikeTarget.POST.name());
    }

    public LikeEntity(Comment comment, User likeUser) {
        this.id = new LikeIdEntity(comment.getId(), likeUser.getId(), LikeTarget.COMMENT.name());
    }

}
