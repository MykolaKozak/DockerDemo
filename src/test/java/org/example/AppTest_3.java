package org.example;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest_3 extends StartDocker {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void test_3() {
        WebDriver driver = getDriver();
        driver.get("https://tsn.ua/");
        System.out.println("Title: " + driver.getTitle());
    }
}
