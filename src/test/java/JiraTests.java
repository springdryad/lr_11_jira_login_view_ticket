import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;

public class JiraTests {
  WebDriver driver = null;

  @BeforeTest()
  public void setUp() {
    // Open the browser
    WebDriverFactory.createInstance("Chrome");
    driver = WebDriverFactory.getDriver();
  }

  @Test
  public void successfulLoginTest() {
    driver.get("https://jira.hillel.it/secure/Dashboard.jspa");
    driver.findElement(By.id("login-form-username")).sendKeys("webinar5");
    driver.findElement(By.id("login-form-password")).sendKeys("webinar5");
    driver.findElement(By.id("login")).click();
    // wait
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    assert (driver.findElement(By.id("header-details-user-fullname")).isDisplayed());
  }

  @Test
  public void ViewJIRATicket() {
    driver.get("https://jira.hillel.it/secure/Dashboard.jspa");
    driver.findElement(By.id("login-form-username")).sendKeys("webinar5");
    driver.findElement(By.id("login-form-password")).sendKeys("webinar5");
    driver.findElement(By.id("login")).click();
    // wait
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    driver.findElement(By.id("quickSearchInput")).sendKeys("WEBINAR-9060", Keys.ENTER);
    //find issue type - Bug
    assert (driver.findElement(By.id("type-val")).getText().contains("Bug"));
    //verify that element contains issue ID
    assert (driver.getCurrentUrl().contains("WEBINAR-9060"));
  }


  @AfterTest()
  public void tearDown() {
    // Close the browser
    WebDriverFactory.getDriver().quit();
  }
}
