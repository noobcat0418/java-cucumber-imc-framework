# E-Commerce Web Application - Test Automation Framework

BDD test automation framework for the e-commerce web application built with Java, Selenium WebDriver, and Cucumber.

---

## Tech Stack

| Technology | Purpose |
|------------|---------|
| Java 17 | Programming language |
| Selenium 4.16 | Browser automation |
| Cucumber 7.15 | BDD framework |
| JUnit 4 | Test runner |
| Maven | Build and dependency management |
| WebDriverManager | Automatic driver management |
| AssertJ | Fluent assertions |

---

## Project Structure

```
src/
 ├── main/java/com/saucedemo/
 │   ├── pages/                    # Page Object classes
 │   │   ├── BasePage.java
 │   │   ├── LoginPage.java
 │   │   ├── InventoryPage.java
 │   │   ├── CartPage.java
 │   │   ├── CheckoutPage.java
 │   │   └── ProductDetailPage.java
 │   └── utils/                    # Utility classes
 │       ├── DriverManager.java
 │       └── ConfigReader.java
 └── test/
     ├── java/com/saucedemo/
     │   ├── runners/
     │   │   └── TestRunner.java
     │   └── stepdefinitions/
     │       ├── LoginSteps.java
     │       ├── CheckoutSteps.java
     │       └── Hooks.java
     └── resources/
         ├── features/
         │   ├── login.feature
         │   └── checkout.feature
         └── config.properties
```

---

## Prerequisites

- Java JDK 17 or higher
- Maven 3.8+
- Chrome, Firefox, or Edge browser installed

Verify installation:
```bash
java -version
mvn -version
```

---

## Setup

1. Clone the repository
   ```bash
   git clone https://github.com/noobcat0418/java-cucumber-automation-framework.git
   ```

2. Navigate to project directory
   ```bash
   cd java-cucumber-automation-framework
   ```

3. Install dependencies
   ```bash
   mvn clean install -DskipTests
   ```

---

## Running Tests

### Run all tests
```bash
mvn test
```

### Run by tags
```bash
mvn test -Dcucumber.filter.tags="@smoke"
mvn test -Dcucumber.filter.tags="@login"
mvn test -Dcucumber.filter.tags="@e2e"
```

### Run with different browsers
```bash
mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
```

### Run in headless mode
```bash
mvn test -Dheadless=true
```

---

## Test Reports

After test execution, reports are generated at:

| Report Type | Location |
|-------------|----------|
| HTML Report | `target/cucumber-reports/cucumber.html` |
| JSON Report | `target/cucumber-reports/cucumber.json` |
| XML Report | `target/cucumber-reports/cucumber.xml` |

---

## Configuration

Edit `src/test/resources/config.properties`:

```properties
base.url=https://www.saucedemo.com
browser=chrome
headless=false
implicit.wait=10
default.username=standard_user
default.password=secret_sauce
```

---

## Test Tags

| Tag | Description |
|-----|-------------|
| `@smoke` | Critical path tests |
| `@e2e` | End-to-end scenarios |
| `@positive` | Happy path tests |
| `@negative` | Error handling tests |
| `@login` | Login module tests |
| `@checkout` | Checkout module tests |

---

## Author

**Mike Ryan B. Cervantes**
Senior Quality Assurance Engineer
