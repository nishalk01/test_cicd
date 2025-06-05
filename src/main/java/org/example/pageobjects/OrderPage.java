package org.example.pageobjects;

import org.example.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {

    public OrderPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);


    }

    @FindBy(css = "table tbody tr td:nth-child(3)")
    List<WebElement> allOrderItems;

    public Boolean verifyOrderHistory(String productName){
        return allOrderItems.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));


    }
}
