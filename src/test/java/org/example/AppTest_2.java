package org.example;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest_2 extends StartDocker {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void test_2() {
        WebDriver driver = getDriver();
        driver.get("https://www.gmail.com/");
        System.out.println("Title: " + driver.getTitle());
    }
}
