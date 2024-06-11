//Verify that upload only accepts .pdf extension file.
//        1. Open localhost/code/form
//        2. click on choose file
//        3. Add a file with extension jpg, txt, zip etc.
//        4. An error message will appear "Only PDF files are al

//Verify that uploading pdf file works.
//        1. Open localhost/code/form
//        2. click on Choose File
//        3. Add a PDF file name Test.pdf
//        4. Click on Upload Document
//        5. Check message "Your File is uploaded"

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class TestPDFUpload extends baseTest{
    private baseWebdriver base;

    private FormHelper formHelper;

    @BeforeMethod
    public void setUp(){
        base = new baseWebdriver();
        formHelper = new FormHelper(base.driver);
        test.info("Setup Done");
    }

    @Test
    public void testUploadPDF() throws InterruptedException {

        test.info("Test Started: TestUploadPDF");
        String basePath = formHelper.getProjectBasePath();
        String filePath = Paths.get(basePath,"Data", "Files",  "This_is_a_valid_file.pdf").toString();
        formHelper.chooseFile(filePath);
        Thread.sleep(2000);
        test.info("File name: This_is_a_valid_file.pdf ");
        base.driver.findElement(By.name("submit")).click();
        test.info("Submit button was clicked successfully");



        Thread.sleep(2000);

        boolean isMessageDisplayed = base.driver.findElement(By.xpath("//*[contains(text(), 'Thank you. Your file is uploaded.')]")).isDisplayed();
        Assert.assertTrue(isMessageDisplayed, "Success message not displayed. Upload failed.");
        test.info("File is uploaded successfully");

    }


    @AfterMethod
    public void tearDown(){
        base.quit();
        test.info("TearDown Completed");
    }
}





