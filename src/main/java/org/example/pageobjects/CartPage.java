package org.example.pageobjects;

import org.example.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {


    WebDriver driver;

     public CartPage(WebDriver driver){
         super(driver);
         this.driver = driver;
         PageFactory.initElements(driver,this);
     }

     @FindBy(css = "div.cartSection h3")
     List<WebElement> itemNames;

     @FindBy(xpath = "//button[text()=\"Checkout\"]")
     WebElement checkOutBtn;


     public Boolean verifyCheckOut(String itemToOrder){

        return itemNames.stream().anyMatch(s->s.getText().equalsIgnoreCase(itemToOrder));
     }

     public CheckOutPage checkoutOrder(){
         checkOutBtn.click();
         return new CheckOutPage(driver);
     }



}
