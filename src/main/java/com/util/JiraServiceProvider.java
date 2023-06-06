package com.util;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue.FluentCreate;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

public class JiraServiceProvider {

	public JiraClient jira;

	public String projectName;

	public JiraServiceProvider(String JiraUrl, String userName, String Password, String projectName) {

		BasicCredentials crds = new BasicCredentials(userName, Password);

		jira = new JiraClient(JiraUrl, crds);

		this.projectName = "LSWI";

	}

	public void createJitaTicket(String issueType, String summary, String description, String repoterName) {

		try {

			FluentCreate fluentCreate = jira.createIssue(projectName, issueType);

			fluentCreate.field(Field.SUMMARY, summary);

			fluentCreate.field(Field.DESCRIPTION, description);

			fluentCreate.execute();

		} catch (JiraException e) {

			e.printStackTrace();

		}

	}

}
