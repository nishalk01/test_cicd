package test;

import org.example.pageobjects.LandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {

    @Test
    public void selTest(){
        WebDriver driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com/client/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));



        driver.findElement(By.id("userPassword")).sendKeys("nishal@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("123456@Aa");
        driver.findElement(By.id("login")).click();

        String[] itemToSelect = {"ZARA COAT 3"};

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.card-body")));
        List<WebElement> cardBodyList = driver.findElements(By.cssSelector("div.card-body"));
        WebElement listToSelect = cardBodyList.stream().filter(s-> s.findElement(By.tagName("h5")).getText().equals(itemToSelect[0])).findFirst().orElse(null);
        listToSelect.findElement(By.cssSelector("i.fa.fa-shopping-cart")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#toast-container")));

        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        driver.findElement(By.cssSelector("button[routerlink*=\"cart\"]")).click();

        List<WebElement> listofCartProduct =driver.findElements(By.cssSelector("div.cartSection h3"));

        Boolean isPresent = listofCartProduct.stream().anyMatch(s->s.getText().equalsIgnoreCase(itemToSelect[0]));
        Assert.assertTrue(isPresent);
        driver.findElement(By.xpath("//button[text()=\"Checkout\"]")).click();

//        driver.findElement(By.cssSelector("div.user__name.mt-5 input[placeholder=\"Select Country\"]")).sendKeys("IND");
//        driver.findElement(By.xpath("//button[@class=\"ta-item list-group-item ng-star-inserted\"]/span[text()=\" India\"]")).click();
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("div.user__name.mt-5 input[placeholder='Select Country']")),"india").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.ta-results")));
        a.moveToElement(driver.findElement(By.xpath("//button[contains(@class,'ta-item')]/span[text()=\" India\"]"))).click().build().perform();

        driver.findElement(By.cssSelector("a.btnn.action__submit")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("h1.hero-primary")).getText().equalsIgnoreCase("Thankyou for the order." ));

        driver.quit();


//        Select the given elements add to cart
//      List<WebElement> addtoCart =  driver.findElements(By.xpath("//div[@class=\"card-body\"]/button/i[@class=\"fa fa-shopping-cart\"]"));

    }
}
