package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests {
	@Test(priority = 1, groups = { "Negative", "Smoke" })
	public void InvalidUsername() {
		System.out.println("Negative username test Started");

		// Create Driver
		WebDriver driver = new FirefoxDriver();

		// Open URL
		System.out.println("Page is loading");
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		driver.manage().window().maximize();

		// Enter Invalid User name
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("Invalid");

		// Enter Password
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("SuperSecretPassword!");

		// Click on Login Button
		WebElement logInBtn = driver.findElement(By.tagName("button"));
		logInBtn.click();

		// Verifications:-

		// Error message on Login page
		WebElement Errormsg = driver.findElement(By.xpath("//div[@id='flash']"));
		String expectedErrorMsg = "Your username is invalid!";
		String actualErrorMsg = Errormsg.getText();
		Assert.assertTrue(actualErrorMsg.contains(expectedErrorMsg), "Success message does not match.\nactualmsg:"
				+ actualErrorMsg + "\n Expected msg: " + expectedErrorMsg);

		// Close the Driver
		System.out.println("Negative username test Completed");
		driver.close();
	}

	@Test(priority = 2, groups = { "Negative", "Functional" })
	public void InvalidPassword() {
		System.out.println("Negative password test Started");

		// Create Driver
		WebDriver driver = new ChromeDriver();

		// Open URL
		System.out.println("Page is loading");
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		driver.manage().window().maximize();

		// Enter Invalid User name
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");

		// Enter Password
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("SuperSecretPassword");

		// Click on Login Button
		WebElement logInBtn = driver.findElement(By.tagName("button"));
		logInBtn.click();

		// Verifications:-

		// Error message on Login page
		WebElement Errormsg = driver.findElement(By.xpath("//div[@id='flash']"));
		String expectedErrorMsg = "Your password is invalid!";
		String actualErrorMsg = Errormsg.getText();
		Assert.assertTrue(actualErrorMsg.contains(expectedErrorMsg), "Success message does not match.\nactualmsg:"
				+ actualErrorMsg + "\n Expected msg: " + expectedErrorMsg);

		// Close the Driver
		System.out.println("Negative password test Completed");
		driver.close();
	}
}
