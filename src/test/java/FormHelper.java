import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.nio.file.Paths;

public class FormHelper {
    private WebDriver driver;

    public  FormHelper(WebDriver driver){
        this.driver = driver;
    }

    public void chooseFile(String filePath){
        WebElement fileInput = driver.findElement(By.name("document"));
        fileInput.sendKeys(filePath);
    }

    // Getting the directory of the project
    public static String getProjectBasePath() {
        // Get the current working directory (project base directory)
        return Paths.get("").toAbsolutePath().toString();
    }
}
