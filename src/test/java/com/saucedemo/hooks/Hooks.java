package com.saucedemo.hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * Cucumber lifecycle hooks.
 * <p>
 * This is the only "framework wiring" left, and it is tiny. There is no driver
 * factory: Selenide creates and owns the browser. The browser type is read from
 * the {@code selenide.browser} system property automatically, so switching
 * browser needs no code change (see the README).
 */
public class Hooks {

    @Before
    public void configureSelenide() {
        Configuration.baseUrl = "https://www.saucedemo.com";
        Configuration.browserSize = "1280x900";
        Configuration.timeout = 10_000; // default condition timeout in ms
        Configuration.pageLoadStrategy = "eager";
        // Configuration.browser defaults to "chrome" and is overridden by -Dselenide.browser
        // Configuration.headless is overridden by -Dselenide.headless=true
    }

    @After
    public void closeBrowser(Scenario scenario) {
        // On failure, grab a screenshot and attach it to the scenario so it shows
        // inline in the Cucumber HTML report. Selenide also saves one to disk by
        // default; attaching makes it visible in the report itself.
        if (scenario.isFailed() && WebDriverRunner.hasWebDriverStarted()) {
            byte[] screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        // Close after each scenario so every scenario starts from a clean session.
        Selenide.closeWebDriver();
    }
}
