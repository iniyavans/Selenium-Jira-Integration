package com.test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class browserSetup {

	public static ChromeDriver driver;

	public static void setup(String baseURL) {

		System.setProperty("webdriver.http.factory", "jdk-http-client");

		ChromeOptions options = new ChromeOptions();

		options.setHeadless(true);

		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver(options);

		driver.manage().window().maximize();

		driver.get(baseURL);

	}

}
