package baseClasses;

import controllers.InitPropertyController;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public final class InitializationClass {
    private static WebDriver driver;
    private static Wait<WebDriver> wait;
    private static InitPropertyController initPropertyController;

    public static WebDriver getDriver() {
        return driver;
    }

    public static Wait<WebDriver> getWait() {
        return wait;
    }


    public static void init() {
        initPropertyController = InitPropertyController.getInstance();
        System.setProperty(
                initPropertyController.getInitProperty("chromeDriverName"),
                initPropertyController.getInitProperty("chromeDriverPath"));

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(
                Integer.parseInt(InitPropertyController.getInitProperty("waitTimeout")),
                TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(
                Integer.parseInt(InitPropertyController.getInitProperty("pageLoadTimeOut")),
                TimeUnit.SECONDS);
        driver.get(initPropertyController.getInitProperty("url"));

        wait = new WebDriverWait(driver, 10, 2000);
    }
}
