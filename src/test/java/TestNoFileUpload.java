//Verify that upload file should throw a message "No file uploaded or there was an upload error."
//        1. Open localhost/code/form
//        2. click on choose file
//        3. Click upload without adding any files.
//        4. A error message should be visible "No file uploaded or there was an upload error."


import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNoFileUpload extends baseTest{

    private baseWebdriver base;
    private FormHelper formHelper;

    @BeforeMethod
    public void setUp(){
        base = new baseWebdriver();
        formHelper = new FormHelper(base.driver);
        test.info("Setup Done");
    }

    @Test
    public void testNoFileUpload() throws InterruptedException {
        test.info("Test Started: TestNoFileUpload");

        Thread.sleep(2000);
        base.driver.findElement(By.name("submit")).click();
        test.info("Submit button was clicked successfully");


        Thread.sleep(2000);


        boolean isMessageDisplayed = base.driver.findElement(By.xpath("//*[contains(text(), 'No file uploaded or there was an upload error.')]")).isDisplayed();
        Assert.assertTrue(isMessageDisplayed, "Error message for no file upload not displayed.");
        test.info("Error message is correct: No file uploaded or there was an upload error.");
    }


    @AfterMethod
    public void tearDown(){
        base.quit();
        test.info("TearDown Completed");
    }

}
