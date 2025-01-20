package org.lmh.auth.repository;

import lombok.RequiredArgsConstructor;
import org.lmh.auth.application.interfaces.EmailSendRepository;
import org.lmh.auth.domain.Email;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmailSendRepositoryImpl implements EmailSendRepository {

    @Override
    public void sendEmail(Email email, String token) {
        // TODO: Email 전송 로직 추가
    }
}
