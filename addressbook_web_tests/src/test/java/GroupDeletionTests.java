import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class GroupDeletionTests extends TestBase{


    @Test
    public void testGroupDeletion() {
        if (!isElementPresent(By.name("new"))) {
            driver.findElement(By.linkText("groups")).click();
        }
        if (!isElementPresent(By.name("selected[]"))) {
            createGroup("group name", "group header", "group footer");
        }
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.name("delete")).click();
        driver.findElement(By.linkText("group page")).click();
    }

}


