package org.lmh.common.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Component
public class FcmConfig {

    @Value("${fcm.certification}")
    private String fcmApplicationCredentials;

    @PostConstruct
    public void init() {
        ClassPathResource resource = new ClassPathResource(fcmApplicationCredentials);

        try (InputStream is = resource.getInputStream()) {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(is))
                    .build();

            if(FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.info("FirebaseApp initialized!");
            }
        }
        catch (IOException e) {
            log.info("FirebaseApp initialization failed {}", e.getMessage());
        }
    }
}
