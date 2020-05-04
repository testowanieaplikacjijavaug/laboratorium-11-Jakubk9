import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SeleniumExtension.class)
public class WaitTest {
    private WebDriver webDriver;

    public WaitTest(ChromeDriver chromeDriver){
        this.webDriver = chromeDriver;
    }

    @BeforeEach
    public void setUp() {
        webDriver.get("https://duckduckgo.com/");
    }

    @Test
    public void elementToBeClickableTest() {
        WebElement wait = new WebDriverWait(webDriver,10)
            .until(ExpectedConditions.elementToBeClickable(By.id("search_button_homepage")));

        assertTrue(wait.isDisplayed());
    }

    @Test
    public void elementToBeSelectedTest() {
        WebDriverWait wait = new WebDriverWait(webDriver,5);
        assertThrows(TimeoutException.class, () ->
                wait.until(ExpectedConditions.elementToBeSelected(By.linkText("not_found"))));
    }

    @Test
    public void presenceOfElementLocatedTest() {
        WebElement wait = new WebDriverWait(webDriver,10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("search_button_homepage")));

        assertTrue(wait.isDisplayed());
    }

    @Test
    public void textToBePresentTest() {
        WebElement webElement = webDriver.findElement(By.cssSelector("#content_homepage > div.badge-link.badge-link--full.js-badge-link > div > div > h1"));
        Boolean wait = new WebDriverWait(webDriver,10)
                .until(ExpectedConditions.textToBePresentInElement(webElement,"Prywatność — jeszcze prostsza."));

        assertTrue(wait);
    }

    @Test
    public void titleContainsTest() {
        webDriver.findElement(By.id("search_form_input_homepage"))
                .sendKeys("Testowanie");
        webDriver.findElement(By.id("search_button_homepage"))
                .click();
        Boolean wait = new WebDriverWait(webDriver,10)
                .until(ExpectedConditions.titleContains("Testowanie"));

        assertTrue(wait);
    }
}
