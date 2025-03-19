package ru.stqa.addressbook.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

public class HelperBase {
    protected final ApplicationManager manager;
    protected final String dbUrl;
    protected final String dbUsername;
    protected final String dbPassword;

    public static final int DEFAULT_WAIT_SECONDS = 10;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
        this.dbUrl = getProperty("db.url");
        this.dbUsername = getProperty("db.username");
        this.dbPassword = getProperty("db.password");
    }

    protected WebElement find(By locator) {
        return manager.driver.findElement(locator);
    }

    protected List<WebElement> findElements(By locator) {
        return manager.driver.findElements(locator);
    }


    protected void click(By locator) {
        find(locator).click();
    }

    protected void type(By locator, String text) {
        find(locator).clear();
        click(locator);
        if (text != null) {
            find(locator).sendKeys(text);
        }
    }


    protected void waitForElement(By locator, int timeoutSeconds) {
        new WebDriverWait(manager.driver, Duration.ofSeconds(timeoutSeconds)).until(
                ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void waitForElement(By locator) {
        waitForElement(locator, DEFAULT_WAIT_SECONDS);
    }

    protected void waitAndClick(By locator) {
        new WebDriverWait(manager.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator))
                .click();
    }

    protected void refresh() {
        manager.driver.navigate().refresh();
    }

    public void selectFromDropdown(By locator, String value) {
        new Select(find(locator)).selectByValue(value);
    }

    protected boolean isElementPresent(By locator) {
        try {
            find(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    protected boolean isAlertPresent() {
        try {
            manager.driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }

    protected void confirmAction() {
        click(By.name("submit"));
    }

    protected void deleteSelected() {
        click(By.xpath("//input[starts-with(@value, 'Delete')]"));
        if (isAlertPresent()) {
            manager.driver.switchTo().alert().accept();
        }
    }

    protected void submitChanges() {
        click(By.name("update"));
    }

    protected void uploadFile(By locator, String file) {
        find(locator).sendKeys(Paths.get(file).toAbsolutePath().toString());
    }

    protected String getProperty(String key) {
        return manager.getProperties().getProperty(key);
    }

}
