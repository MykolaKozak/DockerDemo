package org.example;


import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;

/**
 * Unit test for simple App.
 */
public class AppTest_1 extends StartDocker {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
//        WebDriver driver = getDriver();
//        driver.get("https://www.google.pl/");
        open("https://www.google.pl/");

        System.out.println("Title: " + title());
    }
}
