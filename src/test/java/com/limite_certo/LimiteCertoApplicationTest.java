package com.limite_certo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class LimiteCertoApplicationTest {

    @Test
    void contextLoads() {
        LimiteCertoApplication application = new LimiteCertoApplication();
        assertNotNull(application, "The application should not be null");
    }

    @Test
    void mainTest() {
        assertDoesNotThrow(() -> LimiteCertoApplication.main(new String[] {}));
    }
}
