package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
//package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @Test
    public void testCase01() throws InterruptedException {
        Wrappers homePage = new Wrappers(driver);

        homePage.navigateToHome();
        Thread.sleep(20000);

        homePage.searchBox("Washing Machine");
        Thread.sleep(4000);
        WebElement button1 = driver.findElement(By.xpath("//div[text()='Popularity']"));
        homePage.clickOn(button1);
        Thread.sleep(4000);
        homePage.countRating(4.0);
        Thread.sleep(6000);
    }

    @Test
    public void testCase02() throws InterruptedException {
        Wrappers homePage = new Wrappers(driver);
        homePage.navigateToHome();
        Thread.sleep(20000);
        homePage.searchBox("iPhone");
        Thread.sleep(5000);
        homePage.DiscountPercent(17);
    }

    @Test
    public void testCase03() throws InterruptedException {
        Wrappers homePage = new Wrappers(driver);
        homePage.navigateToHome();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        Thread.sleep(7000);
        homePage.searchBox("Coffee Mug");
        Thread.sleep(5000);

        // wait.until(ExpectedConditions
        // .presenceOfElementLocated(By.xpath("//div[text()='4★ &
        // above']//preceding-sibling::input")));
        WebElement element = driver
                .findElement(By.xpath("(//div[@class='XqNaEv'])[1]"));
        homePage.clickOn(element);
        Thread.sleep(4000);

        homePage.reviewCountsSort(5);

    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();

    }
}