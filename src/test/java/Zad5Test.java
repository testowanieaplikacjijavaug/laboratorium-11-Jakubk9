import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@ExtendWith(SeleniumExtension.class)
public class Zad5Test {
    private WebDriver webDriver;

    public Zad5Test(ChromeDriver chromeDriver) {
        this.webDriver= chromeDriver;
    }

    @BeforeEach
    public void setUp() {
        webDriver.get("https://duckduckgo.com/");
    }

//    @Test
//    public void test1() {
//
//    }
}
