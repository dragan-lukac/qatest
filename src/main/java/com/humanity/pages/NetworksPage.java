package com.humanity.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NetworksPage {
    private WebDriver driver;

    //Page URL
    private static String PAGE_URL="https://account.humanity.com/#!networks";

    //Constructor
    public NetworksPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


//######### Locators ########################

    //Company name Field
    @FindBy(css = "#company-name")
    private WebElement companyNameField;

    //Company address Field
    @FindBy(css = "#company-address")
    private WebElement companyAddressField;

    //CREATE WORKPLACE button
    @FindBy(css = "#create-company")
    private WebElement createWorkplaceButton;

//######### Methods ########################
    //Navigate to Workplace page
    public void navigateToWorkplacePage(){
        driver.get(PAGE_URL);
    }

    //Click on Add workplace button
    public void clickOnAddWorkplaceButton(){
        WebElement element = driver.findElement(By.className("btn"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    //Click on Create New Tab
    public void clickOnCreateNewTab(){
        WebElement element = driver.findElement(By.id("create-new-tab"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    //Input Company Name
    public void inputCompanyName(String companyName){
        companyNameField.clear();
        companyNameField.sendKeys(companyName);
    }

    //Input Company Address
    public void inputCompanyAddress(String companyAddress){
        companyAddressField.clear();
        companyAddressField.sendKeys(companyAddress);
    }

    //Click on Create Workplace button
    public void clickOnCreateWorkplaceButton(){
        createWorkplaceButton.click();
    }

    //Return False if there is no Domain created today message
    public boolean workplaceCreated() {
        boolean created = driver.findElements(By.cssSelector
                ("#details > div.overRight > div.ntNote > p > span > b")).size() != 0;
        if (created){
            return true;
        }
        else{
            return false;
        }
    }

    //Return notification text
    public String notificationText() {
        WebElement notification = driver.findElement(By.xpath
                ("//*[@id=\"notifications\"]/div[@class=\"notification\"]"));
        String notificationText = notification.getText();
        return notificationText;
    }

}
