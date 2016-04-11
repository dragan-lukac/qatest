package com.humanity.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
    private WebDriver driver;

    //Page URL
    private static String PAGE_URL="https://accounts.humanity.com/signin";

    //Constructor
    public SignInPage(WebDriver driver) {
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    //######### Locators ########################
    //Email Field
    @FindBy(css = "#login-email")
    private WebElement emailField;

    //Password Field
    @FindBy(css = "#login-password")
    private WebElement passwordField;

    //Sign in Button
    @FindBy(css = "body > div.page-wrap > div.container > form > div.bFooter > input")
    private WebElement signInButton;

    //######### Methods ########################
    //Input Email
    public void inputEmail(String email){
        emailField.clear();
        emailField.sendKeys(email);
    }

    //Input Password
    public void inputPassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    //Click Sign in button
    public void clickOnSignIn(){
        signInButton.click();
    }

    public void signIn(String Email, String Password) {
        inputEmail(Email);
        inputPassword(Password);
        clickOnSignIn();
    }

    public void firstMessageTextAssert(){
        WebElement firstMessage = driver.findElement( By.xpath("//*[@id='status']/li"));
        String firstMessageText = firstMessage.getText();
        Assert.assertEquals("Processing...", firstMessageText);
    }

    public String secondMessageEmailOrUserRequired(){
        WebElement secondMessage = driver.findElement( By.xpath("//*[@id='status']/li"));
        String secondMessageText = secondMessage.getText();
        return secondMessageText;
    }

    public String secondMessageIvalidCredentials(){
        WebElement secondMessage = driver.findElement( By.xpath("//*[@id='status']/li"));
        String secondMessageText = secondMessage.getText();
        return secondMessageText;
    }

    public String secondMessagePasswordRequired(){
        WebElement secondMessage = driver.findElement( By.xpath("//*[@id='status']/li"));
        String secondMessageText = secondMessage.getText();
        return secondMessageText;
    }

    public String secondMessageEmailAndPasswordRequired(){
        WebElement getSecondMessagePart1 = driver.findElement( By.xpath("//*[@id='status']/li[1]"));
        WebElement getSecondMessagePart2 = driver.findElement( By.xpath("//*[@id='status']/li[2]"));
        String secondMessageText = getSecondMessagePart1.getText() + "\n" + getSecondMessagePart2.getText();
        return secondMessageText;
    }

    public void waitForDashboardElement(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
                wait.until(ExpectedConditions.presenceOfElementLocated
                        (By.cssSelector("#content > div > div.header > div.title > h1")));
    }

    public void waitForTheSecondMessageToLoad(String secondMessage){
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.textToBePresentInElementLocated
                    (By.xpath("//*[@id='status']/li"), secondMessage));
        }
        catch (Exception e){
        }
    }


}

