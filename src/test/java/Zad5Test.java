import io.github.bonigarcia.seljup.SeleniumExtension;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumExtension.class)
public class Zad5Test {
    private WebDriver webDriver;

    public Zad5Test(ChromeDriver chromeDriver) {
        this.webDriver= chromeDriver;
    }
    
    @Test
    public void test1() {
        webDriver.get("https://duckduckgo.com/");
        try {
            webDriver.findElement(By.id("search_form_input_homepage"))
                    .sendKeys("Testowanie");
            webDriver.findElement(By.id("search_button_homepage"))
                    .click();
            WebElement webElement = new WebDriverWait(webDriver,10)
                    .until(new ExpectedCondition<WebElement>() {
                        @NullableDecl
                        @Override
                        public WebElement apply(@NullableDecl WebDriver webDriver) {
                            return webDriver.findElement(By.xpath("//*[@id=\"links_wrapper\"]/div[2]/div[1]/div/div[1]/div/div[1]/span"));
                        }
                    });
            assertEquals("Testowanie oprogramowania",webElement.getText());
        } finally {
            webDriver.quit();
        }
    }

    @Test
    public void test2() {
        webDriver.get("https://duckduckgo.com/");
        try {
            webDriver.findElement(By.id("search_form_input_homepage"))
                    .sendKeys("Testowanie");
            webDriver.findElement(By.id("search_button_homepage"))
                    .click();
            Boolean bool = new WebDriverWait(webDriver,10)
                    .until(new ExpectedCondition<Boolean>() {
                        @NullableDecl
                        @Override
                        public Boolean apply(@NullableDecl WebDriver webDriver) {
                            return webDriver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/a/span")).isDisplayed();
                        }
                    });
            assertTrue(bool);
        } finally {
            webDriver.quit();
        }
    }

    @Test
    public void test3() {
        webDriver.get("https://duckduckgo.com/");
        try {
            webDriver.findElement(By.id("search_form_input_homepage"))
                    .sendKeys("Testowanie");
            webDriver.findElement(By.id("search_button_homepage"))
                    .click();
            Boolean bool = new WebDriverWait(webDriver,10)
                    .until(new ExpectedCondition<Boolean>() {
                        @NullableDecl
                        @Override
                        public Boolean apply(@NullableDecl WebDriver webDriver) {
                            return webDriver.findElement(By.xpath("//*[@id=\"r1-0\"]/div/h2/a[1]"))
                                    .getText()
                                    .contains("Testowanie");
                        }
                    });
            assertTrue(bool);
        } finally {
            webDriver.quit();
        }
    }

}
