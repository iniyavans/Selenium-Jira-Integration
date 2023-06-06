package com.listners;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.util.JiraServiceProvider;
import com.util.jiraPolicy;

public class jiraTestListners implements ITestListener {

	public void onTestFailure(ITestResult result) {

		jiraPolicy JiraPolicy = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(jiraPolicy.class);

		boolean isnewTicket = JiraPolicy.logTicket();

		if (isnewTicket) {
			// Base Url of Jira project
			String project_base_URL = "https://learnseleniumwithiniyavan.atlassian.net";

			// User registered email address with a Jira
			String emailAddress = "iniyavan3107@gmail.com";

			// User password access token
			String jiraAccessToken = "ATATT3xFfGF0iFm0aW1ZNxsK-D00sX-OWKwtVdg0UXPE5e3QxLZTfUHC5HPXOLucsJu5_1Nf1AwtMymvW4R_c8kj5RHMjbO-10PE9xJRbWfcf3nH1TfOokV15wugwrX3qyvm-mQy5Pyq9JRGhu-x5hSm9leBO_iNZsUReq-bDV227B7jK9n-DE8=C8271574";

			// Project name
			String projectName = "Learn Selenium";

			// Create a object with a Jira service provider
			JiraServiceProvider jiraservice = new JiraServiceProvider(project_base_URL, emailAddress, jiraAccessToken,
					projectName);

			// Type of the ticket
			String ticketType = "Bug";

			// Get the method name of failed test case annotation
			String issueSummery = result.getMethod().getConstructorOrMethod().getMethod().getName() + " "
					+ "Test Case Failed";

			// Get the method throw exception message
			String issueDescription = "Failure Reason\n\n" + result.getThrowable().getMessage();

			// Concatenate the issue description with the full stack trace
			issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));

			// Name of the ticket Reporter
			String repoterName = "Iniyavan";

			// Create a new Jira ticket with a help of Jira service
			jiraservice.createJitaTicket(ticketType, issueSummery, issueDescription, repoterName);

		}
	}

}
