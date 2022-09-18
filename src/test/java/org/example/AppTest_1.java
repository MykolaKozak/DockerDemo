package org.example;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest_1 extends StartDocker {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void test_1() {
        WebDriver driver = getDriver();
        driver.get("https://www.google.pl/");
        System.out.println("Title: " + driver.getTitle());
    }
}
