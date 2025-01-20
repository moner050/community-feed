package org.lmh.acceptance.utils;

import groovy.util.logging.Slf4j;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Profile("test")
@Component
@Slf4j
public class DatabaseCleanUp implements InitializingBean {

    @PersistenceContext
    private EntityManager em;
    private List<String> tableNames;
    private List<String> notGeneratedIdTableNames;

    // 모든 Bean 이 설정이 되고 난 뒤에 실행되는 메소드
    @Override
    public void afterPropertiesSet() throws Exception {
        // tableNames 에 Entity 애너테이션이 있는 것 들중, Table 애노테이션에 설정한 명칭을 담아줌.
        tableNames = em.getMetamodel().getEntities()
                .stream()
                .filter(entity -> entity.getJavaType().getAnnotation(Entity.class) != null)
                .map(entity -> entity.getJavaType().getAnnotation(Table.class).name())
                .toList();

        // GenerationType.IDENTITY 설정 안된 Entity 들 PK 값 관리를 위해 테이블 명칭 넣어줌.
        notGeneratedIdTableNames = List.of("community_user_auth", "community_user_relation", "community_like");
    }

    @Transactional
    public void execute() {
        // 수행 안된 쿼리문 flush 처리
        em.flush();
        // 테이블 삭제 전 FK 조약이 있을수도 있으니 FALSE 처리
        em.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();

        for (String tableName : tableNames) {
            em.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
            if(!notGeneratedIdTableNames.contains(tableName)) {
                // GenerationType.IDENTITY 설정된 테이블들 초기값 1로 다시 세팅
                em.createNativeQuery("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1").executeUpdate();
            }
        }

        em.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }
}
