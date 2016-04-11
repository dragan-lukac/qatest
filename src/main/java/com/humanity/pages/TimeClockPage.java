package com.humanity.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;


public class TimeClockPage {
    private WebDriver driver;
    private WebDriverWait wait;

    //Constructor
    public TimeClockPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //######### Locators ########################
    //Time Clock Buton
    @FindBy(css = "#menu > ul > li.jsMenutimeclock\\2f index")
    WebElement timeClockButton;

    //Clock In/Out button
    @FindBy(css = "#content > div > div.header > div.controls > div:nth-child(2) > div:nth-child(1) > " +
            "div > button:nth-child(1)")
    WebElement clockInOutButton;

    //######### Methods ########################
    //Click on Time Clock
    public void clickOnTimeClockButton(){
        timeClockButton.click();
    }

    // Click on Clock In/Out Button
    public void clickOnClockInOutButton(){
        clockInOutButton.click();
    }

    public String ClockInOutButtonText(){
        WebElement buttonStatus = driver.findElement(By.cssSelector
                ("#content > div > div.header > div.controls > div:nth-child(2) > div:nth-child(1) > div > " +
                        "button:nth-child(1)"));
        String buttonStatusText = buttonStatus.getText();
        return buttonStatusText;
    }

    public void WaitForButtonloadAndClickIt(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.textToBePresentInElementLocated
                    (By.cssSelector("#content > div > div.header > div.controls > div:nth-child(2) > div:nth-child(1)" +
                            " > div > button:nth-child(1)"), "Clock In"));
            clickOnClockInOutButton();
        }
        catch (Exception e){
        }
    }

    public void WaitForButtonloadAndVerify(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.textToBePresentInElementLocated
                    (By.cssSelector("#content > div > div.header > div.controls > div:nth-child(2) > div:nth-child(1)" +
                            " > div > button:nth-child(1)"), "Clock Out"));
        }
        catch (Exception e){
        }
        String buttonStatusText = ClockInOutButtonText();
        Assert.assertEquals("Clock Out", buttonStatusText);
    }

}
