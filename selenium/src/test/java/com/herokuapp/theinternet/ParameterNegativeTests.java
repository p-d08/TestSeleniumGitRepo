package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterNegativeTests {

	@Parameters({ "username", "password", "expectedMessage" })
	@Test
	public void ParameterTest(String username, String password, String expectedMessage) {
		System.out.println("Parameter test Started");
		System.out.println("username is : " + username + ", password is : " + password
				+ ", Expected Error message is : " + expectedMessage);

		// Create Driver
		WebDriver driver = new FirefoxDriver();

		// Open URL
		System.out.println("Page is loading");
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		driver.manage().window().maximize();

		// Enter Invalid User name
		WebElement usernameLocator = driver.findElement(By.id("username"));
		usernameLocator.sendKeys(username);

		// Enter Password
		WebElement passwordLocator = driver.findElement(By.id("password"));
		passwordLocator.sendKeys(password);

		// Click on Login Button
		WebElement logInBtn = driver.findElement(By.tagName("button"));
		logInBtn.click();

		// Verifications:-

		// Error message on Login page
		WebElement Errormsg = driver.findElement(By.xpath("//div[@id='flash']"));
		String actualErrorMsg = Errormsg.getText();
		Assert.assertTrue(actualErrorMsg.contains(expectedMessage), "Success message does not match.\n actualmsg:"
				+ actualErrorMsg + "\n Expected msg: " + expectedMessage);

		// Close the Driver
		System.out.println("Parameter test Completed");
		driver.close();
	}
}
