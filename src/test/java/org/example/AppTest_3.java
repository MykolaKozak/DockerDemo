package org.example;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest_3 extends StartDocker {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue3() {
        RemoteWebDriver driver = getDriver();
        driver.get("https://tsn.ua/");
        System.out.println("Title: " + driver.getTitle());
    }
}
