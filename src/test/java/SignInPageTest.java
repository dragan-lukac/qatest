import com.humanity.pages.SignInPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;


public class SignInPageTest {
    private WebDriver driver;

    //Before Each test
    @Before
    public void setup(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        SignInPage signin = new SignInPage(driver);
    }

    //After Each test
    @After
    public void teardown(){
        driver.close();
    }

    @Test
    public void signInSuccess(){
        SignInPage signin = new SignInPage(driver);

        //Sign in with the credentials supplied as arguments for the signIn() method
        signin.signIn("dragan.lukac@gmail.com", "SNQnpknQP8rEiUYx6xkZ");

        //Wait for Dashboard element to be displayed
        signin.waitForDashboardElement();

        //Verify the URL of the page
        Assert.assertEquals("https://app.humanity.com/#!dashboard/gettingstarted", driver.getCurrentUrl());
    }
    @Test
    public void signInWrongPassword(){
        SignInPage signin = new SignInPage(driver);

        //Sign in with the credentials supplied as arguments for the signIn() method
        signin.signIn("dragan.lukac@gmail.com", "Wrong credentials");

        //Get the first message displayed
        //Verify the first message displayed
        signin.firstMessageTextAssert();

        //Wait for the second message to load
        signin.waitForTheSecondMessageToLoad("Invalid login credentials supplied.");
        //Get the second message displayed
        String secondMessage = signin.secondMessageIvalidCredentials();
        //Verify the second message displayed
        Assert.assertEquals("Invalid login credentials supplied.", secondMessage);
    }
    @Test
    public void signInNoEmail(){
        SignInPage signin = new SignInPage(driver);

        //Sign in with the credentials supplied as arguments for the signIn() method
        signin.signIn("", "SNQnpknQP8rEiUYx6xkZ");

        //Get the first message displayed
        //Verify the first message displayed
        signin.firstMessageTextAssert();

        //Wait for the second message to load
        signin.waitForTheSecondMessageToLoad("E-mail or username is required");
        //Get the second message displayed
        String secondMessage = signin.secondMessageEmailOrUserRequired();
        //Verify the second message displayed
        Assert.assertEquals("E-mail or username is required", secondMessage);
    }
    @Test
    public void signInNoPassword(){
        SignInPage signin = new SignInPage(driver);

        //Sign in with the credentials supplied as arguments for the signIn() method
        signin.signIn("dragan.lukac@gmail.com", "");

        //Get the first message displayed
        signin.firstMessageTextAssert();

        //Wait for the second message to load
        signin.waitForTheSecondMessageToLoad("Password is required");
        //Get the second message displayed
        String secondMessage = signin.secondMessageEmailOrUserRequired();
        //Verify the second message displayed
        Assert.assertEquals("Password is required", secondMessage);
    }
    @Test
    public void signInNoEmailAndPassword(){
        SignInPage signin = new SignInPage(driver);

        //Sign in with the credentials supplied as arguments for the signIn() method
        signin.signIn("", "");

        //Get the first message displayed
        //Verify the first message displayed
        signin.firstMessageTextAssert();

        //Get the second message displayed
        signin.waitForTheSecondMessageToLoad("E-mail or username is required\" + \"\\n\" + \"Password is required");
        //Get the second message displayed - there are two lines so needs concatenation
        String secondMessage = signin.secondMessageEmailAndPasswordRequired();
        //Verify the second message displayed
        Assert.assertEquals("E-mail or username is required" + "\n" + "Password is required", secondMessage);
    }
}
