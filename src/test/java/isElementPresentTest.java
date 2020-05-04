import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumExtension.class)
public class isElementPresentTest {

    private final WebDriver webDriver;

    public isElementPresentTest(ChromeDriver chromeDriver) {
        this.webDriver = chromeDriver;
    }

    private boolean isElementPresent(By by) {
        try {
            webDriver.findElement(by);
            return true;
        } catch ( Exception e ) {
            return false;
        }
    }

    @BeforeEach
    public void setUp() {
        webDriver.get("https://duckduckgo.com/");
    }

    @Test
    public void searchButtonTest() {
        boolean isPresent = isElementPresent(By.id("search_button_homepage"));
        assertTrue(isPresent);
    }

    @Test void imgTest() {
        boolean isPresent = isElementPresent(By.cssSelector("#content_homepage > div.badge-link.badge-link--full.js-badge-link > img:nth-child(2)"));
        assertTrue(isPresent);
    }

    @Test void notExistElementTest() {
        boolean isPresent = isElementPresent(By.id("return false"));
        assertFalse(isPresent);
    }

    @Test void logoTest() {
        boolean isPresent = isElementPresent(By.xpath("//*[@id=\"logo_homepage_link\"]"));
        assertTrue(isPresent);
    }

    @AfterEach
    public void tearDown() {
        webDriver.quit();
    }
}