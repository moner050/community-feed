package org.lmh.user.domain;

import org.lmh.common.domain.PositiveIntegerCounter;

import java.util.Objects;

public class User {
    private final Long id;
    private final UserInfo info;
    private final PositiveIntegerCounter followingCounter;
    private final PositiveIntegerCounter followerCounter;

    public User(Long id, UserInfo userInfo) {
        this.id = id;
        this.info = userInfo;
        this.followingCounter = new PositiveIntegerCounter();
        this.followerCounter = new PositiveIntegerCounter();
    }

    public void follow(User targetUser) {
        if(targetUser.equals(this)) {
            throw new IllegalArgumentException("팔로워가 자기 자신입니다.");
        }

        followingCounter.increase();
        targetUser.increaseFollowerCount();
    }

    public void unfollow(User targetUser) {
        if(this.equals(targetUser)) {
            throw new IllegalArgumentException("언팔로우 대상이 자기 자신입니다.");
        }

        followingCounter.decrease();
        targetUser.decreaseFollowerCount();
    }

    /**
     * 디미터의 법칙으로 자신 소유의 객체와 대화해야 하는데
     * 너무 깊숙히 관여하면 캡슐화가 깨질 위험이 있음
     * 그래서 private 메소드를 이용해 해당 사항을 방지.
     */

    private void increaseFollowerCount() {
        followerCounter.increase();
    }

    private void decreaseFollowerCount() {
        followerCounter.decrease();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}