package com.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pages.LoginPage;
import com.utilities.WaitUtils;

public class LoginPageTest extends BaseClass {
	LoginPage loginPage;
	WaitUtils waitUtils;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod()
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
	}

	@Test
	public void Login_StandardUser() {

		loginPage.login("standard");
		loginPage.verifyTitle();
		driver.quit();
	}

	@Test
	public void Login_lockedOutUser() {
		loginPage.login("locked_out");		
		loginPage.userLockedOut();
		Assert.assertFalse(loginPage.userOnInventoryPage(), "User is not on home page");
		driver.quit();
	}

	@Test
	public void Login_problemUser() {
		loginPage.login("problem");
		Assert.assertTrue(loginPage.verifyTitle(), "User is not on home page");
		if (!loginPage.userOnInventoryPage()) {
			waitUtils.captureScreenshots("userNotonPage");
			Assert.fail("user is not on Inventory Page");
		}
		driver.quit();
	}

	@Test
	public void Login_PerformanceGlitchUser() {
		loginPage.login("performance_glitch_user");
		long curreTim = System.currentTimeMillis();
		long elemappeared = loginPage.elementsNotVisibleAFterQuickLoad();
		System.out.println("time after elment loaded " + (elemappeared - curreTim) + " milliseconds");
		driver.quit();

	}

	@Test
	public void Login_InvalidUser() {
		loginPage.login("invalid");
		Assert.assertTrue(loginPage.errorMsgExists(), "Error message not displayed");
		driver.quit();

	}
}