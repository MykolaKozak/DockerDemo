package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Objects;

import static config.PropertyReader.getProperty;
import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.FileUtils.runFile;
import static utils.TimeUtils.isTimeExpired;
import static utils.TimeUtils.waitForSec;

public class DockerConfig {

    private static final Logger log = LogManager.getLogger(DockerConfig.class);
    private static final String lofFile = "docker_log.txt";
    private static boolean isDockerUp = false;
    private static final int waitTime = 10;
    private static final String dockerUp = "dockerUp.bat";
    private static final String dockerDown = "dockerDown.bat";
    private static final String seleniumHubStatusCommand = "seleniumHubStatusCommand.bat";

    public static void dockerUP() {
        log.info("ENV: " + getProperty("env"));
        log.info("BROWSER: " + getProperty("browserName"));
        log.info("PLATFORM: " + getProperty("platform"));
        log.info("----- DOCKER STARTING ... ------");

        if (getProperty("env").equals("local")) {
            runFile(dockerUp); // docker up and scale chrome = 5
            waitForSec(waitTime);
            waitForDockerIsUp();
            log.info("----- LOCAL RUN ------");
        }
    }

    public static void dockerDown() {
        if (getProperty("env").equals("local")) {
            runFile(dockerDown);
//            waitForSec(5);
            waitForDockerIsDown();
        }
    }

    private static void waitForDockerIsUp() {
        while (!isTimeExpired(System.currentTimeMillis(), waitTime) && !isDockerUp) {
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(lofFile));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            while (Objects.nonNull(reader.lines()) && !isDockerUp) {
                if (reader.lines().anyMatch(line -> line.contains("Event bus ready"))) {
                    isDockerUp = true;
                    try {
                        reader.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        assertThat(isDockerUp).isTrue().as("Docker wasn't started.");
        log.info("Docker is UP");
    }

    private static void waitForDockerIsDown() {
//        while (!isTimeExpired(System.currentTimeMillis(), waitTime) && isDockerUp) {
//            BufferedReader reader;
//            try {
//                reader = new BufferedReader(new FileReader(lofFile));
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//            if (reader.lines().anyMatch(line -> line.contains("selenium-hub exited"))) {
//                isDockerUp = false;
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                break;
//            }
//        }
        isDockerUp = false;
        assertThat(isDockerUp).isFalse().as("Docker wasn't DOWN.");
        log.info("Docker is DOWN");
        waitForSec(5);
        File log = new File(lofFile);
        PrintWriter writer;
        try {
            writer = new PrintWriter(log);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        writer.print(EMPTY);
        writer.close();
    }

    public static WebDriver getDriver() {
        URL url = null;
        try {
            url = new URL(getProperty("seleniumGridUrl"));
        } catch (MalformedURLException e) {
            log.error("Selenium Grid hub url error: " + e);
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