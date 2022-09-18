package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class AppTest_2 extends StartDocker {

    @Test
    public void test_2() {
        WebDriver driver = getDriver();
        driver.get("https://www.gmail.com/");
        System.out.println("Title: " + driver.getTitle());
    }
}
