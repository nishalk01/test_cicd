package org.example.pageobjects;

import org.example.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class CheckOutPage extends AbstractComponent {

    WebDriver driver;
    Actions a;

    public CheckOutPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        this.a = new Actions(driver);
    }

    @FindBy(css = "div.user__name.mt-5 input[placeholder='Select Country']")
    WebElement countrySelect;


    @FindBy(xpath = "//button[contains(@class,'ta-item')]/span[text()=' India']")
    WebElement searchCountry;

//     driver.findElement(By.cssSelector("a.btnn.action__submit")).click();
    @FindBy(css ="a.btnn.action__submit" )
    WebElement submitBtn;

    By countryResult = By.cssSelector("section.ta-results");

    public void fillCountry(String country){
        a.sendKeys(countrySelect,country).build().perform();
        waitForElementToAppear(countryResult);
        a.moveToElement(searchCountry).click().build().perform();


    }

    public ConfirmationPage submit(){
        submitBtn.click();
        return new ConfirmationPage(driver);
    }


}
