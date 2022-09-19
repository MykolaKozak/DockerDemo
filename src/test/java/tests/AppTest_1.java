package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static config.DockerConfig.getDriver;


public class AppTest_1 extends BaseTest{


    @Test
    public void test_1() {
        WebDriver driver = getDriver();
        driver.get("https://www.google.pl/");
        System.out.println("Title: " + driver.getTitle());
    }
}
