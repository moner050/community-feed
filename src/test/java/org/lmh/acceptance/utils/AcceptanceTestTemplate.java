package org.lmh.acceptance.utils;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)        // 고정으로 8080 포트 사용 선언
public class AcceptanceTestTemplate {

    @Autowired
    private DatabaseCleanUp cleanUp;

    @Autowired
    private DataLoader dataLoader;

    @BeforeEach
    public void init() {
        cleanUp.execute();
        dataLoader.loadData();
    }

    protected void cleanUp() {
        cleanUp.execute();
    }

    protected String getEmailToken(String email) {
        return dataLoader.getEmailToken(email);
    }

    protected boolean isEmailVerified(String email) {
        return dataLoader.isEmailVerified(email);
    }

    protected Long getUserId(String email) {
        return dataLoader.getUserId(email);
    }
}
