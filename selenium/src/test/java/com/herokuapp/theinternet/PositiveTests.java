package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.Assert;

public class PositiveTests {
	@Test
	public void Login() throws InterruptedException {
		System.out.println("Test Started");

		// Create Driver
		WebDriver driver = new ChromeDriver();

		// Open URL
		System.out.println("Page is loading");
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		driver.manage().window().maximize();

		// Enter User name
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");

		// Enter Password
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("SuperSecretPassword!");

		// Click on Login Button
		WebElement logInBtn = driver.findElement(By.tagName("button"));
		logInBtn.click();

		// Verifications:-

		// new URL
		String expectedUrl = "http://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual URL is not expected as Expected URL");

		// Successful Login Message
		WebElement Successmessage = driver.findElement(By.xpath("//div[@id='flash']"));
		String expectedmsg = "You logged into a secure area!";
		String actualmsg = Successmessage.getText();
		Assert.assertTrue(actualmsg.contains(expectedmsg),
				"Success message does not match.\nactualmsg:" + actualmsg + "\n Expected msg: " + expectedmsg);

		// Logout Button is visible
		WebElement logOutBtn = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logOutBtn.isDisplayed(), "Log out button is not present");

		// Close the Driver
		System.out.println("Test Completed");
		driver.close();
	}

}
