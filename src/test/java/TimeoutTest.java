import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumExtension.class)
public class TimeoutTest {
    private WebDriver webDriver1;
    private WebDriver webDriver2;

    public TimeoutTest(ChromeDriver chromeDriver) {
        this.webDriver1 = chromeDriver;
        this.webDriver2 = chromeDriver;
    }

    @BeforeEach
    public void setUp() {
        webDriver1.get("https://duckduckgo.com/");
        //pageLoadTimeout() - ogranicza czas który przeznacza na wyswietlenie/załadowanie strony
        webDriver1.manage()
                .timeouts()
                .pageLoadTimeout(10,TimeUnit.SECONDS);
        webDriver2.get("https://duckduckgo.com/");
        //setScriptTimeout() - ogranicza czas na wykonanie danego skryptu
        webDriver2.manage()
                .timeouts()
                .setScriptTimeout(10,TimeUnit.SECONDS);
    }

    @Test
    public void pageLoadTimeoutTest() {
        webDriver1.findElement(By.id("search_form_input_homepage"))
                .sendKeys("Testowanie");
        webDriver1.findElement(By.id("search_button_homepage"))
                .click();

        assertTrue(webDriver1.getTitle().contains("Testowanie"));
    }

    @Test
    public void setScriptTimeoutTest() {
        webDriver1.findElement(By.id("search_form_input_homepage"))
                .sendKeys("Testowanie");
        webDriver1.findElement(By.id("search_button_homepage"))
                .click();

        assertTrue(webDriver1.getTitle().contains("Testowanie"));
    }
}
