package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.nio.file.Paths;
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

    public static String randomString(int n) {
        Random rnd = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append((char) (rnd.nextInt(26) + 'a'));
        }
        return result.toString();
    }

    public static String getRandomImagePath(String dir) {
        var fileNames = new File(dir).list();
        if (fileNames == null || fileNames.length == 0) {
            throw new IllegalStateException("No files found in directory: " + dir);
        }
        var rnd = new Random();
        var index = rnd.nextInt(fileNames.length);
        return Paths.get(dir, fileNames[index]).toString();
    }

}
