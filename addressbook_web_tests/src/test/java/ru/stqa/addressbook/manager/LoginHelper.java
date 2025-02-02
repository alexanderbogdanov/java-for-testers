package ru.stqa.addressbook.manager;

import org.openqa.selenium.By;



public class LoginHelper extends HelperBase {

//    private final ApplicationManager manager;

//    public LoginHelper(ApplicationManager manager) {
//        this.manager = manager;
//    }

    public LoginHelper(ApplicationManager manager) {
        super(manager);
    }

    void login(String username, String password) {
        type(By.name("user"), username);
        type(By.name("pass"), password);
        click(By.xpath("//input[@value='Login']"));
    }
}
