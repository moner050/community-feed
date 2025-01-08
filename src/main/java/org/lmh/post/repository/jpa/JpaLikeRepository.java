package org.lmh.post.repository.jpa;

import org.lmh.post.repository.entity.like.LikeEntity;
import org.lmh.post.repository.entity.like.LikeIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, LikeIdEntity> {
}
