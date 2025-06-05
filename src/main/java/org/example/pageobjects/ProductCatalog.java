package org.example.pageobjects;

import org.example.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalog extends AbstractComponent {

    WebDriver driver;

    public ProductCatalog(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "div.card-body")
    List<WebElement>  cardBodyList;

    @FindBy(css = ".ng-animating")
    WebElement spinner;

    By cardBody = By.cssSelector("div.card-body");
    By addToCart = By.cssSelector("i.fa.fa-shopping-cart");
    By loading = By.cssSelector("div#toast-container");




    public List<WebElement> getProductList(){
        waitForElementToAppear(cardBody);

        return driver.findElements(cardBody);

    }

    public WebElement getProductByName(String itemToSearch){
        return getProductList().stream()
                                    .filter(s-> s.findElement(By.tagName("h5")).getText().equals(itemToSearch))
                                    .findFirst().orElse(null);
    }

    public  void AddToCart(String itemToSearch){
        getProductByName(itemToSearch).findElement(addToCart).click();
        waitForElementToAppear(loading);
        waitForElementToDisappear(spinner);


    }





}
