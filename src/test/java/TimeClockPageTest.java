import com.humanity.pages.SignInPage;
import com.humanity.pages.TimeClockPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class TimeClockPageTest {
    private WebDriver driver;

    //Before Each test
    @Before
    public void setup(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        SignInPage signin = new SignInPage(driver);
        signin.signIn("dragan.lukac@gmail.com", "SNQnpknQP8rEiUYx6xkZ");
        TimeClockPage timeclock = new TimeClockPage(driver);
        timeclock.clickOnTimeClockButton();
    }
    //After Each test
    @After
    public void teardown(){
        driver.close();
    }
    @Test
    public void clockInWithPrintMessageIfClockedIn(){
        TimeClockPage timeclock = new TimeClockPage(driver);

        timeclock.WaitForButtonloadAndClickIt();
        timeclock.WaitForButtonloadAndVerify();
    }
}
