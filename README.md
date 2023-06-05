
# Integrate Jira with Selenium(Java)
To integrate [Jira](https://www.atlassian.com/software/jira) with [Selenium](https://www.selenium.dev/documentation/webdriver/) using Java, you can utilize the Jira REST API to create, update, or fetch Jira issues from your Selenium test scripts.

#### 1. Set up the Jira REST API dependencies:

Add the [Jira REST API client library](https://mvnrepository.com/artifact/net.rcarz/jira-client) to your Maven or Gradle dependencies. You can use the jira-rest-java-client library, which provides a Java client for Jira's REST API. Add the following dependency to your pom.xml (Maven).

```bash
<dependency>
    <groupId>net.rcarz</groupId>
    <artifactId>jira-client</artifactId>
    <version>0.5</version>
</dependency>

```

#### 2. Create basic tests using Selenium with TestNG and Java:

Create a new package under the src/test/java and create a test class(testNG) under the above created package.

```bash
	@Test(priority = 1) @jiraPolicy(logTicket = true)
	public static void test() {

		String actualResult = driver.getCurrentUrl();

		String expectedResult = "https://opensea.io/";

		Assert.assertEquals(actualResult, expectedResult);

	}
```

#### 3. Step 3: Create a new utility to Manage Jira issue

Let’s create a package inside the main folder com.util, which will contain two classes.

- Jira Policy - Run time Java Interface to get the failed test annotation in the run time.
- Jira Service Provider - This class contain method to Manage the Jira issue.
