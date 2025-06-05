package test.stepDefination;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pageobjects.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import test.TestComponents.BaseTest;

import java.io.IOException;
import java.util.List;

public class StepDefinationImpl extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalog productCatalog;
    ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void Logged_in_username_and_password(String username,String password){
        productCatalog =landingPage.loginApplication(username,password);
    }

    @When("^I add the product (.+) to Cart$")
    public void i_add_product_to_cart(String productName){
        List<WebElement> cardBodyList = productCatalog.getProductList();
        productCatalog.AddToCart(productName);
    }

    @When("^Checkout (.+) and Submit the order$")
    public void checkout_and_submit_order(String productName){
        CartPage cartPage = productCatalog.goToCart();
        Assert.assertTrue(cartPage.verifyCheckOut(productName));

        CheckOutPage checkout  =cartPage.checkoutOrder();
        checkout.fillCountry("IND");
        confirmationPage =  checkout.submit();
    }


    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmation(String string){
        Assert.assertTrue(confirmationPage.getConfirmationText().getText().equalsIgnoreCase(string ));

    }

    @Then("{string} message is displayed in LoginPage inisde a Toast")
            public void error_login_message_displayed(String string){
              Assert.assertEquals(landingPage.getErrorMessage(),string);
    }

}
