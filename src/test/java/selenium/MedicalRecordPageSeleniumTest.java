package selenium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;


class MedicalRecordPageSeleniumTest extends WebDriverSettings {

    private static final String ERROR_MESSAGE = "Operation with an invalid parameter";


    @Test
    void shouldAddDiagnosisTest() {
        driver.get(URLs.LOGIN_PAGE_URL);
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(TestCredentials.TEST_USERNAME);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(TestCredentials.TEST_PASSWORD);
        driver.findElement(By.xpath("//*[@id=\"sign-in-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"medical-record-button\"]/i")).click();
        driver.findElement(By.xpath("//*[@id=\"#add-diagnosis\"]")).click();
        Assertions.assertTrue(driver.getCurrentUrl().contains(URLs.ADD_DIAGNOSIS_PAGE));
        String newDisease = "Cardiomyopathy";
        driver.findElement(By.xpath("//*[@id=\"mainDisease\"]")).sendKeys(newDisease);
        driver.findElement(By.xpath("//*[@id=\"icd10Code\"]")).sendKeys("I43.1");
        driver.findElement(By.xpath("//*[@id=\"set-new-diagnosis\"]")).click();
        Assertions.assertTrue(driver.getCurrentUrl().contains(URLs.MEDICAL_RECORD_PAGE));
        Assertions.assertEquals(newDisease, driver.findElement(By.xpath("//*[@id=\"main-disease\"]")).getText());
    }


    @Test
    void shouldEditDiagnosisTest() {
        driver.get(URLs.LOGIN_PAGE_URL);
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(TestCredentials.TEST_USERNAME);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(TestCredentials.TEST_PASSWORD);
        driver.findElement(By.xpath("//*[@id=\"sign-in-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"medical-record-button\"]/i")).click();

        boolean isEditButtonPresent = driver.findElements(By.xpath("//*[@id=\"edit-diagnosis-button\"]")).size() != 0;
        if (isEditButtonPresent) {
            driver.findElements(By.xpath("//*[@id=\"edit-diagnosis-button\"]")).get(0).click();
            Assertions.assertTrue(driver.getCurrentUrl().contains(URLs.EDIT_DIAGNOSIS_PAGE));
            String newDisease = "Cardiomyopathy";
            driver.findElement(By.xpath("//*[@id=\"mainDisease\"]")).clear();
            driver.findElement(By.xpath("//*[@id=\"mainDisease\"]")).sendKeys(newDisease);
            driver.findElement(By.xpath("//*[@id=\"icd10Code\"]")).clear();
            driver.findElement(By.xpath("//*[@id=\"icd10Code\"]")).sendKeys("I43.1");
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"submit-edit-diagnosis\"]"))).click().perform();
            Assertions.assertTrue(driver.getCurrentUrl().contains(URLs.MEDICAL_RECORD_PAGE));
            String editedDisease = driver.findElement(By.xpath("//*[@id=\"main-disease\"]")).getText();
            Assertions.assertEquals("Cardiomyopathy", editedDisease);
        }
    }


    @Test
    void shouldWorkAllNavigationBarLinks_WhenClickedTest() {
        Actions actions = new Actions(driver);

        driver.get(URLs.LOGIN_PAGE_URL);
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(TestCredentials.TEST_USERNAME);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(TestCredentials.TEST_PASSWORD);
        driver.findElement(By.xpath("//*[@id=\"sign-in-button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"medical-record-button\"]/i")).click();

        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"#patients\"]/a/span"))).click().perform();
        Assertions.assertEquals(URLs.DOCTOR_MAIN_PAGE, driver.getCurrentUrl());
        driver.navigate().back();

        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"#show-med-record\"]/a/span"))).click().perform();
        Assertions.assertTrue(driver.getCurrentUrl().contains(URLs.MEDICAL_RECORD_PAGE));
        driver.navigate().back();

        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"#hospitalisation\"]/a/span"))).click().perform();
        Assertions.assertTrue(driver.getCurrentUrl().contains(URLs.HOSPITALISATION_PAGE));
        driver.navigate().back();

        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"#add-diagnosis\"]/a/span"))).click().perform();
        Assertions.assertTrue(driver.getCurrentUrl().contains(URLs.ADD_DIAGNOSIS_PAGE));
        driver.navigate().back();

        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"#show-prescriptions\"]/a"))).click().perform();
        Assertions.assertTrue(driver.getCurrentUrl().contains(URLs.PATIENT_PRESCRIPTIONS_PAGE));
        driver.navigate().back();

        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"#add-prescription\"]/a/span"))).click().perform();
        Assertions.assertTrue(driver.getCurrentUrl().contains(URLs.ADD_PRESCRIPTION_PAGE));
        driver.navigate().back();

        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"#events\"]/a/span"))).click().perform();
        Assertions.assertTrue(driver.getCurrentUrl().contains(URLs.TREATMENT_EVENTS_PAGE));
    }


    @Test
    void shouldShowErrorPage_WhenEnterWrongMedicalRecordIdTest() {
        driver.get(URLs.LOGIN_PAGE_URL);
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(TestCredentials.TEST_USERNAME);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(TestCredentials.TEST_PASSWORD);
        driver.findElement(By.xpath("//*[@id=\"sign-in-button\"]")).click();
        int wrongId = 1000000;
        driver.get(URLs.MEDICAL_RECORD_PAGE + wrongId);
        String message = driver.findElement(By.xpath("//*[@id=\"error-message-part-1\"]")).getText();
        Assertions.assertEquals(ERROR_MESSAGE, message);
    }
}
