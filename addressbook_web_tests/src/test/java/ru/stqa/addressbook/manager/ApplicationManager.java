package ru.stqa.addressbook.manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ApplicationManager {
    protected WebDriver driver;
    private LoginHelper sessionManager;
    private GroupHelper groups;
    private ContactHelper contacts;

    public void init(String browser) {
        if (driver == null) {
            System.setProperty("webdriver.http.factory", "jdk-http-client");
            if ("chrome".equals(browser)) {
                driver = new ChromeDriver();
            } else if ("firefox".equals(browser)) {
                driver = new FirefoxDriver();
            } else if ("edge".equals(browser)) {
                driver = new EdgeDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unsupported browser: %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                driver.quit();
                driver = null;
            }));
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(1280, 680));
            sessionManager().login("admin", "secret");
        }
    }

    public LoginHelper sessionManager() {
        if (sessionManager == null) {
            sessionManager = new LoginHelper(this);
        }
        return sessionManager;
    }


    public GroupHelper groups() {
        if (groups == null) {
            groups = new GroupHelper(this);
        }
        return groups;
    }

    public ContactHelper contacts() {
        if (contacts == null) {
            contacts = new ContactHelper(this);
        }
        return contacts;
    }

}
