package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.AfterEach;
import ru.stqa.addressbook.manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class TestBase {
    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() throws IOException {
        if (app == null) {
            Properties properties = new Properties();
            String targetFile = System.getProperty("target");
            if (targetFile != null) {
                properties.load(Files.newInputStream(Paths.get(targetFile)));
            } else {
                try (InputStream input = getClass().getClassLoader().getResourceAsStream("local.properties")) {
                    if (input == null) {
                        throw new IllegalStateException("Configuration file 'local.properties' not found in classpath");
                    }
                    properties.load(input);
                }
            }
            app = new ApplicationManager();
            app.init(System.getProperty("browser", "chrome"), properties);
        }
    }

    @AfterEach
    void DatabaseConsistency() {
        app.jdbc().checkConsistency();
    }
}
