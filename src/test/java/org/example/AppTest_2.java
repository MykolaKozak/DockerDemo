package org.example;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest_2 extends StartDocker {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue2() {
        RemoteWebDriver driver = getDriver();
        driver.get("https://www.gmail.com/");
        System.out.println("Title: " + driver.getTitle());
    }
}
