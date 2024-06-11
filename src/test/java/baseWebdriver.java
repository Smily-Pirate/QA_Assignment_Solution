import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Paths;

public class baseWebdriver {

    protected WebDriver driver;

    public baseWebdriver(){
        String basePath = Paths.get("").toAbsolutePath().toString();;
        String filePath = Paths.get(basePath,"Data", "Driver", "chromedriver.exe").toString();
        System.setProperty("webdriver.chrome.driver", filePath);
        driver = new ChromeDriver();
        driver.get("http://localhost/code/form");

    }

    public void quit(){
        if(driver != null){
            driver.quit();
        }
    }
}
