package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

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

		// Enter Username
		driver.findElement(By.id("username")).sendKeys("tomsmith");

		// Enter Password
		driver.findElement(By.id("password")).sendKeys("uperSecretPassword!");

		// Click on Login Button
		driver.findElement(By.tagName("button")).click();

		// Verify Login test
		System.out.println(driver.getTitle());

		// Close the Driver
		System.out.println("Test Completed");
		driver.close();

	}

}
