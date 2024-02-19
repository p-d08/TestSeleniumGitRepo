package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class BeforeAndAfterAnnotation {
	WebDriver driver;

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	private void Login(String browser) {
		System.out.println("Driver is : " + browser);
		// Create Driver
		switch (browser) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		default:
			driver = new ChromeDriver();
			break;
		}
	}

	@Parameters({ "username", "password", "expectedUrl", "expectedMessage" })
	@Test(priority = 1)
	public void Positive(String username, String password, String expectedUrl, String expectedMessage) {

		// Open URL
		System.out.println("Page is loading");
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		driver.manage().window().maximize();

		// Enter User name
		WebElement usernameLocator = driver.findElement(By.id("username"));
		usernameLocator.sendKeys(username);

		// Enter Password
		WebElement passwordLocator = driver.findElement(By.id("password"));
		passwordLocator.sendKeys(password);

		// Inputs
		System.out.println("username is : " + username + ", password is : " + password + ", Expected URL is : "
				+ expectedUrl + ", Expected msg is : " + expectedMessage);

		// Click on Login Button
		WebElement logInBtn = driver.findElement(By.tagName("button"));
		logInBtn.click();

		// Verifications:-

		// new URL
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual URL is not expected as Expected URL");

		// Successful Login Message
		WebElement Successmessage = driver.findElement(By.xpath("//div[@id='flash']"));
		String actualmsg = Successmessage.getText();
		Assert.assertTrue(actualmsg.contains(expectedMessage),
				"Success message does not match.\nactualmsg:" + actualmsg + "\n Expected msg: " + expectedMessage);

		// Logout Button is visible
		WebElement logOutBtn = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logOutBtn.isDisplayed(), "Log out button is not present");
	}

	@Parameters({ "username", "password", "expectedMessage" })
	@Test(enabled = false)
	public void Negative(String username, String password, String expectedMessage) {

		// Open URL
		System.out.println("Test Started and Page is loading");
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		driver.manage().window().maximize();

		// Enter User name
		WebElement usernameLocator = driver.findElement(By.id("username"));
		usernameLocator.sendKeys(username);

		// Enter Password
		WebElement passwordLocator = driver.findElement(By.id("password"));
		passwordLocator.sendKeys(password);

		// Inputs
		System.out.println("username is : " + username + ", password is : " + password
				+ ", Expected Error message is : " + expectedMessage);

		// Click on Login Button
		WebElement logInBtn = driver.findElement(By.tagName("button"));
		logInBtn.click();

		// Verifications:-

		// Error message on Login page
		WebElement Errormsg = driver.findElement(By.xpath("//div[@id='flash']"));
		String actualErrorMsg = Errormsg.getText();
		Assert.assertTrue(actualErrorMsg.contains(expectedMessage), "Success message does not match.\n actualmsg:"
				+ actualErrorMsg + "\n Expected msg: " + expectedMessage);

	}

	@AfterMethod
	private void teardown() {
		// Close the Driver
		System.out.println("Test Completed & Driver getting Close");
		driver.close();
	}

}
