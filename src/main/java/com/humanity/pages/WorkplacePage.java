package com.humanity.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WorkplacePage {
    private WebDriver driver;


    //Constructor
    public WorkplacePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


//######### Locators ########################

    //Workplace button
    @FindBy(xpath = "//*[@id=\"menu\"]/ul/li[2]")
    private WebElement workplaceButton;

    //Add Employee button
    @FindBy(xpath = "//*[@id=\"content\"]/div/div[1]/div[2]/div[1]/div[1]/div/button[1]")
    private WebElement addEmployeeButton;

    //First Name field
    @FindBy(css = "#input-0")
    private WebElement firstNameField;

    //Last Name field
    @FindBy(css = "#input-1")
    private WebElement lastNameField;

    //E-mail field
    @FindBy(css = "#input-2")
    private WebElement emailField;

    //Add Employee button2
    @FindBy(css = "body > div.show.panel.slide.has-header.has-footer > div.footer.border-top > div:nth-child(1) > " +
            "div:nth-child(1) > div > button.btn.fr.btn-green > span")
    private WebElement addEmployeeButton2;

    //Add & Invite Employee button
    @FindBy(css = "div.footer:nth-child(2) > div:nth-child(1)")
    private WebElement addAndInviteEmployeeButton;

    //Warning message close button
    @FindBy(css = "#close-notification > i")
    private WebElement warningMessageCloseButton;

    //######### Methods ########################
    //Click on Workplace button
    public void clickOnWorkplaceButton(){
        workplaceButton.click();
    }


    //Click on Add Employee button
    public void clickOnAddEmployeeButton(){
        addEmployeeButton.click();
    }

    //Input First Name
    public void inputFirstName(String firstName){
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    //Input Last Name
    public void inputLastName(String lastName){
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    //Input Email
    public void inputEmail(String email){
        emailField.clear();
        emailField.sendKeys(email);
    }

    //Click on Add Employee button2
    public void clickOnAddEmployeeButton2(){
        addEmployeeButton2.click();
    }

    //Click on Add & Invite Employee button
    public void clickOnAddAndInviteEmployeeButton(){
        addAndInviteEmployeeButton.click();
    }

    //Create an Employee
    public void createEmployee(String firstName, String lastName, String email){
        inputFirstName(firstName);
        inputLastName(lastName);
        inputEmail(email);
    }

    //Close warning message
    public void closeWarningMessage(){
        warningMessageCloseButton.click();
    }

    //Return true if error appears
    public boolean error() {
        boolean error = driver.findElements(By.cssSelector
                ("body > div.notification > p")).size() != 0;
        if (error){
            return true;
        }
        else{
            return false;
        }
    }

    //Return sucessText
    public String sucess(){
        WebElement sucess = driver.findElement(By.cssSelector("#notificationMessage"));
        String sucessText = sucess.getText();
        return sucessText;
    }

    //Return notification text
    public String notificationText(){
        WebElement notification = driver.findElement
                (By.cssSelector("body > div.notification > p"));
        String notificationText = notification.getText();
        return notificationText;
    }

    //Return first name tooltip
    public String firstNameTooltip(){
        //Get the First Name Tooltip displayed
        WebElement getFirstTooltip = driver.findElement
                ( By.cssSelector("body > div:nth-child(14) > div.tooltip-inner"));
        String firstNameTooltip = getFirstTooltip.getText();
        return firstNameTooltip;
    }

    public String lastNameTooltip(){
        //Get the First Name Tooltip displayed
        WebElement getlastTooltip = driver.findElement
                ( By.cssSelector("body > div:nth-child(15) > div.tooltip-inner"));
        String lastNameTooltip = getlastTooltip.getText();
        return lastNameTooltip;
    }

    public String invalidEmailTooltip() {
        WebElement getInvalidEmailTooltip = driver.findElement
                (By.cssSelector("body > div:nth-child(14) > div.tooltip-inner"));
        String invalidEmailTooltip = getInvalidEmailTooltip.getText();
        return invalidEmailTooltip;
    }

    public void waitForSidebar(){
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector("body > div.show.panel.slide.has-header.has-footer > div.footer.border-top > " +
                        "div:nth-child(1) > div:nth-child(1) > div > button.btn.fr.btn-green > span")));
    }

    public boolean waitForSucessMessage(){
        boolean created;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.textToBePresentInElementLocated
                    (By.cssSelector("#notificationMessage"), "Employee successfully created"));
            //If there is a message and the text is "Employee successfully created" break out of the loop
            String sucessText = sucess();

            return (sucessText.equals("Employee successfully created"));

        }
        catch (Exception e){
            return false;
        }
    }
}
