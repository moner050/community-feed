package org.lmh.message.repository;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import lombok.RequiredArgsConstructor;
import org.lmh.message.application.interfaces.MessageRepository;
import org.lmh.message.repository.entity.FcmTokenEntity;
import org.lmh.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FcmMessageRepositoryImpl implements MessageRepository {

    private final JpaFcmTokenRepository jpaFcmTokenRepository;

    private static final String LIKE_MESSAGE_TEMPLATE = "%s님이 %s님 글에 좋아요를 눌렀습니다.";
    private static final String MESSAGE_KEY = "message";

    @Override
    public void sendLikeMessage(User sender, User targetUser) {
        Optional<FcmTokenEntity> tokenEntity = jpaFcmTokenRepository.findById(targetUser.getId());

        if(tokenEntity.isEmpty()) {
            return;
        }

        FcmTokenEntity token = tokenEntity.get();
        Message message = Message.builder()
                .putData(MESSAGE_KEY, LIKE_MESSAGE_TEMPLATE.formatted(sender.getName(), targetUser.getName()))
                .setToken(token.getFcmToken())
                .build();

        FirebaseMessaging.getInstance().sendAsync(message);
    }
}
