package org.lmh.admin.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.lmh.admin.ui.dto.users.GetDailyRegisterUserResponseDto;
import org.lmh.admin.ui.query.UserStatsQueryRepository;
import org.lmh.common.utils.TimeCalculator;
import org.lmh.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserStatsQueryRepositoryImpl implements UserStatsQueryRepository {

    private final JPAQueryFactory queryFactory;
    private static final QUserEntity userEntity = QUserEntity.userEntity;

    @Override
    public List<GetDailyRegisterUserResponseDto> getDailyRegisterUserStats(int beforeDays) {
        return queryFactory
                .select(
                        Projections.fields(
                                GetDailyRegisterUserResponseDto.class,
                                userEntity.regDate.as("date"),
                                userEntity.count().as("count")
                        )
                )
                .from(userEntity)
                .where(userEntity.regDate.after(TimeCalculator.getDateDaysAgo(beforeDays)))
                .groupBy(userEntity.regDate)
                .orderBy(userEntity.regDate.asc())
                .fetch();
    }
}
