package test.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.example.pageobjects.LandingPage;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver ;
    public LandingPage landingPage;

    public WebDriver initDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\GlobalProperties.properties");
        prop.load(fis);

        String browserName = System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");

        if(browserName.contains("Edge")){
            EdgeOptions options = new EdgeOptions();
            if(browserName.contains("headless")){
                options.addArguments("headless");
            }

            driver = new EdgeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));
        }
        else if(browserName.contains("Chrome")){
            ChromeOptions options = new ChromeOptions();
            if(browserName.contains("headless")){
                options.addArguments("headless");
            }

            driver = new ChromeDriver();
            driver.manage().window().setSize(new Dimension(1440,900));

        }


        assert driver != null;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return  driver;
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver = initDriver();

        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;

    }



    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
        driver.quit();
    }

    public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
    }

    public String getScreenshot(String fileName,WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\org\\example\\data\\reports\\"+fileName+".png";
        FileUtils.copyFile(ts.getScreenshotAs(OutputType.FILE),new File(filePath));
        return filePath;
    }

    }
