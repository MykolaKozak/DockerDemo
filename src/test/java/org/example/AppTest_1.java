package org.example;


import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest_1 extends StartDocker {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        RemoteWebDriver driver = getDriver();
        driver.get("https://www.google.pl/");
        System.out.println("Title: " + driver.getTitle());
    }
}
