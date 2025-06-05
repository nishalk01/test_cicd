package test;

import org.example.pageobjects.ProductCatalog;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import test.TestComponents.BaseTest;
import test.TestComponents.Retry;

public class ErrorLoginTest extends BaseTest {
    @Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
//    @Parameters({"email"})
    public  void ErrorLogin(){
        ProductCatalog productCatalogue = landingPage.loginApplication("nishal@gmail.com","123456@");
        Assert.assertEquals(landingPage.getErrorMessage(),"Incorrect or password.");
    }
}
