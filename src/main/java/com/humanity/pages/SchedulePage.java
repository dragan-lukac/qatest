package com.humanity.pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SchedulePage {
    private WebDriver driver;

    //Constructor
    public SchedulePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //######### Locators ########################
    //Schedule Button
    @FindBy(css = "#menu > ul > li.jsMenuschedule\\2f employees")
    private WebElement scheduleButton;

    //Shift Button
    @FindBy(css = "#\\33 07565846505455618 > div.days.textCenter.jsCellContainer > div.date.compact-view.day0")
    private WebElement shiftButton;

    //Time Field
    @FindBy(css = "#time-input")
    private WebElement timeField;

    //Position input dropdown
    @FindBy(css = "#position-input")
    private WebElement positionDropdown;

    //Create Shift Button
    @FindBy(xpath = "/html/body/div[7]/div/div/div/div[3]/div/div[2]/button[1]")
    private WebElement createShiftButton;


    //######### Methods ########################
    //Click on Schedule Button
    public void sclickOnScheduleButton(){
        scheduleButton.click();
    }

    //Click on Shift Button
    public void clickOnShiftButton(){
        WebElement element = driver.findElement(By.cssSelector("#\\33 07565846505455618 > div.days.textCenter.jsCellContainer > div.date.compact-view.day0"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    //Input Time interval
    public void inputTime(String time){
        timeField.clear();
        timeField.sendKeys(time);
    }

    //Input Position
    public void inputPosition(){
        positionDropdown.click();
        positionDropdown.sendKeys(Keys.ARROW_DOWN);
        positionDropdown.sendKeys(Keys.ENTER);
    }

    //Click on Create Shift Button
    public void clickOnCreateShift(){
        createShiftButton.click();
    }

    //Create Shift
    public void createShift(String time){
        clickOnShiftButton();
        inputTime(time);
        inputPosition();
        clickOnCreateShift();
    }

    //Return Sjift Created notification text
    public String shiftCreatedNotificationText(){
        WebElement notification = driver.findElement(By.cssSelector("body > div.notification > p"));
        String notificationText = notification.getText();
        return notificationText;
    }

    //Return You have to enter a valid time interval notification text
    public String invalidTimeTooltipText(){
        WebElement tooltip = driver.findElement(By.cssSelector("body > div.tooltip.right.in > div.tooltip-inner"));
        String tooltipTimeText = tooltip.getText();
        return tooltipTimeText;
    }

    //Return You have to enter a valid time interval notification text
    public String noPositionTooltipText(){
        WebElement tooltipPosition = driver.findElement(By.cssSelector
                ("body > div.tooltip.right.in > div.tooltip-inner"));
        String tooltipPositionText = tooltipPosition.getText();
        return tooltipPositionText;
    }
}
