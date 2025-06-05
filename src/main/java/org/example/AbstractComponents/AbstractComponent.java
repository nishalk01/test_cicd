package org.example.AbstractComponents;

import org.example.pageobjects.CartPage;
import org.example.pageobjects.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;
    WebDriverWait wait;

    public AbstractComponent( WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver,this);
    }


    @FindBy(css = "button[routerlink*=\"cart\"]")
    WebElement goToCartBtn;

    @FindBy(css = "button[routerlink*='/myorders']")
    WebElement orderHistory;


    public CartPage goToCart(){
        goToCartBtn.click();
        return new CartPage(driver);
    }

    public OrderPage goToOrders(){
        orderHistory.click();
        return new OrderPage(driver);
    }



    public void waitForElementToAppear(By locator){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void waitForWebElementToAppear(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public  void waitForElementToDisappear(WebElement webElement){
        wait.until(ExpectedConditions.invisibilityOf(webElement));

    }




}
