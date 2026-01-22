# Java Cucumber Selenium Framework

A robust BDD (Behavior-Driven Development) test automation framework for [SauceDemo](https://www.saucedemo.com) e-commerce web application using Java, Selenium WebDriver, and Cucumber.

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)
![Selenium](https://img.shields.io/badge/Selenium-4.16.1-green?style=flat-square&logo=selenium)
![Cucumber](https://img.shields.io/badge/Cucumber-7.15.0-brightgreen?style=flat-square&logo=cucumber)
![Maven](https://img.shields.io/badge/Maven-3.8+-purple?style=flat-square&logo=apache-maven)

---

## Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Installation](#-installation)
- [Running Tests](#-running-tests)
- [Test Reports](#-test-reports)
- [Configuration](#-configuration)
- [Author](#-author)

---

## Features

- ✅ **Page Object Model (POM)** design pattern for maintainability
- ✅ **BDD approach** with Gherkin syntax for readable test scenarios
- ✅ **Cross-browser support** (Chrome, Firefox, Edge)
- ✅ **Headless execution** for CI/CD pipelines
- ✅ **Automatic screenshot capture** on test failure
- ✅ **Configurable test data** via properties file
- ✅ **Detailed HTML reports** with Cucumber reporting
- ✅ **Thread-safe WebDriver** management for parallel execution

---

## Tech Stack

| Technology | Purpose |
|------------|---------|
| Java 17 | Programming language |
| Selenium 4.16 | Browser automation |
| Cucumber 7.15 | BDD framework |
| JUnit 4 | Test runner |
| Maven | Build & dependency management |
| WebDriverManager | Automatic driver management |
| AssertJ | Fluent assertions |

---

## Project Structure

```
java-cucumber-framework/
├── src/
│   ├── main/java/com/saucedemo/
│   │   ├── pages/                    # Page Object classes
│   │   │   ├── BasePage.java
│   │   │   ├── LoginPage.java
│   │   │   ├── InventoryPage.java
│   │   │   ├── CartPage.java
│   │   │   ├── CheckoutPage.java
│   │   │   └── ProductDetailPage.java
│   │   └── utils/                    # Utility classes
│   │       ├── DriverManager.java
│   │       └── ConfigReader.java
│   └── test/
│       ├── java/com/saucedemo/
│       │   ├── runners/
│       │   │   └── TestRunner.java   # Cucumber test runner
│       │   └── stepdefinitions/      # Step definition classes
│       │       ├── LoginSteps.java
│       │       ├── CheckoutSteps.java
│       │       └── Hooks.java
│       └── resources/
│           ├── features/             # Cucumber feature files
│           │   ├── login.feature
│           │   └── checkout.feature
│           └── config.properties     # Configuration file
└── pom.xml
```

---

## Prerequisites

Before running this project, ensure you have:

- **Java JDK 17** or higher → [Download](https://adoptium.net/)
- **Maven 3.8+** → [Download](https://maven.apache.org/download.cgi)
- **Chrome/Firefox/Edge** browser installed
- **Git** → [Download](https://git-scm.com/)

Verify installation:
```bash
java -version
mvn -version
```

---

## Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/noobcat0418/java-cucumber-framework.git
   ```

2. **Navigate to project directory**
   ```bash
   cd java-cucumber-framework
   ```

3. **Install dependencies**
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
# Smoke tests only
mvn test -Dcucumber.filter.tags="@smoke"

# Login tests only
mvn test -Dcucumber.filter.tags="@login"

# End-to-end tests
mvn test -Dcucumber.filter.tags="@e2e"
```

### Run with different browsers
```bash
# Firefox
mvn test -Dbrowser=firefox

# Edge
mvn test -Dbrowser=edge

# Chrome (default)
mvn test -Dbrowser=chrome
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

Open the HTML report in your browser for detailed results.

---

## Configuration

Edit `src/test/resources/config.properties`:

```properties
# Application URL
base.url=https://www.saucedemo.com

# Browser settings
browser=chrome
headless=false

# Timeouts (seconds)
implicit.wait=10

# Test credentials
default.username=standard_user
default.password=secret_sauce
```

---

## Test Tags Reference

| Tag | Description |
|-----|-------------|
| `@smoke` | Critical path tests |
| `@e2e` | End-to-end scenarios |
| `@positive` | Happy path tests |
| `@negative` | Error handling tests |
| `@login` | Login module tests |
| `@checkout` | Checkout module tests |

---

## Sample Test Scenario

```gherkin
@smoke @e2e
Scenario: Complete checkout with valid information
  Given I am logged in as "standard_user"
  And I have added "Sauce Labs Backpack" to the cart
  When I navigate to the cart
  And I click checkout
  And I enter checkout information:
    | firstName | lastName | postalCode |
    | John      | Doe      | 12345      |
  And I click continue
  And I click finish
  Then I should see the order confirmation message "Thank you for your order!"
```

---

## Author

**Mike Ryan B. Cervantes**  
Senior Quality Assurance Engineer

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-blue?style=flat-square&logo=linkedin)](https://www.linkedin.com/in/yourusername)
[![Email](https://img.shields.io/badge/Email-Contact-red?style=flat-square&logo=gmail)](mailto:cervantesmikeryan24@gmail.com)

---

 If you found this helpful, please give it a star!
