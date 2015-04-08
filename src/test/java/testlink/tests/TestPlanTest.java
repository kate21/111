package testlink.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testlink.model.TestPlan;
import testlink.model.User;
import testlink.pages.HomePage;
import testlink.pages.LoginPage;
import testlink.pages.TestPlanEditPage;
import testlink.pages.TestPlanManagementPage;

/**
 * Created by admin on 08.04.2015.
 */
public class TestPlanTest {
    WebDriver driver;

    @BeforeTest
    public void login (){
        loginPage loginPage = new LoginPage();
        LoginPage.login(new User());
        driver = new FirefoxDriver();
    }

    @Test
    public void positiveTest () {
        HomePage homePage = new HomePage();
        TestPlanManagementPage testPlanManagementPage = homePage.opentestPlanManagementPage();
        TestPlanEditPage editPage = TestPlanManagementPage.createTestPlan();

        TestPlan testPlan = new TestPlan();
        editPage.createTestPlan (testPlan);

        Assert.assertTrue(testPlanManagementPage.isTestPlanPresent(testPlan));
        deleteTestPlan(testPlan);
    }


    @AfterTest
    public void shutEnv(){
        deleteTestPlan();
        logout();
        if (driver !=null)
            driver.quit();
    }

    public void deleteTestPlan (TestPlan testPlan){
        TestPlanManagementPage testPlanManagementPage = new TestPlanManagementPage();
        testPlanManagementPage.deleteTestPlan(testPlan);

    }

    public void logout(){
        HomePage homePage = new HomePage();
        homePage.logout();
    }


    @AfterClass
}
