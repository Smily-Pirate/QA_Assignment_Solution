
//Verify that file size should not exceeds 5MB.
//        1. Open localhost/code/form
//        2. click on choose
//        3. Click upload adding a file greater than 5MB.
//        4. A error message should be visible "File size must not exceed 5MB.".


import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class TestFileSize extends baseTest{

    private baseWebdriver base;
    private FormHelper formHelper;

    @BeforeMethod
    public void setUp(){
        base = new baseWebdriver();
        formHelper = new FormHelper(base.driver);
        test.info("Setup Done");
    }


    @Test
    public void testFileSize() throws InterruptedException {
        test.info("Test Started: TestFileSize");
        Thread.sleep(2000);
        String basePath = formHelper.getProjectBasePath();
        String filePath = Paths.get(basePath,"Data", "Files",  "Behave_The.pdf").toString();
        System.out.println(filePath);
        formHelper.chooseFile(filePath);

        test.info("File name: Behave_The.pdf");

        Thread.sleep(2000);

        try {
            Alert alert = base.driver.switchTo().alert();
            String alertText = alert.getText();
            Assert.assertTrue(alertText.contains("File size must not exceed 5MB."), "File size validation failed.");
            test.info("File size exceeds 5MB");
            alert.accept();
            test.info("Alert accepted");
        } catch (NoAlertPresentException e) {
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


