package com.saucedemo.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * Single entry point that wires Cucumber to TestNG.
 * <p>
 * Plugins:
 * <ul>
 *   <li>{@code pretty} – readable results in the console.</li>
 *   <li>{@code html} – a self-contained report at
 *       {@code target/cucumber-reports/cucumber.html}. Failure screenshots
 *       attached in {@link com.saucedemo.hooks.Hooks} appear inline.</li>
 *   <li>{@code json} – machine-readable results at
 *       {@code target/cucumber-reports/cucumber.json} (handy for CI dashboards).</li>
 *   <li>{@code AllureCucumber7Jvm} – writes raw Allure results to
 *       {@code target/allure-results}, which {@code mvn allure:report} renders
 *       into the Allure HTML report.</li>
 * </ul>
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.saucedemo.steps", "com.saucedemo.hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {

    /**
     * Scenarios run sequentially by default ({@code parallel = false}).
     * <p>
     * Parallel execution is wired up and safe to enable: Selenide keeps a
     * separate browser per thread (it stores the WebDriver in a thread local),
     * and the {@code @After} hook closes each scenario's browser independently.
     * To turn it on, set {@code parallel = true} below and choose a thread count
     * at run time, e.g. {@code mvn test -Ddataproviderthreadcount=4}.
     */
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
