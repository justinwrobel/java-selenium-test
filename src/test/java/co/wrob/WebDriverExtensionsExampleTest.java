package co.wrob;
import static java.util.concurrent.TimeUnit.SECONDS;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.github.webdriverextensions.Bot;
import com.github.webdriverextensions.junitrunner.WebDriverRunner;
import com.github.webdriverextensions.junitrunner.annotations.Chrome;
import com.github.webdriverextensions.junitrunner.annotations.Firefox;
import com.github.webdriverextensions.junitrunner.annotations.ScreenshotsPath;
import com.github.webdriverextensions.junitrunner.annotations.TakeScreenshotOnFailure;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(WebDriverRunner.class)
@Chrome
@Firefox
@TakeScreenshotOnFailure
@ScreenshotsPath("target/screenshots")
public class WebDriverExtensionsExampleTest {

    @FindBy(xpath="//input[@id='lst-ib']")
    WebElement queryInput;
    @FindBy(name = "btnK")
    WebElement searchButton;
    @FindBy(id = "ires")
    WebElement searchResult;

    @Test
    public void searchGoogleForHelloWorldTest() {
    	
    	log.warn("!!! {}", "hi");
    	Bot.open("http://www.google.com");
        Bot.assertCurrentUrlContains("google");
        Bot.waitForElementToDisplay(queryInput);
        log.debug("wait {}", queryInput.getAttribute("name"));
        
        Bot.type("Hello World", queryInput);
        Bot.click(searchButton);

        Bot.waitFor(1, SECONDS);
        log.warn("!!! {}", searchResult.getAttribute("data-async-context"));
        Bot.assertAttributeContains("data-async-context", "query:Hello%20World", searchResult);
    }
    
    
    @Test
    public void searchGoogleForHelloWorldTestShouldFail() {
    	
    	log.warn("!!! {}", "hi");
    	Bot.open("http://www.google.com");
    	Bot.assertCurrentUrlContains("google");
    	Bot.waitForElementToDisplay(queryInput);
        log.debug("wait {}", queryInput.getAttribute("name"));
        
        Bot.type("Hello World", queryInput);
        Bot.click(searchButton);

        Bot.waitFor(1, SECONDS);
        log.warn("!!! {}", searchResult.getAttribute("data-async-context"));
        Bot.assertAttributeContains("data-async-context", "query:!!!!", searchResult);
    }
}