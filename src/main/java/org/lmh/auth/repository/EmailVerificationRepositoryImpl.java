package org.lmh.auth.repository;

import lombok.RequiredArgsConstructor;
import org.lmh.auth.application.interfaces.EmailVerificationRepository;
import org.lmh.auth.domain.Email;
import org.lmh.auth.repository.entity.EmailVerificationEntity;
import org.lmh.auth.repository.jpa.JpaEmailVerificationRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmailVerificationRepositoryImpl implements EmailVerificationRepository {

    private final JpaEmailVerificationRepository jpaEmailVerificationRepository;

    @Override
    public void createEmailVerification(Email email, String token) {
        String emailAddress = email.getEmailText();
        Optional<EmailVerificationEntity> entity = jpaEmailVerificationRepository.findByEmail(emailAddress);

        // TODO : 생성 하려는 이메일이 이미 존재하면 에러처리
        if (entity.isPresent()) {
            EmailVerificationEntity emailVerificationEntity = entity.get();

            if(emailVerificationEntity.isVerified()) {
                throw new IllegalArgumentException("이미 인증된 이메일입니다.");
            }

            emailVerificationEntity.updateToken(token);

            return;
        }

        EmailVerificationEntity emailVerificationEntity = new EmailVerificationEntity(emailAddress, token);
        jpaEmailVerificationRepository.save(emailVerificationEntity);
    }
}
