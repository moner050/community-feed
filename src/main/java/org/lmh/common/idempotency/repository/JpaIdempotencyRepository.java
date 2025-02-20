package org.lmh.common.idempotency.repository;

import org.lmh.common.idempotency.repository.entity.IdempotencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaIdempotencyRepository extends JpaRepository<IdempotencyEntity, Long> {

    Optional<IdempotencyEntity> findByIdempotencyKey(String key);
}
