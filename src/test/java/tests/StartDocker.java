package tests;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Objects;

import static config.PropertyReader.getProperty;

public class StartDocker {
    private final String lofFile = "docker_log.txt";
    private boolean isDockerUp = false;
    private final int waitTime = 10;
    private final String dockerUp = "dockerUp.bat";
    private final String dockerDown = "dockerDown.bat";
    private final String dockerChromeScale = "dockerScaleChrome.bat";

    @BeforeSuite
    public void startFile() throws IOException {
        if (getProperty("env").equals("local")) {
            runFile(dockerUp); // docker up and scale chrome = 5
            waitForSec(waitTime);
        }
    }

    @AfterSuite
    public void after() throws IOException {
        if (getProperty("env").equals("local")) {
            runFile(dockerDown);
            waitForSec(5);
            waitForDockerIsDown(waitTime);
        }
    }

    private void runFile(String filePath) throws IOException {
        //Run from Windows
//      Runtime runtime = Runtime.getRuntime();
//      runtime.exec("cmd /c start dockerUp.txt"); // run from Windows

        // Run from MAC
        String[] args = new String[]{"/bin/bash", "-c", "./".concat(filePath)};
        System.out.println("Run file: ".concat(filePath));
        new ProcessBuilder(args).start();
    }

    private void waitForDockerIsUp(int waitSeconds) throws IOException {
        while (!isTimeExpired(System.currentTimeMillis(), waitSeconds) && !isDockerUp) {
            BufferedReader reader = new BufferedReader(new FileReader(lofFile));
            while (Objects.nonNull(reader.lines()) && !isDockerUp) {
                if (reader.lines().anyMatch(line -> line.contains("Sending registration event"))) {
                    isDockerUp = true;
                    reader.close();
                }
            }
        }
        Assert.assertTrue(isDockerUp, "Docker wasn't started.");
        System.out.println("Docker is UP");
    }

    private void waitForDockerIsDown(int waitSeconds) throws IOException {
        while (!isTimeExpired(System.currentTimeMillis(), waitSeconds) && isDockerUp) {
            BufferedReader reader = new BufferedReader(new FileReader(lofFile));
            if (reader.lines().anyMatch(line -> line.contains("selenium-hub exited"))) {
                isDockerUp = false;
                reader.close();
                break;
            }
        }
        Assert.assertFalse(isDockerUp, "Docker wasn't DOWN.");
        System.out.println("Docker is DOWN");
        waitForSec(5);
        File log = new File(lofFile);
        PrintWriter writer = new PrintWriter(log);
        writer.print("");
        writer.close();
    }

    private boolean isTimeExpired(long startTime, long endTimeInSec) {
        return !((System.currentTimeMillis() - startTime) <= endTimeInSec * 1000);
    }

    private void waitForSec(int seconds) {
        seconds = seconds * 1000;
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WebDriver getDriver() {
        URL url = null;
        try {
            url = new URL(getProperty("seleniumGridUrl"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName(getProperty("browserName"));
        desiredCapabilities.setPlatform(Platform.valueOf(getProperty("platform")));
        desiredCapabilities.setCapability("pageLoadStrategy", PageLoadStrategy.NORMAL);

        WebDriver driver = new RemoteWebDriver(url, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTime));
        driver.manage().window().maximize();
        return driver;
    }
}