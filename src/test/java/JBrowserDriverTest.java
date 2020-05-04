import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;
import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SeleniumExtension.class)
public class JBrowserDriverTest {
    
    JBrowserDriver jBrowserDriver = new JBrowserDriver(Settings
            .builder()
            .timezone(Timezone.EUROPE_WARSAW)
            .build());
    
    @BeforeEach
    public void setUp() {
        jBrowserDriver.get("https://duckduckgo.com/");
    }

    @Test
    public void titlePageTest() {
        assertEquals("DuckDuckGo — Privacy, simplified.", jBrowserDriver.getTitle());
    }

    @Test
    public void sourceTest() {
        String source = jBrowserDriver.getPageSource();
        assertTrue(source.contains("DuckDuckGo"));
    }


    @Test
    public void clickTest() {
        jBrowserDriver.findElement(By.id("search_form_input_homepage"))
                .sendKeys("Mateusz Miotk");
        jBrowserDriver.findElement(By.id("search_button_homepage"))
                .click();
        assertEquals("Mateusz Miotk at DuckDuckGo", jBrowserDriver.getTitle());
    }

    @Test
    public void getFirstTest() {
        jBrowserDriver.findElement(By.cssSelector("#search_form_input_homepage")).sendKeys("Test");
        jBrowserDriver.findElement(By.id("search_button_homepage")).click();

        assertNotNull(jBrowserDriver.findElement(By.xpath("//*[@id=\"r1-0\"]/div/h2/a[1]")));
    }

    @Test
    public void notExistingElementTest() {
        assertThrows(NoSuchElementException.class, () -> {
            jBrowserDriver.findElement(By.linkText("not exist"));
        });
    }

    @AfterEach
    public void tearDown() {
        jBrowserDriver.quit();
    }
}
