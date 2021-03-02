package com.pages;

//import com.pages.basePage.BasePage;
//import com.utilities.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.base.BaseClass;
import com.utilities.WaitUtils;

//LoadableComponent<HomePage>
public class HomePage extends BaseClass{

    @FindBy(xpath = "//*[@id=\"inventory_filter_container\"]")
    WebElement productsLabel;
    WaitUtils waitUtils;

   private WebDriver driver;

   public HomePage(WebDriver driver)
    {
        
        this.driver = driver;
        waitUtils = new WaitUtils(driver);
    }


    public Boolean verifyTitle(){

        System.out.println(driver.getTitle());
        return driver.getTitle().contains("Swag Labs");
    }

    public Boolean iAmOnInventoryPage(){
       return waitUtils.getElementAfterMediumWait(productsLabel).isDisplayed();
    }


    @Override
    protected void load() {
        driver.get("https://www.saucedemo.com/index.html");
    }

    @Override
    protected void isLoaded() throws Error {
        try{
            if(driver.getCurrentUrl().contains("https://www.saucedemo.com/index.html"))
            {
                System.out.println("home page loaded");
            }
        }catch (Exception e)
        {
                System.out.println("home page not loaded");
        }

   }

}
