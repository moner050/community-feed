package org.lmh.post.domain.common;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DateTimeInfoTest {

    @Test
    void givenCreated_whenUpdated_thenTimeAndStateArsUpdated() throws InterruptedException {
        // given
        DateTimeInfo dateTimeInfo = new DateTimeInfo();
        LocalDateTime localDateTime = dateTimeInfo.getDateTime();

        // when
        Thread.sleep(1);
        dateTimeInfo.updateEditDateTime();

        // then
        assertTrue(dateTimeInfo.isEdited());
        assertNotEquals(localDateTime, dateTimeInfo.getDateTime());
    }
}
