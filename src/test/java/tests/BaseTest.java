package tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static config.DockerConfig.dockerDown;
import static config.DockerConfig.dockerUP;

public class BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void beforeRun(){
        dockerUP();
    }

    @AfterSuite
    public void afterRun(){
        dockerDown();
    }
}
