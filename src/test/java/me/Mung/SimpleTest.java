package me.Mung;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static java.time.LocalDateTime.now;

public class SimpleTest {
    private static final org.slf4j.Logger LOGGER = (Logger) LoggerFactory.getLogger(SimpleTest.class);
    private static final String SEOUL_ZONE = "Asia/Seoul";

    //    @RepeatedTest(5000)
    @Test
    public void TestF() throws LoginException {
        ZonedDateTime now = ZonedDateTime.of(now(), ZoneId.of(SEOUL_ZONE));
        LOGGER.info("{}",now.getDayOfWeek());
        LOGGER.info("{}",now.getDayOfWeek().getValue() == 3);
    }


}
