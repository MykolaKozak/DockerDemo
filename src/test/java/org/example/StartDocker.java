package org.example;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class StartDocker {
    private final String lofFile = "docker_log.txt";
    private boolean isDockerUp = false;
    private final int waitTime = 20;
    private final String dockerUp = "dockerUp.bat";
    private final String dockerDown = "dockerDown.bat";
    private final String dockerChromeScale = "dockerScaleChrome.bat";

    @BeforeSuite
    public void startFile() throws IOException {
        runFile(dockerUp);
        waitForDockerIsUp(waitTime);
        runFile(dockerChromeScale);
        waitForSec(waitTime);
    }

    @AfterSuite
    public void after() throws IOException {
        runFile(dockerDown);
        waitForSec(5);
        waitForDockerIsDown(waitTime);
    }

    private void runFile(String filePath) throws IOException {
//Run from Windows
//        Runtime runtime = Runtime.getRuntime();
//        runtime.exec("cmd /c start dockerUp.txt"); // run from Windows

// Run from MAC
//        String[] args = new String[]{"/bin/bash", "-c", "./dockerUp.bat"};
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

    public RemoteWebDriver getDriver() {

        URL url = null;
        try {
            url = new URL("http://localhost:4444/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //        FirefoxOptions capabilities = new FirefoxOptions();
        ChromeOptions capabilities = new ChromeOptions();
        return new RemoteWebDriver(url, capabilities);
    }
}
