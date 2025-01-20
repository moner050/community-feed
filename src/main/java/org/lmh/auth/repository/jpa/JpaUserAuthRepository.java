package org.lmh.auth.repository.jpa;

import org.lmh.auth.repository.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserAuthRepository extends JpaRepository<UserAuthEntity, String> {
}
