import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class WebDriverManagerTest {
    private static WebDriver webDriver;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver()
                .setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage()
                .timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() {
        webDriver.get("https://duckduckgo.com/");
    }

    @Test
    public void titlePageTest() {
        assertEquals("DuckDuckGo — Prywatność — jeszcze prostsza.", webDriver.getTitle());
    }

    @Test
    public void sourceTest() {
        String source = webDriver.getPageSource();
        assertTrue(source.contains("DuckDuckGo"));
    }


    @Test
    public void clickTest() {
        webDriver.findElement(By.id("search_form_input_homepage"))
                .sendKeys("Mateusz Miotk");
        webDriver.findElement(By.id("search_button_homepage"))
                .click();
        assertEquals("Mateusz Miotk at DuckDuckGo", webDriver.getTitle());
    }

    @Test
    public void getFirstTest() {
        webDriver.findElement(By.cssSelector("#search_form_input_homepage")).sendKeys("Test");
        webDriver.findElement(By.id("search_button_homepage")).click();

        assertNotNull(webDriver.findElement(By.xpath("//*[@id=\"r1-0\"]/div/h2/a[1]")));
    }

    @AfterAll
    public static void tearDown() {
        webDriver.quit();
    }
}
