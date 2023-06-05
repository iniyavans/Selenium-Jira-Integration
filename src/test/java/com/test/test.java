package com.test;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.util.jiraPolicy;

public class test extends browserSetup {

	@BeforeTest()
	public static void launchbrowser() {

		String OpenSea = "https://opensea.io/";

		browserSetup.setup(OpenSea);

	}

	
	@Test(priority = 1) @jiraPolicy(logTicket = true)
	public static void LS001_verify_page_URL() {

		String actualResult = driver.getCurrentUrl();

		String expectedResult = "https://opensea.io/";

		Assert.assertEquals(actualResult, expectedResult);

	}

	@Test(priority = 2) @jiraPolicy(logTicket = true)
	public static void LS002_verify_page_title() {

		String actualResult = driver.getTitle();

		String expectedResult = "Rarible — aggregated NFT marketplace with rewards";

		Assert.assertEquals(actualResult, expectedResult);

	}

	@AfterTest()
	public static void close() {
		
		driver.quit();
		
	}

}
