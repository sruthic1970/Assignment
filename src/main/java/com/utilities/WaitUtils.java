package com.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;


public class WaitUtils {

    WebDriver driver;

   public WaitUtils(WebDriver driver)
    {
        this.driver = driver;
    }

    private static final long SHORT_TIMEOUT = 5;
    private static final long LONG_TIMEOUT = 25;
    private static final long MEDIUM_TIMEOUT = 10;

    public WebElement getElementAfterShortWait(WebElement ele)
    {
        return getElementAfterWait(ele,SHORT_TIMEOUT);
    }

    public WebElement getElementAfterLongWait(WebElement ele)
    {
        return getElementAfterWait(ele,LONG_TIMEOUT);
    }

    public WebElement getElementAfterMediumWait(WebElement ele)
    {
        return getElementAfterWait(ele,MEDIUM_TIMEOUT);
    }

    private WebElement getElementAfterWait(WebElement ele , long timeout)
    {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            return  wait.until(ExpectedConditions.visibilityOf(ele));
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public WebElement waitForElementToEnabled(WebElement ele)
    {
        try {
            WebDriverWait wait = new WebDriverWait(driver, MEDIUM_TIMEOUT);
            return wait.until(ExpectedConditions.elementToBeClickable(ele));
        }catch(TimeoutException e)
        {
            e.printStackTrace();
            return  null;
        }

    }

    public void waitForElementToDisappear(WebElement ele)
    {

            WebDriverWait wait = new WebDriverWait(driver, MEDIUM_TIMEOUT);
            wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    public void captureScreenshots(String res){
        try {
            File resourcesDirectory = new File("src/test/resources");
            File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File(resourcesDirectory.getAbsolutePath()+"/screenshots/"+res+System.currentTimeMillis()+"_test.png"));
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
	