package me.Mung;

import me.Mung.Model.TestDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestF {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestF.class);

    //    @Test
    @RepeatedTest(30)
    public void testAF() {
        int success = TestDAO.TestDAOF();
        LOGGER.info("{}", success);
        Assertions.assertEquals(1, success);
//        Random random = new Random();
//        boolean b = random.nextBoolean();
//        LOGGER.info("{}", b);
//        Assertions.assertEquals(true, b);
    }
}
