package selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;


class DoctorMainPageSeleniumTest extends WebDriverSettings {

//    @Test
//    void shouldSignOut_WhenClickedTest() {
//        driver.get(URLs.LOGIN_PAGE_URL);
//        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(TestCredentials.TEST_USERNAME);
//        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(TestCredentials.TEST_PASSWORD);
//        driver.findElement(By.xpath("//*[@id=\"sign-in-button\"]")).click();
//        driver.findElement(By.xpath("//*[@id=\"sign_out_button\"]")).click();
//        Assertions.assertEquals(URLs.LOGOUT, driver.getCurrentUrl());
//    }
//
//
//    @Test
//    void shouldShowMessage_WhenSearchNotFoundAnyoneTest() {
//        driver.get(URLs.LOGIN_PAGE_URL);
//        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(TestCredentials.TEST_USERNAME);
//        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(TestCredentials.TEST_PASSWORD);
//        driver.findElement(By.xpath("//*[@id=\"sign-in-button\"]")).click();
//        driver.findElement(By.xpath("//*[@id=\"search-input\"]")).sendKeys("Krakozyabra12345");
//        driver.findElement(By.xpath("//*[@id=\"search-button\"]")).click();
//        String message = driver.findElement(By.xpath("//*[@id=\"message-not-found\"]")).getText();
//        Assertions.assertFalse(message.isEmpty());
//    }
//
//
//    @Test
//    void shouldWorkMedicalRecordButton_WhenClickedTest() {
//        driver.get(URLs.LOGIN_PAGE_URL);
//        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(TestCredentials.TEST_USERNAME);
//        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(TestCredentials.TEST_PASSWORD);
//        driver.findElement(By.xpath("//*[@id=\"sign-in-button\"]")).click();
//        boolean isButtonShown = driver.findElements(By.xpath("//*[@id=\"medical-record-button\"]/i")).size() != 0;
//        if (isButtonShown) {
//            driver.findElements(By.xpath("//*[@id=\"medical-record-button\"]/i")).get(0).click();
//            Assertions.assertTrue(driver.getCurrentUrl().contains(URLs.MEDICAL_RECORD_PAGE));
//        }
//    }
//
//
//    @Test
//    void shouldWorkShowPrescriptionButton_WhenClickedTest() {
//        driver.get(URLs.LOGIN_PAGE_URL);
//        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(TestCredentials.TEST_USERNAME);
//        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(TestCredentials.TEST_PASSWORD);
//        driver.findElement(By.xpath("//*[@id=\"sign-in-button\"]")).click();
//        boolean isButtonShown = driver.findElements(By.xpath("//*[@id=\"show-prescriptions-button\"]/i")).size() != 0;
//        if (isButtonShown) {
//            driver.findElements(By.xpath("//*[@id=\"show-prescriptions-button\"]/i")).get(0).click();
//            Assertions.assertTrue(driver.getCurrentUrl().contains(URLs.PATIENT_PRESCRIPTIONS_PAGE));
//        }
//    }
//
//
//    @Test
//    void shouldWorkEventsButton_WhenClickedTest() {
//        driver.get(URLs.LOGIN_PAGE_URL);
//        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(TestCredentials.TEST_USERNAME);
//        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(TestCredentials.TEST_PASSWORD);
//        driver.findElement(By.xpath("//*[@id=\"sign-in-button\"]")).click();
//        boolean isButtonShown = driver.findElements(By.xpath("//*[@id=\"show-treatment-events-button\"]")).size() != 0;
//        if (isButtonShown) {
//            driver.findElements(By.xpath("//*[@id=\"show-treatment-events-button\"]")).get(0).click();
//            Assertions.assertTrue(driver.getCurrentUrl().contains(URLs.TREATMENT_EVENTS_PAGE));
//        }
//    }
//
//
//    @Test
//    void shouldWorkMyPatientsButton_WhenClickedTest() {
//        driver.get(URLs.LOGIN_PAGE_URL);
//        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(TestCredentials.TEST_USERNAME);
//        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(TestCredentials.TEST_PASSWORD);
//        driver.findElement(By.xpath("//*[@id=\"sign-in-button\"]")).click();
//        driver.findElements(By.xpath("//*[@id=\"#patients\"]")).get(0).click();
//        Assertions.assertTrue(driver.getCurrentUrl().contains(URLs.DOCTOR_MAIN_PAGE));
//    }
//
//
//    @Test
//    void shouldShowAboutPopup_WhenClickedTest() {
//        driver.get(URLs.LOGIN_PAGE_URL);
//        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(TestCredentials.TEST_USERNAME);
//        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(TestCredentials.TEST_PASSWORD);
//        driver.findElement(By.xpath("//*[@id=\"sign-in-button\"]")).click();
//        driver.findElement(By.xpath("//*[@id=\"about-popup\"]")).click();
//        Assertions.assertEquals(URLs.DOCTOR_MAIN_PAGE + "#", driver.getCurrentUrl());
//        driver.findElement(By.xpath("//*[@id=\"about-close-icon\"]")).click();
//        Assertions.assertEquals(URLs.DOCTOR_MAIN_PAGE + "#", driver.getCurrentUrl());
//    }
//
//
//    @Test
//    void pageTitleTest() {
//        driver.get(URLs.LOGIN_PAGE_URL);
//        String title = driver.getTitle();
//        Assertions.assertEquals("MedHelper", title);
//    }
}
