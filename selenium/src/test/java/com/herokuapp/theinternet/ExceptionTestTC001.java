package com.herokuapp.theinternet;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class ExceptionTestTC001 {
	WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	private void Login() {
		// Create Driver
		driver = new ChromeDriver();
	}

	@Test
	public void noSuchElementFound() {
		// Open page
		System.out.println("Page is loading");
		String url = "https://practicetestautomation.com/practice-test-exceptions/";
		driver.get(url);
		driver.manage().window().maximize();

		// Click on Add Button
		WebElement logInBtn = driver.findElement(By.id("add_btn"));
		logInBtn.click();

		// Adding implicit wait
		/* driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); */

		// Adding explicit wait is ideal
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement row2inputfield = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));

		// Row 2 input field is displayed
		Assert.assertTrue(row2inputfield.isDisplayed(), "Input Field is not displayed");
	}

	@Test
	public void elementNotInteractableException() {
		// Open page
		System.out.println("Page is loading");
		String url = "https://practicetestautomation.com/practice-test-exceptions/";
		driver.get(url);
		driver.manage().window().maximize();

		// Click on Add Button
		WebElement logInBtn = driver.findElement(By.id("add_btn"));
		logInBtn.click();

		// Wait for the second row to load
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement row2inputfield = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));

		// Type text into the second input field
		row2inputfield.sendKeys("Text");

		// Push Save button using locator By.name(“Save”)
		WebElement savebtn = driver.findElement(By.xpath("//div[@id='row2']/button[@id='save_btn']"));
		savebtn.click();

		// Verify text saved
		WebElement confirmationmsg = driver.findElement(By.id("confirmation"));
		String message = confirmationmsg.getText();
		Assert.assertEquals(message, "Row 2 was saved", "Confirmation message text is not present");
	}

	@Test
	public void invalidElementStateException() {
		// Open page
		System.out.println("InvalidElementStateException Started");
		String url = "https://practicetestautomation.com/practice-test-exceptions/";
		driver.get(url);
		driver.manage().window().maximize();

		// Click on Edit button

		// Clear input field which is disabled so we need to click first on edit
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement inputfield = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='input-field']")));
		WebElement editbtn = driver.findElement(By.id("edit_btn"));
		editbtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(inputfield));
		inputfield.clear();

		// Type text into the input field
		inputfield.sendKeys("Text");

		// Click on save button
		WebElement savebtn = driver.findElement(By.id("save_btn"));
		savebtn.click();

		// Verify text changed
		String text = inputfield.getAttribute("value");
		Assert.assertEquals(text, "Text", "Text message is not present");

		// Verify Confirmation message
		WebElement confirmationmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation")));
		String message = confirmationmsg.getText();
		Assert.assertEquals(message, "Row 1 was saved", "Confirmation message text is not present");
	}

	@Test
	public void staleElementReferenceException() {
		// Open page
		System.out.println("Page is loading");
		String url = "https://practicetestautomation.com/practice-test-exceptions/";
		driver.get(url);
		driver.manage().window().maximize();

		// Find the instructions text element
		WebElement instructions = driver.findElement(By.id("instructions"));

		// Push add button
		WebElement logInBtn = driver.findElement(By.id("add_btn"));
		logInBtn.click();

		// Verify instruction text element is no longer displayed
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("instructions"))),
				"Intructions are still displayed");
	}

	@AfterMethod
	private void teardown() {
		// Close the Driver
		System.out.println("Test Completed & Driver getting Close");
		driver.close();
	}

}
