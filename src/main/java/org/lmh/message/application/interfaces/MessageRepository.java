package org.lmh.message.application.interfaces;

import org.lmh.user.domain.User;

public interface MessageRepository {

    void sendLikeMessage(User sender, User targetUser);
}
