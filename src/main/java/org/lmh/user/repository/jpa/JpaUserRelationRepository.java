package org.lmh.user.repository.jpa;

import org.lmh.user.repository.entity.UserRelationEntity;
import org.lmh.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {
}
