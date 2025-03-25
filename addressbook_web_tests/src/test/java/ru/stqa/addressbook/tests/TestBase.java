package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.AfterEach;
import ru.stqa.addressbook.manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;
import ru.stqa.addressbook.utils.PreconditionHelper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class TestBase {
    protected static ApplicationManager app;
    protected static PreconditionHelper preconditions;
    public static String resourceDir;

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
            resourceDir = properties.getProperty("resource.dir");
            if (resourceDir == null || resourceDir.isBlank()) {
                throw new IllegalStateException("Missing 'resource.dir' in configuration.");
            }
        }
        preconditions = new PreconditionHelper(app);
    }

    @AfterEach
    void DatabaseConsistency() {
        app.jdbc().cleanupOrphanReferences();
        app.jdbc().checkConsistency();
    }
}
