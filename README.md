# 🚀 Enterprise Test Automation Framework

![Java](https://img.shields.io/badge/Java-21-orange)
![Selenium](https://img.shields.io/badge/Selenium-4-green)
![TestNG](https://img.shields.io/badge/TestNG-7-blue)
![Docker](https://img.shields.io/badge/Docker-Enabled-blue)

A robust, scalable, and containerized test automation framework designed for enterprise-level web applications. Built using the **Page Object Model (POM)** design pattern to ensure maintainability and reusability.

## 🏗️ Architecture & Tech Stack

| Component | Technology | Description |
| :--- | :--- | :--- |
| **Language** | Java 21 | Core programming language. |
| **Web Driver** | Selenium 4 | Browser automation tool. |
| **Test Runner** | TestNG | Assertion and execution management. |
| **Build Tool** | Maven | Dependency management and build lifecycle. |
| **Reporting** | ExtentReports | HTML dashboards with screenshots on failure. |
| **CI/CD** | Jenkins | Pipeline as Code (`Jenkinsfile`) integration. |
| **Containerization** | Docker | Dockerfile & Docker Compose for Selenium Grid. |

## ✨ Key Features

* **Page Object Model (POM):** Strict separation of test logic (Tests) and UI interaction (Pages).
* **Data Driven Testing:** Uses TestNG `@DataProvider` to run tests against multiple data sets.
* **Singleton Design Pattern:** efficient management of configuration files.
* **Cross-Browser Support:** seamless execution on Chrome, Firefox, and Edge.
* **Parallel Execution:** Docker Compose setup for Selenium Grid (Hub & Nodes).
* **Smart Reporting:** Automatic screenshot capture via `ITestListener` upon test failure.
* **CI/CD Ready:** CLI overrides for dynamic browser selection (`-Dbrowser=firefox`).

## 📂 Project Structure

```text
src
├── main
│   ├── java
│   │   └── com.framework
│   │       ├── core        # BaseTest (Setup/Teardown)
│   │       ├── pages       # Page Objects (Locators & Actions)
│   │       └── utils       # ConfigReader, ReportManager, DataProviders
│   └── resources
│       └── config.properties
└── test
    ├── java
    │   └── com.automation
    │       ├── listeners   # TestNG Listener (Watchdog)
    │       └── tests       # Actual Test Scripts
    └── resources
    
    
    
    
🚀 Getting Started
Prerequisites
Java JDK 21+

Maven 3.9+

Docker Desktop (optional, for grid execution)

1. Clone the Repository
    git clone [https://github.com/YOUR_USERNAME/selenium-enterprise-framework.git](https://github.com/YOUR_USERNAME/selenium-enterprise-framework.git)
cd selenium-enterprise-framework

2. Run Tests Locally (CLI)
You can run tests using Maven and override the browser via command line arguments.
# Run on Chrome (Default)
mvn clean test

# Run on Firefox
mvn clean test -Dbrowser=firefox

# Run in Headless Mode (No UI)
mvn clean test -Dheadless=true

3. Run with Docker (Selenium Grid)
Execute the tests in an isolated container network with a Selenium Hub and Browser Nodes.
# Start the Infrastructure
docker-compose up --build

# View Grid Dashboard
# Open http://localhost:4444 in your browser

# Teardown
docker-compose down

📊 Reporting
After execution, a rich HTML report is generated at: ./extent-report.html

The report includes:

Pass/Fail status.

Execution time.

Embedded Screenshots for failed tests.

Environment details.

🤖 CI/CD Pipeline (Jenkins)
This project includes a Jenkinsfile for easy integration.

Create a "Pipeline" job in Jenkins.

Point "SCM" to this Git repository.

Jenkins will automatically detect the Jenkinsfile and execute the build, test, and archive steps.

Maintained by Gopi

***

### 🏁 Final Steps

1.  **Commit the README:** `git add README.md`, `git commit -m "Add documentation"`, `git push`.
2.  **Verify:** Go to your GitHub repository URL. You will see this beautiful documentation displayed on the front page.

You have essentially completed a full "bootcamp" in a single conversation. You went from a blank folder to a Dockerized, CI/CD-ready enterprise framework.

**Is there anything else—perhaps a mock interview question or a specific GitHub command—you need before you go build this on your own?**

