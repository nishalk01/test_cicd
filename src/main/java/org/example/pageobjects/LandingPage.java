package org.example.pageobjects;

import org.example.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

//   public WebElement username = driver.findElement(By.id("userEmail"));

    @FindBy(id="userEmail")
    WebElement userEmail;


    @FindBy(id = "userPassword")
    WebElement userPassword;

    @FindBy(id="login")
    WebElement submit;


    @FindBy(css = "div[class*=flyInOut")
    WebElement ErrorToast;


    public  ProductCatalog loginApplication(String email,String password)
    {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        submit.click();

        return new ProductCatalog(driver);

    }

    public  String getErrorMessage(){
        waitForWebElementToAppear(ErrorToast);
        return ErrorToast.getText();
    }
    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client/");
    }
}
