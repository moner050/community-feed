package org.lmh.common.idempotency.repository;

import lombok.RequiredArgsConstructor;
import org.lmh.common.idempotency.Idempotency;
import org.lmh.common.idempotency.IdempotencyRepository;
import org.lmh.common.idempotency.repository.entity.IdempotencyEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class IdempotencyRepositoryImpl implements IdempotencyRepository {

    private final JpaIdempotencyRepository jpaIdempotencyRepository;

    @Override
    public Idempotency getByKey(String key) {
        Optional<IdempotencyEntity> idempotencyEntity = jpaIdempotencyRepository.findByIdempotencyKey(key);
        return idempotencyEntity.map(IdempotencyEntity::toIdempotency).orElse(null);
    }

    @Override
    public void save(Idempotency idempotency) {
        jpaIdempotencyRepository.save(new IdempotencyEntity(idempotency));
    }
}
