package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class InicioSesionIT {
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
  public void testInicioSesion() throws Exception {
    //30 caracteres en el campo login
    driver.get("http://localhost:4200/login");
    driver.findElement(By.id("login")).click();
    driver.findElement(By.id("login")).clear();
    driver.findElement(By.id("login")).sendKeys("exactamente 30 caracteres 1234");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("admin");
    driver.findElement(By.name("btnEnviar")).click();
    
    //mas de 30 caracteres campo login
    driver.findElement(By.id("login")).click();
    driver.findElement(By.id("login")).clear();
    driver.findElement(By.id("login")).sendKeys("mas de 30 caracteres definitivo");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("admin");
    driver.findElement(By.name("btnEnviar")).click();
    
    //campo login vacio
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("admin");
    
    //campo password vacio
    driver.findElement(By.id("login")).click();
    driver.findElement(By.id("login")).clear();
    driver.findElement(By.id("login")).sendKeys("administrador");
    
    //30 caracteres campo password
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("exactamente 30 caracteres123455");
    driver.findElement(By.id("login")).click();
    driver.findElement(By.id("login")).clear();
    driver.findElement(By.id("login")).sendKeys("administrador");
    driver.findElement(By.name("btnEnviar")).click();
    
    //mas de 30 caracteres campo password
    driver.findElement(By.id("login")).click();
    driver.findElement(By.id("login")).clear();
    driver.findElement(By.id("login")).sendKeys("administrador");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("mas de 30 caracteres deifnitivo");
    driver.findElement(By.name("btnEnviar")).click();
    
    //Credenciales correctas
    driver.findElement(By.id("login")).click();
    driver.findElement(By.id("login")).clear();
    driver.findElement(By.id("login")).sendKeys("administrador");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("administrador");
    driver.findElement(By.name("btnEnviar")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
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
