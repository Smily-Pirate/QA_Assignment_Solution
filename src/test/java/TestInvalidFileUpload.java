import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;


public class TestInvalidFileUpload extends baseTest{

    private baseWebdriver base;
    private FormHelper formHelper;

    @BeforeMethod
    public void setup(){

        base = new baseWebdriver();
        formHelper = new FormHelper(base.driver);
        test.info("Setup Done");
    }


    @Test

    public void testUploadInvalidFile() throws InterruptedException {
        test.info("Test Started: TestUploadInvalidFile");
        String basePath = formHelper.getProjectBasePath();
        String filePath = Paths.get(basePath,"Data", "Files", "Brainstroming.txt").toString();
        formHelper.chooseFile(filePath);
        Thread.sleep(2000);
        base.driver.findElement(By.name("submit")).click();
        test.info("Submit button is clicked successfully");


        Thread.sleep(2000);


        boolean isMessageDisplayed = base.driver.findElement(By.xpath("//*[contains(text(), 'Sorry, only PDF files are allowed.')]")).isDisplayed();

        Assert.assertTrue(isMessageDisplayed, "Error message for invalid file type not displayed.");
        test.info("Correct message is visible: Sorry, only PDF files are allowed.");

    }

    @AfterMethod
    public void tearDown(){
        base.quit();
        test.info("TearDown Completed");
    }
}
