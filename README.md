# Selenide + Cucumber Test Automation Framework

A small, easy-to-read example of a UI test automation framework. It uses
**Selenide** (to control the browser), **Cucumber** (to write tests in plain
English), and **TestNG** (to run them). The tests run against a free practice
website: https://www.saucedemo.com/.

It was built as a learning reference. It is small enough to read from start to
finish, and it covers four common user journeys: **login, inventory, cart, and
checkout**.

With Cucumber, the tests are written as readable steps like:

```gherkin
Scenario: A user can log in with valid details
  Given the user is on the login page
  When the user logs in with valid credentials
  Then the products page is shown
```

These plain-English steps are then connected to Java code that drives the browser.

## Prerequisites

Before running the project, install the following:

- **Java (JDK) 17 or newer**
- **Maven 3.9 or newer**
- **A web browser** (Chrome is used by default)

You do **not** need to download any browser drivers by hand. The framework
downloads the correct one automatically the first time you run the tests.

### Installing Java (JDK)

1. Download a JDK 17 (or newer) build. Any of these official distributions work:
   - [Eclipse Temurin (Adoptium)](https://adoptium.net/temurin/releases/) – recommended, free
   - [Oracle JDK](https://www.oracle.com/java/technologies/downloads/)
   - [Microsoft Build of OpenJDK](https://learn.microsoft.com/java/openjdk/download)
2. Run the installer and accept the defaults. On Windows, the Temurin installer
   can set `JAVA_HOME` and update the `PATH` for you if you tick those optional
   features during setup.
3. Official install guides:
   [Adoptium installation docs](https://adoptium.net/installation/) ·
   [Oracle JDK installation guide](https://docs.oracle.com/en/java/javase/17/install/overview-jdk-installation.html)

### Installing Maven

1. Download the **Binary zip archive** from the
   [Apache Maven download page](https://maven.apache.org/download.cgi).
2. Unzip it to a permanent location, for example `C:\Tools\apache-maven-3.9.x`.
3. Follow the official
   [Maven installation instructions](https://maven.apache.org/install.html).

> Tip: if you use a package manager you can skip the manual download.
> Windows: `winget install Microsoft.OpenJDK.17` and `winget install Apache.Maven`.
> macOS: `brew install temurin maven`. Linux: use your distro's package manager
> or [SDKMAN!](https://sdkman.io/).

### Setting up the PATH and JAVA_HOME (Windows)

If the installers did not configure these for you, set them manually:

1. Press **Start**, type *"Edit the system environment variables"*, open it, then
   click **Environment Variables…**
2. Under **System variables**, click **New** and add:
   - Variable name: `JAVA_HOME`
   - Variable value: your JDK folder, e.g. `C:\Program Files\Eclipse Adoptium\jdk-17`
3. Add a `MAVEN_HOME` (or `M2_HOME`) variable pointing to your Maven folder, e.g.
   `C:\Tools\apache-maven-3.9.x`.
4. Select the **Path** variable, click **Edit**, then **New**, and add these two
   entries:
   - `%JAVA_HOME%\bin`
   - `%MAVEN_HOME%\bin`
5. Click **OK** on every dialog, then **open a new terminal** so the changes take
   effect.

On macOS/Linux, add the equivalent lines to your shell profile
(`~/.zshrc` or `~/.bashrc`):

```bash
export JAVA_HOME=/path/to/jdk-17
export PATH="$JAVA_HOME/bin:/path/to/apache-maven/bin:$PATH"
```

### Verifying the installation

Open a **new** terminal and confirm both tools are on the `PATH`:

```powershell
java -version    # should report 17 or newer
mvn -version     # should report 3.9 or newer (and the Java version it found)
```

If either command is not recognised, the `PATH` entry is missing or the terminal
was opened before the change was saved.

## IDE setup

You can use **IntelliJ IDEA** (recommended) or **Eclipse**. To get the most out
of the `.feature` files, install these free plugins in your IDE:

- **Cucumber for Java** – lets you run scenarios and jump from a step to its Java code
- **Gherkin** – adds colour and formatting to the `.feature` files

In IntelliJ: `File > Settings > Plugins > Marketplace`, search for the plugin
name, click **Install**, then restart the IDE.

## Project structure

```
src/test/
  java/com/saucedemo/
    pages/      Page classes (LoginPage, InventoryPage, CartPage, CheckoutPage)
                One class per screen, holding the page's elements and actions.
                BasePage is the shared parent for the pages that show the
                saucedemo header (title + cart); they extend it to inherit it.
    steps/      Step classes (LoginSteps, InventorySteps, CheckoutSteps)
                The "glue" connecting the English steps to the page classes.
    hooks/      Hooks      Sets up the browser and closes it after each test.
    runner/     TestRunner The entry point that runs all the tests.
  resources/
    features/   login.feature, inventory.feature, checkout.feature
                The tests written in plain English (Gherkin).
pom.xml         Lists the project's libraries and build settings.
```

There is no `src/main` folder. A test project has no app of its own to ship, so
everything lives under `src/test`.

## How to run the tests

Open a terminal in the project folder and run:

```powershell
mvn test
```

This runs all the `.feature` files and prints the results to the console.

You can also right-click a `.feature` file in your IDE and choose **Run** to run
a single feature.

### Running only some tests (tags)

Each feature file is tagged at the top so you can run them selectively:

| Tag          | Feature file        |
|--------------|---------------------|
| `@login`     | `login.feature`     |
| `@inventory` | `inventory.feature` |
| `@checkout`  | `checkout.feature`  |

Run a single feature by its tag:

```powershell
mvn test "-Dcucumber.filter.tags=@checkout"
```

Combine tags with `and`, `or`, and `not`:

```powershell
mvn test "-Dcucumber.filter.tags=@login or @checkout"   # either feature
mvn test "-Dcucumber.filter.tags=not @checkout"          # everything except checkout
```

### Running on a different browser

```powershell
mvn test "-Dselenide.browser=chrome"   # default
mvn test "-Dselenide.browser=firefox"
mvn test "-Dselenide.browser=edge"
```

### Running without opening a browser window (headless)

```powershell
mvn test "-Dselenide.headless=true"
```

## What I would add next (left out on purpose)

- A reporting layer for stakeholder visibility, once reports are wanted.
- Screenshot-on-failure is already a Selenide default; wiring it into a report is
  the natural next step.
- A CI pipeline (GitHub Actions / Jenkins / ADO) running
  `mvn test "-Dselenide.headless=true"`.
- Broader tagging (e.g. `@smoke`, `@regression`) layered on top of the
  per-feature tags to group scenarios across features.
- Running tests in parallel.

## References (official documentation)

### Tools used in this framework

- **Selenide** – [Official site](https://selenide.org/) ·
  [Documentation](https://selenide.org/documentation.html) ·
  [Quick start](https://selenide.org/quick-start.html) ·
  [Javadoc](https://selenide.org/javadoc.html)
- **Cucumber** – [Official site](https://cucumber.io/) ·
  [Docs home](https://cucumber.io/docs/cucumber/) ·
  [Cucumber-JVM (Java)](https://github.com/cucumber/cucumber-jvm) ·
  [Gherkin reference](https://cucumber.io/docs/gherkin/reference/)
- **TestNG** – [Official site](https://testng.org/) ·
  [Documentation](https://testng.org/doc/documentation-main.html)
- **Selenium / Selenium Manager** – [Documentation](https://www.selenium.dev/documentation/)
  (Selenide bundles Selenium and uses Selenium Manager to resolve the browser driver)
- **Maven Surefire plugin** – [Documentation](https://maven.apache.org/surefire/maven-surefire-plugin/)
  (runs the tests during `mvn test`)

### Languages and build tools

- **Java SE 17** – [API docs](https://docs.oracle.com/en/java/javase/17/docs/api/index.html) ·
  [Language reference](https://docs.oracle.com/javase/specs/)
- **Apache Maven** – [Documentation](https://maven.apache.org/guides/) ·
  [Getting started guide](https://maven.apache.org/guides/getting-started/index.html)
