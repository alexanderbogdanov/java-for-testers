package ru.stqa.addressbook.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperBase {
    protected final ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
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
        find(locator).sendKeys(text);
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

    }
}
