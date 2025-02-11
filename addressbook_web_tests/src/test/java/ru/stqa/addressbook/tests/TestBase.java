package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.util.Random;

public class TestBase {
    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new ApplicationManager();
            app.init(System.getProperty("browser", "chrome"));
        }
    }

    public String randomString(int n) {
        Random rnd = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append((char) (rnd.nextInt(26) + 'a'));
        }
        return result.toString();
    }

}
