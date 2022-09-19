package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static config.DockerConfig.getDriver;

public class AppTest_2 extends BaseTest {

    @Test
    public void test_2() {
        WebDriver driver = getDriver();
        driver.get("https://www.gmail.com/");
        System.out.println("Title: " + driver.getTitle());
    }
}
