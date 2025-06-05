package org.example.pageobjects;

import org.example.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponent {
    WebDriver driver;

    ConfirmationPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "h1.hero-primary")
    WebElement confirmation;

    public Boolean verifyOrder(){
       return confirmation.getText().equalsIgnoreCase("Thankyou for the order." );
    }

    public WebElement getConfirmationText(){
        return confirmation;
    }



}
