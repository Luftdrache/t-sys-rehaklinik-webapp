package selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSettings {

    protected WebDriver driver;

    @BeforeEach
    public void init() {
        System.setProperty("webdriver.chrome.driver", "C://MyFiles//chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterEach
    public void destroy() {
        driver.quit();
    }
}
