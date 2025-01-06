package org.lmh.user.repository.jpa;

import org.lmh.user.application.dto.GetUserListResponseDto;
import org.lmh.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaUserListQueryRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT new org.lmh.user.application.dto.GetUserListResponseDto(u.name, u.profileImageUrl) " +
            "FROM UserRelationEntity ur " +
            "INNER JOIN UserEntity u ON ur.followingUserId = u.id " +
            "WHERE ur.followingUserId = :userId")
    List<GetUserListResponseDto> getFollowingUserList(Long userId);

    @Query(value = "SELECT new org.lmh.user.application.dto.GetUserListResponseDto(u.name, u.profileImageUrl) " +
            "FROM UserRelationEntity ur " +
            "INNER JOIN UserEntity u ON ur.followingUserId = u.id " +
            "WHERE ur.followerUserId = :userId")
    List<GetUserListResponseDto> getFollowerUserList(Long userId);
}
