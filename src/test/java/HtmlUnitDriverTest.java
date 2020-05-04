import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

public class HtmlUnitDriverTest {

    WebDriver webDriver = new HtmlUnitDriver();

    @BeforeEach
    public void setUp() {
        webDriver.get("https://duckduckgo.com/");
    }

    @Test
    public void titlePageTest() {
        assertEquals("DuckDuckGo — Privacy, simplified.", webDriver.getTitle());
    }

    @Test
    public void sourceTest() {
        String source = webDriver.getPageSource();
        assertTrue(source.contains("DuckDuckGo"));
    }


    @Test
    public void clickTest() {
        webDriver.findElement(By.id("search_form_input_homepage"))
                .sendKeys("Testowanie");
        webDriver.findElement(By.id("search_button_homepage"))
                .click();
        assertEquals("Testowanie at DuckDuckGo", webDriver.getTitle());
    }

    @Test
    public void notExistingElementTest() {
        assertThrows(NoSuchElementException.class, () -> {
            webDriver.findElement(By.linkText("not exist"));
        });
    }

    @AfterEach
    public void tearDown() {
        webDriver.quit();
    }
}
