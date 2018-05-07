package Test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class RealizarReservaIT {
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
  public void testReservar() throws Exception {
    //ReservaFija completa
    driver.get("http://localhost:4200/horariofijo");
    driver.findElement(By.id("exampleFormControlSelect1")).click();
    new Select(driver.findElement(By.id("exampleFormControlSelect1"))).selectByVisibleText("FUTBOL SALA");
    driver.findElement(By.id("exampleFormControlSelect1")).click();
    driver.findElement(By.id("selectEscenarioDe")).click();
    new Select(driver.findElement(By.id("selectEscenarioDe"))).selectByVisibleText("Sintetica 1");
    driver.findElement(By.id("selectEscenarioDe")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.id("inlineRadio1")).click();
    driver.findElement(By.id("nombre-text-input")).click();
    driver.findElement(By.id("nombre-text-input")).clear();
    driver.findElement(By.id("nombre-text-input")).sendKeys("Camilo Tomar");
    driver.findElement(By.id("descripcion-text-input")).clear();
    driver.findElement(By.id("descripcion-text-input")).sendKeys("Reservar para jugar con mis compañeros");
    driver.findElement(By.id("tipo")).click();
    new Select(driver.findElement(By.id("tipo"))).selectByVisibleText("Normal");
    driver.findElement(By.id("tipo")).click();
    driver.findElement(By.xpath("(//input[@type='text'])[3]")).click();
    driver.findElement(By.xpath("(//input[@type='text'])[3]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("02");
    driver.findElement(By.xpath("(//input[@type='text'])[4]")).click();
    driver.findElement(By.xpath("(//input[@type='text'])[4]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys("00");
    driver.findElement(By.xpath("(//input[@type='text'])[5]")).click();
    driver.findElement(By.xpath("(//input[@type='text'])[5]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[5]")).sendKeys("04");
    driver.findElement(By.xpath("(//input[@type='text'])[6]")).click();
    driver.findElement(By.xpath("(//input[@type='text'])[6]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[6]")).sendKeys("00");
    driver.findElement(By.name("btnEnviar")).click();
    driver.findElement(By.xpath("//div[3]/div/div[3]")).click();
   
    //ReservaFija Misma hora inicio y fin
    driver.get("http://localhost:4200/horariofijo");
    driver.findElement(By.xpath("//h2")).click();
    driver.findElement(By.id("exampleFormControlSelect1")).click();
    new Select(driver.findElement(By.id("exampleFormControlSelect1"))).selectByVisibleText("FUTBOL SALA");
    driver.findElement(By.id("exampleFormControlSelect1")).click();
    driver.findElement(By.id("selectEscenarioDe")).click();
    new Select(driver.findElement(By.id("selectEscenarioDe"))).selectByVisibleText("Sintetica 1");
    driver.findElement(By.id("selectEscenarioDe")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.id("inlineRadio1")).click();
    driver.findElement(By.id("nombre-text-input")).click();
    driver.findElement(By.id("nombre-text-input")).clear();
    driver.findElement(By.id("nombre-text-input")).sendKeys("luis Tomar");
    driver.findElement(By.id("descripcion-text-input")).click();
    driver.findElement(By.id("descripcion-text-input")).clear();
    driver.findElement(By.id("descripcion-text-input")).sendKeys("entrenamiento");
    driver.findElement(By.id("tipo")).click();
    new Select(driver.findElement(By.id("tipo"))).selectByVisibleText("Academico");
    driver.findElement(By.id("tipo")).click();
    driver.findElement(By.xpath("(//input[@type='text'])[3]")).click();
    driver.findElement(By.xpath("(//input[@type='text'])[5]")).click();
    driver.findElement(By.name("btnEnviar")).click();
    
    //Solamente Nombre
    driver.get("http://localhost:4200/horariofijo");
    driver.findElement(By.id("exampleFormControlSelect1")).click();
    new Select(driver.findElement(By.id("exampleFormControlSelect1"))).selectByVisibleText("FUTBOL SALA");
    driver.findElement(By.id("exampleFormControlSelect1")).click();
    driver.findElement(By.id("selectEscenarioDe")).click();
    new Select(driver.findElement(By.id("selectEscenarioDe"))).selectByVisibleText("Sintetica 4");
    driver.findElement(By.id("selectEscenarioDe")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.id("inlineRadio1")).click();
    driver.findElement(By.id("nombre-text-input")).click();
    driver.findElement(By.id("nombre-text-input")).clear();
    driver.findElement(By.id("nombre-text-input")).sendKeys("Luis Tomar");
    driver.findElement(By.xpath("(//input[@type='text'])[5]")).click();
    driver.findElement(By.xpath("(//input[@type='text'])[5]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[5]")).sendKeys("08");
    driver.findElement(By.name("btnEnviar")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    
    //Solamente Descripcion
    driver.get("http://localhost:4200/horariofijo");
    driver.findElement(By.id("nombre-text-input")).clear();
    driver.findElement(By.id("nombre-text-input")).sendKeys("");
    driver.findElement(By.id("descripcion-text-input")).click();
    driver.findElement(By.id("descripcion-text-input")).clear();
    driver.findElement(By.id("descripcion-text-input")).sendKeys("Entrenamiento");
    driver.findElement(By.xpath("(//input[@type='text'])[3]")).click();
    driver.findElement(By.xpath("(//input[@type='text'])[3]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("08");
    driver.findElement(By.xpath("(//input[@type='text'])[5]")).click();
    driver.findElement(By.xpath("(//input[@type='text'])[5]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[5]")).sendKeys("10");
    driver.findElement(By.name("btnEnviar")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    
    //Solamente Tipo
    driver.get("http://localhost:4200/horariofijo");
    driver.findElement(By.id("tipo")).click();
    new Select(driver.findElement(By.id("tipo"))).selectByVisibleText("Normal");
    driver.findElement(By.id("tipo")).click();
    driver.findElement(By.xpath("(//input[@type='text'])[3]")).click();
    driver.findElement(By.xpath("(//input[@type='text'])[3]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("04");
    driver.findElement(By.xpath("(//input[@type='text'])[5]")).click();
    driver.findElement(By.name("btnEnviar")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    
   
    //ReservaDiaria completa
    driver.get("http://localhost:4200/horariofijo");
    driver.findElement(By.id("exampleFormControlSelect1")).click();
    new Select(driver.findElement(By.id("exampleFormControlSelect1"))).selectByVisibleText("FUTBOL SALA");
    driver.findElement(By.id("exampleFormControlSelect1")).click();
    driver.findElement(By.id("selectEscenarioDe")).click();
    new Select(driver.findElement(By.id("selectEscenarioDe"))).selectByVisibleText("Cancha700");
    driver.findElement(By.id("selectEscenarioDe")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.id("inlineRadio2")).click();
    driver.findElement(By.id("nombre-text-input")).click();
    driver.findElement(By.id("nombre-text-input")).clear();
    driver.findElement(By.id("nombre-text-input")).sendKeys("luis Tomar");
    driver.findElement(By.id("descripcion-text-input")).click();
    driver.findElement(By.id("descripcion-text-input")).clear();
    driver.findElement(By.id("descripcion-text-input")).sendKeys("entrenamiento");
    driver.findElement(By.id("tipo")).click();
    new Select(driver.findElement(By.id("tipo"))).selectByVisibleText("Normal");
    driver.findElement(By.id("tipo")).click();
    driver.findElement(By.name("btnEnviar")).click();
    
    //Solamente Nombre
    driver.get("http://localhost:4200/horariofijo");
    driver.findElement(By.id("exampleFormControlSelect1")).click();
    new Select(driver.findElement(By.id("exampleFormControlSelect1"))).selectByVisibleText("FUTBOL SALA");
    driver.findElement(By.id("exampleFormControlSelect1")).click();
    driver.findElement(By.id("selectEscenarioDe")).click();
    new Select(driver.findElement(By.id("selectEscenarioDe"))).selectByVisibleText("Sintetica3");
    driver.findElement(By.id("selectEscenarioDe")).click();
    new Select(driver.findElement(By.id("selectEscenarioDe"))).selectByVisibleText("Sintetica 3");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.id("inlineRadio2")).click();
    driver.findElement(By.id("nombre-text-input")).click();
    driver.findElement(By.id("nombre-text-input")).clear();
    driver.findElement(By.id("nombre-text-input")).sendKeys("luis");
    driver.findElement(By.name("btnEnviar")).click();
    
    //Solamente Descripcion
    driver.get("http://localhost:4200/horariofijo");
    driver.findElement(By.id("exampleFormControlSelect1")).click();
    new Select(driver.findElement(By.id("exampleFormControlSelect1"))).selectByVisibleText("FUTBOL SALA");
    driver.findElement(By.id("exampleFormControlSelect1")).click();
    driver.findElement(By.xpath("//h2")).click();
    driver.findElement(By.id("selectEscenarioDe")).click();
    new Select(driver.findElement(By.id("selectEscenarioDe"))).selectByVisibleText("Sintetica3");
    driver.findElement(By.id("selectEscenarioDe")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.id("inlineRadio2")).click();
    driver.findElement(By.id("descripcion-text-input")).click();
    driver.findElement(By.id("descripcion-text-input")).clear();
    driver.findElement(By.id("descripcion-text-input")).sendKeys("entrenamiento");
    driver.findElement(By.name("btnEnviar")).click();
    
    //Solamente Tipo
    driver.get("http://localhost:4200/horariofijo");
    driver.findElement(By.id("exampleFormControlSelect1")).click();
    new Select(driver.findElement(By.id("exampleFormControlSelect1"))).selectByVisibleText("FUTBOL SALA");
    driver.findElement(By.id("exampleFormControlSelect1")).click();
    driver.findElement(By.xpath("//h2")).click();
    driver.findElement(By.id("selectEscenarioDe")).click();
    new Select(driver.findElement(By.id("selectEscenarioDe"))).selectByVisibleText("Sintetica3");
    driver.findElement(By.id("selectEscenarioDe")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.id("inlineRadio2")).click();
    driver.findElement(By.id("tipo")).click();
    new Select(driver.findElement(By.id("tipo"))).selectByVisibleText("Normal");
    driver.findElement(By.id("tipo")).click();
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
