package Test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class EditarEscenarioIT {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void EditarEscenarioIT() throws Exception {
    driver.get("http://localhost:4200/espaciodeportivos");
    driver.findElement(By.xpath("//table[@id='tblEspaciosdeportivos']/tbody/tr/td[3]/a[2]/i")).click();
    driver.findElement(By.id("nombre")).click();
    driver.findElement(By.id("nombre")).clear();
    driver.findElement(By.id("nombre")).sendKeys("Sintetica 100");
    driver.findElement(By.id("estado")).click();
    new Select(driver.findElement(By.id("estado"))).selectByVisibleText("Mantenimiento");
    driver.findElement(By.id("estado")).click();
    acceptNextAlert = true;
    driver.findElement(By.name("btnEnviar")).click();
    assertTrue(closeAlertAndGetItsText().matches("^¿ DESEA ACTUALIZAR ESTE ESPACIO DEPORTIVO [\\s\\S]$"));
  }

  @After
  public void tearDown() throws Exception {
    //driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
