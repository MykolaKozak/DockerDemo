package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class AppTest_3 extends StartDocker {

    @Test
    public void test_3() {
        WebDriver driver = getDriver();;
        driver.get("https://tsn.ua/");
        System.out.println("Title: " + driver.getTitle());
    }
}
