import com.humanity.pages.SchedulePage;
import com.humanity.pages.SignInPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class SchedulePageTest {
    private WebDriver driver;

    //Before Each test
    @Before
    public void setup(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        SignInPage signin = new SignInPage(driver);
        signin.signIn("dragan.lukac@gmail.com", "SNQnpknQP8rEiUYx6xkZ");
        SchedulePage schedule = new SchedulePage(driver);
        schedule.sclickOnScheduleButton();
    }
    //After Each test
    @After
    public void teardown(){
        driver.close();
    }

    @Test
    public void createShift(){
        SchedulePage schedule = new SchedulePage(driver);

        schedule.createShift("9-17");

        String notificationText = schedule.shiftCreatedNotificationText();
        Assert.assertEquals("Shift created", notificationText);

    }

    @Test
    public void createShiftNoTime(){
        SchedulePage schedule = new SchedulePage(driver);

        schedule.createShift("");

        String tooltipTimeText = schedule.invalidTimeTooltipText();

        Assert.assertEquals("You have to enter a valid time interval", tooltipTimeText);
    }

    @Test
    public void createShiftNoPosition(){
        SchedulePage schedule = new SchedulePage(driver);

        schedule.clickOnShiftButton();
        schedule.inputTime("9-17");
        schedule.clickOnCreateShift();

        String tooltipPositionText = schedule.noPositionTooltipText();
        Assert.assertEquals("You have to select a position from the list or create a new one", tooltipPositionText);
    }

}
