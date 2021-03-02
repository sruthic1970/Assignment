package com.pages;

import java.util.List;

import org.openqa.selenium.By;
//import com.pages.basePage.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseClass;
import com.utilities.WaitUtils;

public class LoginPage extends BaseClass {
	WaitUtils waitUtils;

	public LoginPage() {
		PageFactory.initElements(driver, this);
		this.waitUtils = new WaitUtils(driver);
	}

	@FindBy(xpath = "//*[@id=\"user-name\"]")
	WebElement username;

	@FindBy(xpath = "//*[@id=\"password\"]")
	WebElement password;

	@FindBy(xpath = "//input[@value=\"LOGIN\"]")
	WebElement login;

	@FindBy(xpath = "//h3[@data-test=\"error\"]")
	WebElement errorMsg;

	@FindBy(xpath = "//button[text()=\"ADD TO CART\"]")
	WebElement addToCartBtns;

	@FindBy(xpath = ".//div[@id='shopping_cart_container']/a/span")
	WebElement cartCounter;

	@FindBy(xpath = ".//div[@id='inventory_filter_container']/div")
	WebElement productsLabel;

	List<WebElement> addToCartBtnsList;

	public void login(String val) {
		String uname = "";
		String pass = "";

		switch (val) {
		case ("standard"):
			uname = "standard_user";
			pass = "secret_sauce";
			break;
		case ("problem"):
			uname = "problem_user";
			pass = "secret_sauce";
			break;
		case ("locked_out"):
			uname = "locked_out_user";
			pass = "secret_sauce";
			break;
		case ("performance_glitch_user"):
			uname = "performance_glitch_user";
			pass = "secret_sauce";
			break;
		case ("invalid"):
			uname = "test@gmail.com";
			pass = "test@123";
			break;

		default:
			System.out.println("not a valid input ");
		}
		try {
			username.clear();
			username.sendKeys(uname);
			password.clear();
			password.sendKeys(pass);
			login.click();
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
	}

	public Boolean verifyTitle() {
		System.out.println(driver.getTitle());
		return driver.getTitle().contains("Swag Labs");
	}

	public Boolean errorMsgExists() {

		try {
			return waitUtils.getElementAfterMediumWait(errorMsg).isDisplayed();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public Boolean userLockedOut() {
		String te = errorMsg.getText();
		return waitUtils.getElementAfterShortWait(errorMsg).getText().contains("Sorry, this user has been locked out.");
	}

	public boolean userOnInventoryPage() {
		return driver.getCurrentUrl().contains("inventory");
	}

	public void addItemsToCart() {
		addToCartBtnsList = driver.findElements(By.xpath("//button[text()=\"ADD TO CART\"]"));
		for (WebElement btn : addToCartBtnsList) {
			waitUtils.getElementAfterShortWait(btn).click();
		}
	}

	public boolean validateCartCount() {
		int cartSize = Integer.parseInt(cartCounter.getText());
		return addToCartBtnsList.size() == cartSize;
	}

	public boolean addeditemsNotInCart() {
		int cartSize = Integer.parseInt(cartCounter.getText());
		return cartSize < addToCartBtnsList.size();
	}

	public long elementsNotVisibleAFterQuickLoad() {
		waitUtils.getElementAfterLongWait(productsLabel).isDisplayed();
		return System.currentTimeMillis();
	}

	@Override
	protected void load() {
		driver.get("https://www.saucedemo.com/index.html");
	}

	@Override
	protected void isLoaded() throws Error {
		if (driver.getCurrentUrl().contains("https://www.saucedemo.com/index.html")) {
			System.out.println("login page loaded");
		} else {
			System.out.println("not loaded");
		}
	}
}
