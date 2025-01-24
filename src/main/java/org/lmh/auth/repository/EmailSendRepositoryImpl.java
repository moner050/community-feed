package org.lmh.auth.repository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.lmh.auth.application.interfaces.EmailSendRepository;
import org.lmh.auth.domain.Email;
import org.lmh.common.template.EmailTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmailSendRepositoryImpl implements EmailSendRepository {

    private final JavaMailSender mailSender;
    private final EmailTemplate template;

    @Override
    public void sendEmail(String toMail, String title, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(toMail);
            helper.setSubject(title);

            helper.setText(content, true);
            mailSender.send(message);
        }
        catch (MessagingException e) {
            throw new IllegalArgumentException("이메일 전송에 실패하였습니다. {}", e);
        }
    }

    @Override
    public void sendVerifyEmail(Email email, String token) {
        sendEmail(email.getEmailText(), template.getSignupTitle(), template.getSignupContent(token));
    }
}
