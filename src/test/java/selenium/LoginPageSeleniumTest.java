package selenium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

class LoginPageSeleniumTest extends WebDriverSettings {

    @Test
    void shouldLoginUser_WhenCredentialsAreValidTest() {
        driver.get(URLs.LOGIN_PAGE_URL);
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(TestCredentials.TEST_USERNAME);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(TestCredentials.TEST_PASSWORD);
        driver.findElement(By.xpath("//*[@id=\"sign-in-button\"]")).click();
        String currentPage = driver.getCurrentUrl();
        Assertions.assertEquals(URLs.DOCTOR_MAIN_PAGE, currentPage);
    }

    @Test
    void shouldShowErrorMassage_WhenCredentialsAreInvalidTest() {
        driver.get(URLs.LOGIN_PAGE_URL);
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("Invalid username");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("Invalid password");
        driver.findElement(By.xpath("//*[@id=\"sign-in-button\"]")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//*[@id=\"error-message\"]")).isDisplayed());
    }

    @Test
    void shouldShowErrorMassage_WhenUsernameIsInvalidTest() {
        driver.get(URLs.LOGIN_PAGE_URL);
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("Invalid username");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(TestCredentials.TEST_PASSWORD);
        driver.findElement(By.xpath("//*[@id=\"sign-in-button\"]")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//*[@id=\"error-message\"]")).isDisplayed());
    }

    @Test
    void shouldShowErrorMassage_WhenPasswordIsInvalidTest() {
        driver.get(URLs.LOGIN_PAGE_URL);
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(TestCredentials.TEST_USERNAME);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("Invalid password");
        driver.findElement(By.xpath("//*[@id=\"sign-in-button\"]")).click();
        Assertions.assertTrue(driver.findElement(By.xpath("//*[@id=\"error-message\"]")).isDisplayed());
    }

    @Test
    void checkUsernameFieldIsRequiredTest() {
        driver.get(URLs.LOGIN_PAGE_URL);
        WebElement inputElement = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;", inputElement);
        Assertions.assertTrue(isRequired);
    }

    @Test
    void checkPasswordFieldIsRequiredTest() {
        driver.get(URLs.LOGIN_PAGE_URL);
        WebElement inputElement = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;", inputElement);
        Assertions.assertTrue(isRequired);
    }
}
