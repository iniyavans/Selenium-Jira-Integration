
# Integrate Jira with Selenium(Java)
[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)

To integrate [Jira](https://www.atlassian.com/software/jira) with [Selenium](https://www.selenium.dev/documentation/webdriver/) using Java, you can utilize the Jira REST API to create, update, or fetch Jira issues from your Selenium test scripts.

#### Step 1. Set up the Jira REST API dependencies:

Add the [Jira REST API client library](https://mvnrepository.com/artifact/net.rcarz/jira-client) to your Maven or Gradle dependencies. You can use the jira-rest-java-client library, which provides a Java client for Jira's REST API. Add the following dependency to your pom.xml (Maven).

```xml
<dependency>
    <groupId>net.rcarz</groupId>
    <artifactId>jira-client</artifactId>
    <version>0.5</version>
</dependency>

```

#### Step 2. Create basic tests using Selenium with TestNG and Java:

Create a new package under the src/test/java and create a test class(testNG) under the above created package.

```java
	@Test(priority = 1)
	public static void test() {

	// wirte a test here

	}
```

#### Step 3: Create a new utility to Manage Jira issue

Let’s create a package inside the main folder com.util, which will contain two classes.

- Jira Policy - Run time Java Interface to get the failed test annotation in the run time.
- Jira Service Provider - This class contain method to Manage the Jira issue.

```1. Jira Policy:```

Create a new interface under the com.util and name the class as a "jiraPolicy"

```java
@Retention(RetentionPolicy.RUNTIME)
public @interface jiraPolicy {
	
	boolean logTicket();

}
```

After that we need to give the jira policy's logTicket funtion as True with the test annotation.

```java
@jiraPolicy(logTicket = true)
```

Change the above test annotation like below

```java
	@Test(priority = 1) @jiraPolicy(logTicket = true)
	public static void test() {
	
	// wirte a test here
	
	}
```

If the Test anootation get false, the run time interface automatically called.

```2. Jira Service Provider:```

In the Jira service provider we can manage(Create, Update, Remove) the jira tickets.

```java
FluentCreate fluentCreate = jira.createIssue(projectName, issueType);

			fluentCreate.field(Field.SUMMARY, summary);

			fluentCreate.field(Field.DESCRIPTION, description);

			fluentCreate.execute();
```
#### 4. Create Listener to notify test failures:
Create a listener so that each time a test fails, it should automatically create the Jira issue method. This listener keeps track of test failures and executes specified code.

This example names the listener as jiratestListener.java, created inside package com.listeners.

```java
public class jiraTestListners implements ITestListener {

	public void onTestFailure(ITestResult result) {

		jiraPolicy JiraPolicy = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(jiraPolicy.class);

		boolean isnewTicket = JiraPolicy.logTicket();

		if (isnewTicket) {
		
			JiraServiceProvider jiraservice = new JiraServiceProvider(projectbaseURL, emailAddress, jiraAccessToken,
					projectName);

			String issueSummery = result.getMethod().getConstructorOrMethod().getMethod().getName() + " "
					+ "Test Case Failed";

			String issueDescription = "Failure Reason\n\n" + result.getThrowable().getMessage();

			issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));

			jiraservice.createJitaTicket(ticketType, issueSummery, issueDescription, repoterName);

		}
	}

}
```
Refer the below Points when creating a JiraServiceProvider object:

```projectbaseURL``` -  This should be a company Jira URL. If someone is using the Jira cloud it will be something like: https://abc.atlassian.net

```emailAddress``` - The email address should be a registred address with a above given jira account.

```Password``` - The Password is a Jira clouds Pasword API Token, [click Here](https://support.atlassian.com/atlassian-account/docs/manage-api-tokens-for-your-atlassian-account/) to create a new API Token.

```projectName``` - Name of the Project which one created under the mentioned Jira Account.

```ticketType``` - Its about the Type of the Ticket, It should be the Bug, Tash or Epic.

```repoterName``` - Name of the reporter name. Note: The repoter name should be available in the list of repoters name.

#### Step 5: Create a new Test Suite

Once we have complete all the above required set up, we need to have a test-suite.xml file configured to run our tests. Our test suite xml file looks like below.

```xml
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >
<suite thread-count="2" name="Suite" parallel="none" verbose="3">
	<listeners>
		<listener class-name="com.listners.jiraTestListners" />
	</listeners>
   <test name="My NFT Marketplace" parallel="none" thread-count="5" >
	   <parameter name="browser" value="chrome" />
       <classes> 
          <class name="com.test.test" />
       </classes>
   </test>
 </suite>
 ```
#### Step 6: Execute your tests

After setup the test suite, execute the test suite to run our test cases. After execution, view the result in the console.

#### Step 7: Ensure ticket created in Jira Account

After sucessfully execute the test cases, navigated to the Jira account and verify the new tickets.

![Image](https://photos.pinksale.finance/file/pinksale-logo-upload/1686028357085-063d190f1bffa8f37cb6a3f2ae6e8e15.png)
