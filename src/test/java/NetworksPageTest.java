import com.humanity.pages.NetworksPage;
import com.humanity.pages.SignInPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;


public class NetworksPageTest {
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
    public void createWorkplace() {
        NetworksPage networks = new NetworksPage(driver);

        networks.navigateToWorkplacePage();

        int counter = 22;

        networks.clickOnAddWorkplaceButton();
        networks.clickOnCreateNewTab();
        networks.inputCompanyName("Ime");
        networks.inputCompanyAddress("Adresa, Beograd, Serbia");
        networks.clickOnCreateWorkplaceButton();

        //If element is present go to While loop (Workplace created)
        boolean created = networks.workplaceCreated();

        while (!created) {
            counter++;
            String sCounter = Integer.toString(counter);

            //Repeat workplace creation with added counter at the end for Name and Address
            networks.inputCompanyName("Ime" + sCounter);
            networks.inputCompanyAddress("Adresa" + sCounter + " , Beograd, Serbia");
            networks.clickOnCreateWorkplaceButton();

            //Check if element is present, if yes break out of loop
            boolean created2 = networks.workplaceCreated();
            if (created2) break;
        }
    }

    @Test
    public void createWorkplaceNoNameError(){
        NetworksPage networks = new NetworksPage(driver);

        networks.navigateToWorkplacePage();

        networks.clickOnAddWorkplaceButton();
        networks.clickOnCreateNewTab();
        networks.clickOnCreateWorkplaceButton();

        String notificationText = networks.notificationText();
        Assert.assertEquals("Please enter name.", notificationText);
    }
}

