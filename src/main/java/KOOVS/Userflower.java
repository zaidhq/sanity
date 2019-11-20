package KOOVS;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Userflower {


    public static WebDriver driver;
    public Properties prop;

    @BeforeTest
    public WebDriver initializeDriver() throws IOException {

        prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\zaidh\\IdeaProjects\\sanity\\src\\main\\java\\KOOVS\\data.properties");

        prop.load(fis);
        String browserName = prop.getProperty("browserName");
        System.out.println(browserName);

        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\zaidh\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
            driver = new ChromeDriver();
            //execute in chrome driver

        } else if (browserName.equals("firefox")) {
            driver = new FirefoxDriver();
            //firefox code
        } else if (browserName.equals("IE")) {
            //	IE code
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;

    }


    @Test
    public void Userflow() throws IOException, InterruptedException {
        driver.navigate().to("http://koovs.com");

        System.out.println("Reaching there");
        WebElement element = driver.findElement(By.id("goto_my_account"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        driver.findElement(By.id("login-email")).sendKeys("zaid.haque@koovs.com");
        driver.findElement(By.id("login-pswd")).sendKeys("Zaidhq@2019");
        WebElement submit = driver.findElement(By.xpath("//button[starts-with(@type,'button')]"));
        executor.executeScript("arguments[0].click();", submit);

        Actions actions = new Actions(driver);
        WebElement Women = driver.findElement(By.xpath("//a[contains(text(),'WOMEN')]"));
        actions.moveToElement(Women);

        WebElement Dress = driver.findElement(By.xpath("//a[contains(text(),'Tops')]"));
        actions.moveToElement(Dress);
        actions.click().build().perform();


        driver.findElement(By.className("sort-text")).click();
        driver.findElement(By.xpath("//li[contains(text(),\"What's New\")]")).click();

        WebDriverWait wait=new WebDriverWait(driver,30);
        // Wait till the element is not visible
        WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("prodImgBox")));
        element2.click();
        Set<String> handleswindow = driver.getWindowHandles();

        for (String windowHandle : handleswindow) {

            driver.switchTo().window(windowHandle);

        }

        System.out.println(driver.getCurrentUrl());

        ArrayList<WebElement> sizeList = new ArrayList<WebElement>(driver.findElements(By.xpath("//*[contains(@class, 'size-data')]")));

        for (WebElement element1 : sizeList) {
            if (element1.isEnabled()) {
                element1.click();
                break;
            }

        }

        WebElement addBag = driver.findElement(By.className("add-to-bag-btn"));
        executor.executeScript("arguments[0].click();", addBag);

        WebElement goBag = driver.findElement(By.className("go-to-bag-btn"));
        executor.executeScript("arguments[0].click();", goBag);

        WebElement checkOut = driver.findElement(By.className("checkout-btn"));
        executor.executeScript("arguments[0].click();", checkOut);
        System.out.println("Reached checkout page");


    }


    @AfterTest
    public void teardown() {
// TODO Auto-generated method stub
        driver.close();
        driver = null;


    }
}