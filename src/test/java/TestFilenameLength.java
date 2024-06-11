//Verify that file with letter count greater than 50 are not allowed
//        1. Open localhost/code/form
//        2. click on choose file
//        3. Add a file with name greater than 50 characters
//        4. A popup should appear with message "File size must not exceed 50 characters."


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class TestFilenameLength extends baseTest {

    private baseWebdriver base;
    private FormHelper formHelper;




    @BeforeMethod
    public void setUp(){
        base = new baseWebdriver();
        formHelper = new FormHelper(base.driver);
        test.info("Setup Done");

    }


    @Test
    public void testFilenameLength() throws InterruptedException {
        test.info("Test Started: testFilenameLength");
        Thread.sleep(2000);
        String basePath = formHelper.getProjectBasePath();
        String filePath = Paths.get(basePath,"Data", "Files", "This_is_a_Long_file_name_greater_than_50_characters.pdf").toString();
        formHelper.chooseFile(filePath);

        test.info("his_is_a_Long_file_name_greater_than_50_characters.pdf");



        Thread.sleep(2000);

        try{
            Alert alert = base.driver.switchTo().alert();
            String alertText = alert.getText();
            test.info("Alert text:" + alertText);
            Assert.assertTrue(alertText.contains("Filename is too long. Only 50 character allowed."), "Filename length validation failed.");
            alert.accept();
            test.info("Alert accepted");

        }catch (NoAlertPresentException e){
            test.fail("Expected alert not present." + e);
            Assert.fail("Expected alert not present.");

        }

    }



    @AfterMethod
    public void tearDown(){
        base.quit();
        test.info("TearDown Completed");
    }


}
