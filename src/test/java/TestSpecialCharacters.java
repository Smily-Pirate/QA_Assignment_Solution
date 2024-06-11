//Verify that file with special character expect _,-,. are not allowed
//        1. Open localhost/code/form
//        2. click on choose file
//        3. Add a file with name consisting of _,-,.
//        4. A popup should appear with message " Only alphanumeric characters, dots, underscores, and hyphens are allowed"



import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class TestSpecialCharacters extends baseTest{

    private baseWebdriver base;
    private FormHelper formHelper;

    @BeforeMethod
    public void setUp(){
        base = new baseWebdriver();
        formHelper = new FormHelper(base.driver);
        test.info("Setup Done");
    }

    @Test
    public void testSpecialCharactersInFilename() throws InterruptedException {
        test.info("Test Started: TestSpecialCharactersInFilename");
        Thread.sleep(2000);
        String basePath = formHelper.getProjectBasePath();
        String filePath = Paths.get(basePath,"Data", "Files",  "Thi#$#$)(.pdf").toString();
        formHelper.chooseFile(filePath);
        test.info("File name: Thi#$#$)(.pdf");
        Thread.sleep(2000);

        try{

            Alert alert = base.driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("This is a alert message:" + alertText);
            Assert.assertTrue(alertText.contains("Only alphanumeric characters, dots, underscores, and hyphens are allowed."), "Special character validation failed.");
            alert.accept();
            test.info("Popup is visible. File is not accepted.");
        }catch (NoAlertPresentException e){
            test.info("Expected alert not present." + e);
            Assert.fail("Expected alert not present.");
        }

    }

    @AfterMethod
    public void tearDown(){
        base.quit();
        test.info("Teardown Completed");
    }
}
