package test;

import org.apache.commons.io.FileUtils;
import org.example.pageobjects.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import test.TestComponents.BaseTest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    String[] itemToSelect = {"ZARA COAT 3"};

    @Test(dataProvider = "getData",groups = {"Purchase"})
//    @Parameters({"email","password"})
    public void Submit(HashMap<String,String> input) throws IOException {

        ProductCatalog productCatalogue =landingPage.loginApplication(input.get("email"),input.get("password"));

        List<WebElement> cardBodyList = productCatalogue.getProductList();
        productCatalogue.AddToCart(input.get("product"));

        CartPage cartPage = productCatalogue.goToCart();
        Assert.assertTrue(cartPage.verifyCheckOut(input.get("product")));

        CheckOutPage checkout  =cartPage.checkoutOrder();
        checkout.fillCountry("IND");
        ConfirmationPage confirmationPage =  checkout.submit();
        Assert.assertTrue(confirmationPage.verifyOrder());

    }

    @Test(dependsOnMethods = {"Submit"})
    @Parameters({"email","password"})
    public  void orderHistoryTest(String email,String password){
        ProductCatalog productCatalogue =  landingPage.loginApplication(email,password);
        OrderPage orderPage =productCatalogue.goToOrders();
        Assert.assertTrue(orderPage.verifyOrderHistory(itemToSelect[0]));


    }


    @DataProvider
    public Object[][] getData() throws IOException {
        HashMap<String,String> map= new HashMap<String,String>();
        map.put("email","nishal@gmail.com");
        map.put("password","123456@Aa");
        map.put("product","ZARA COAT 3");

        HashMap<String,String> map1= new HashMap<String,String>();
        map1.put("email","nishal@gmail.com");
        map1.put("password","123456@Aa");
        map1.put("product","ADIDAS ORIGINAL");

        List<HashMap<String,String>> jsonData=getJsonDataToMap(System.getProperty("user.dir") + "\\src\\main\\java\\org\\example\\data\\PurchaseOrder.json");


        return new Object [][]{{jsonData.get(0)},{jsonData.get(1)}};
    }





}
