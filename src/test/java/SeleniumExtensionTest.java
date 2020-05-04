import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SeleniumExtension.class)
public class SeleniumExtensionTest {
//    private final WebDriver chromeDriver;
    private ChromeDriver chromeDriver;

    public SeleniumExtensionTest(ChromeDriver chromeDriver) {
//        this.chromeDriver = chromeDriver;
        this.chromeDriver = chromeDriver;
    }

    @BeforeEach
    public void setUp() {
        chromeDriver.get("https://duckduckgo.com/");
    }

    @Test
    public void titlePageTest() {
        assertEquals("DuckDuckGo — Prywatność — jeszcze prostsza.", chromeDriver.getTitle());
    }

    @Test
    public void sourceTest() {
        String source = chromeDriver.getPageSource();
        assertTrue(source.contains("DuckDuckGo"));
    }


    @Test
    public void clickTest() {
        chromeDriver.findElement(By.id("search_form_input_homepage"))
                .sendKeys("Mateusz Miotk");
        chromeDriver.findElement(By.id("search_button_homepage"))
                .click();
        assertEquals("Mateusz Miotk at DuckDuckGo", chromeDriver.getTitle());
    }

    @Test
    public void getFirstTest() {
        chromeDriver.findElement(By.cssSelector("#search_form_input_homepage")).sendKeys("Test");
        chromeDriver.findElement(By.id("search_button_homepage")).click();

        assertNotNull(chromeDriver.findElement(By.xpath("//*[@id=\"r1-0\"]/div/h2/a[1]")));
    }

    @AfterEach
    public void tearDown() {
        chromeDriver.quit();
    }
}
