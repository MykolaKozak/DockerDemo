package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static config.DockerConfig.getDriver;

public class AppTest_3 extends BaseTest {

    @Test
    public void test_3() {
        WebDriver driver = getDriver();;
        driver.get("https://tsn.ua/");
        System.out.println("Title: " + driver.getTitle());
    }
}
