/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package me.Mung;

import org.junit.Test;
import javax.security.auth.login.LoginException;
import static org.junit.Assert.assertNotNull;
public class BotTest {

    @Test
    public void testAppHasAGreeting() throws LoginException {
        Bot classUnderTest = new Bot();

        assertNotNull("app should have a greeting", classUnderTest);

    }
}
