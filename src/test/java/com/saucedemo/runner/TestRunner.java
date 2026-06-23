package com.saucedemo.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * Single entry point that wires Cucumber to TestNG.
 * <p>
 * {@code plugin = "pretty"} prints readable results to the console only. No HTML
 * or JSON report files are generated, as requested.
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.saucedemo.steps", "com.saucedemo.hooks"},
        plugin = {"pretty"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

    /**
     * Scenarios run sequentially here. Set {@code parallel = true} to run them in
     * parallel; Selenide isolates each thread's browser, so that is safe once the
     * Surefire/TestNG thread settings are configured.
     */
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
