package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.rmi.Remote;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    WebDriver driver;

    public Wrappers(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHome() {
        driver.get("https://www.flipkart.com/");
    }

    public void clickOn(WebElement element) {
        element.click();
    }

    public void searchBox(String product) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement element = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//input[@name='q']")));
        // Thread.sleep(4000);
        element.clear();

        // Thread.sleep(8000);
        element.sendKeys(product);
        Thread.sleep(2000);
        element.sendKeys(Keys.ENTER);
    }

    public void countRating(Double number) throws InterruptedException {
        int count = 0;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.xpath("//div[@class='col col-7-12']//div[@class='XQDdHH']")));
        List<WebElement> ratingList = driver
                .findElements(By.xpath("//div[@class='col col-7-12']//div[@class='XQDdHH']"));
        for (WebElement productRating : ratingList) {
            String numbText = productRating.getText();
            double numb = Double.parseDouble(numbText);
            if (numb <= number) {
                count++;
            }
        }
        System.out.println("the washing machine rating less than or equal to " + number + " is : " + count);
    }

    public void DiscountPercent(int percentage) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.xpath("//div[@class='yKfJKb row']//div[@class='UkUFwK']/span")));
        List<WebElement> percentageList = driver
                .findElements(By.xpath("//div[@class='yKfJKb row']//div[@class='UkUFwK']/span"));
        wait.until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.xpath("//div[@class='yKfJKb row']//div[@class='KzDlHZ']")));
        List<WebElement> headingList = driver
                .findElements(By.xpath("//div[@class='yKfJKb row']//div[@class='KzDlHZ']"));
        for (int i = 0; i < percentageList.size(); i++) {
            String percentageText = percentageList.get(i).getText();
            String arr[] = percentageText.split(" ");
            int num = Integer.parseInt(arr[0].replace("%", ""));
            if (num > percentage) {
                String heading = headingList.get(i).getText();
                System.out.println("Phone title : " + heading);
                System.out.println("Discount Percentage : " + percentageText);
            }

        }
    }

    public void reviewCountsSort(int item) throws InterruptedException {
        int count = 0;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(
                        By.xpath("//div[@class='slAVV4']//div[@class='_5OesEi afFzxY']/span[@class='Wphh3N']")));
        List<WebElement> reviewsList = driver
                .findElements(By.xpath("//div[@class='slAVV4']//div[@class='_5OesEi afFzxY']/span[@class='Wphh3N']"));
        List<String> reviewTextList = new ArrayList<>();
        for (WebElement reviewsListItem : reviewsList) {
            String reviewText = reviewsListItem.getText();
            reviewTextList.add(reviewText);
        }
        Collections.sort(reviewTextList, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                // Remove parentheses and commas
                String num1 = s1.replaceAll("[^\\d]", "");
                String num2 = s2.replaceAll("[^\\d]", "");

                // Compare the numeric values
                return Integer.compare(Integer.parseInt(num2), Integer.parseInt(num1));
            }
        });
        for (String reviewstText : reviewTextList) {
            if (count < item) {
                WebElement imgUrl = driver.findElement(
                        By.xpath("//span[text()='" + reviewstText + "']//parent::div/preceding-sibling::a//img"));
                String imgUrlText = imgUrl.getAttribute("src");
                System.out.println("Url : " + imgUrlText);
                WebElement title = driver.findElement(By.xpath("//span[text()='" + reviewstText
                        + "']//parent::div[@class='_5OesEi afFzxY']//parent::div//a[@class='wjcEIp']"));
                String titleText = title.getText();
                System.out.println("title is : " + titleText);
                System.out.println("The number of reviews are : " + reviewstText);
                count++;
            }
        }
    }

}
