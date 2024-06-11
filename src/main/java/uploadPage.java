import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class uploadPage {

    WebDriver uploadPageDriver;

    @FindBy(xpath = "input[type='file']")
    WebElement ChooseFile;

    @FindBy(xpath = "input[type='submit']")
    WebElement Submit;

    public void clickChoosefile(){
        ChooseFile.click();
    }

    public void clickSubmit(){
        Submit.click();
    }


}
