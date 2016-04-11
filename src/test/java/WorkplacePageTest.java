import com.humanity.pages.SignInPage;
import com.humanity.pages.WorkplacePage;
import org.junit.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WorkplacePageTest {
    private WebDriver driver;

    //Before Each test
    @Before
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        SignInPage signin = new SignInPage(driver);
        signin.signIn("dragan.lukac@gmail.com", "SNQnpknQP8rEiUYx6xkZ");
    }

    //After Each test
    @After
    public void teardown() {
        driver.close();
    }
    @Test
    public void createEmployee() {
        WorkplacePage workplace = new WorkplacePage(driver);

        int counter = 18;

        //Concatenate email String
        String email = "dragan.lukac+employee" + Integer.toString(counter) + "@gmail.com";

        //Create an Employee
        workplace.clickOnWorkplaceButton();
        workplace.clickOnAddEmployeeButton();
        workplace.createEmployee("Dragan", "Lukac", email);

        //Wait for sidebar
        workplace.waitForSidebar();
        workplace.clickOnAddEmployeeButton2();

        //Go to while loop of error is displayed
        boolean error = workplace.error();

        //Loop while error == true
        while (error){
            //Increment counter
            counter ++;
            //Create employee again because counter changed
            String newEmail = "dragan.lukac+employee" + Integer.toString(counter) + "@gmail.com";
            workplace.createEmployee("Dragan", "Lukac"+ Integer.toString(counter), newEmail);
            workplace.clickOnAddAndInviteEmployeeButton();
            System.out.println(newEmail);

            //Close warning message
            workplace.closeWarningMessage();

            //Wait for "Employee successfully created" message
            Boolean created = workplace.waitForSucessMessage();
            if (created){
                break;
            }
        }
    }
    @Test
    public void createEmployeeDuplicate() {
        WorkplacePage workplace = new WorkplacePage(driver);

        workplace.clickOnWorkplaceButton();
        workplace.clickOnAddEmployeeButton();
        workplace.createEmployee("Dragan", "Lukac", "dragan.lukac@gmail.com");
        workplace.clickOnAddAndInviteEmployeeButton();

        String notificationText = workplace.notificationText();

        org.junit.Assert.assertEquals
                ("Invalid contact for type 'email', at index 0: Duplicate email address: 'dragan.lukac@gmail.com'",
                        notificationText);
    }
    @Test
    public void createEmployeeNoFirstAndLastName() {
        WorkplacePage workplace = new WorkplacePage(driver);

        workplace.clickOnWorkplaceButton();
        workplace.clickOnAddEmployeeButton();

        //Wait for sidebar
        workplace.waitForSidebar();
        workplace.clickOnAddEmployeeButton2();

        //Get the FirstName Tooltip displayed
        String firstNameTooltip = workplace.firstNameTooltip();
        //Verify Text of FirstName Tooltip message
        Assert.assertEquals("This value is required.", firstNameTooltip);

        //Get the Last Name Tooltip displayed
        String lastNameTooltip = workplace.lastNameTooltip();
        //Verify Text of LastName Tooltip message
        Assert.assertEquals("This value is required.", lastNameTooltip);
    }
    @Test
    public void createEmployeeWrongEmailFormat() {
        WorkplacePage workplace = new WorkplacePage(driver);

        workplace.clickOnWorkplaceButton();
        workplace.clickOnAddEmployeeButton();

        workplace.inputFirstName("Dragan");
        workplace.inputLastName("Lukac");
        workplace.inputEmail("dragan.lukac.gmail.com");

        //Wait for sidebar
        workplace.waitForSidebar();
        workplace.clickOnAddEmployeeButton2();

        //Get the Invalid Email Tooltip displayed
        String invalidEmailTooltip = workplace.invalidEmailTooltip();
        //Verify the Invalid Email Tooltip message is displayed
        Assert.assertEquals("This value should be a valid email.", invalidEmailTooltip);
    }
}
