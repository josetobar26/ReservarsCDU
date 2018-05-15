package Test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ReservaDiariaCalendarioIT {
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
  public void testReservaDiariaCalendarioIT() throws Exception {
      //Reserva Normal
    driver.get("http://localhost:4200/horariofijo");
    driver.findElement(By.id("exampleFormControlSelect1")).click();
    new Select(driver.findElement(By.id("exampleFormControlSelect1"))).selectByVisibleText("FUTBOL SALA");
    driver.findElement(By.id("exampleFormControlSelect1")).click();
    driver.findElement(By.id("selectEscenarioDe")).click();
    new Select(driver.findElement(By.id("selectEscenarioDe"))).selectByVisibleText("Sintetica 1");
    driver.findElement(By.id("selectEscenarioDe")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.id("inlineRadio2")).click();
    driver.findElement(By.id("nombre-text-input")).click();
    driver.findElement(By.id("nombre-text-input")).clear();
    driver.findElement(By.id("nombre-text-input")).sendKeys("Sofia Vergara");
    driver.findElement(By.id("descripcion")).click();
    driver.findElement(By.id("descripcion")).clear();
    driver.findElement(By.id("descripcion")).sendKeys("Juego con mis compañeros");
    driver.findElement(By.id("tipo")).click();
    driver.findElement(By.id("tipo")).click();
    driver.findElement(By.id("tipo")).click();
    new Select(driver.findElement(By.id("tipo"))).selectByVisibleText("Normal");
    driver.findElement(By.id("tipo")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[12]")).click();
    driver.findElement(By.name("btnEnviar")).click();
    driver.findElement(By.xpath("//div[3]/div/div[3]")).click();
    
    //Mas de 20 caracteres Nombre
    driver.get("http://localhost:4200/horariofijo");
    driver.findElement(By.id("exampleFormControlSelect1")).click();
    new Select(driver.findElement(By.id("exampleFormControlSelect1"))).selectByVisibleText("FUTBOL");
    driver.findElement(By.xpath("//option[@value='1: Object']")).click();
    driver.findElement(By.id("selectEscenarioDe")).click();
    new Select(driver.findElement(By.id("selectEscenarioDe"))).selectByVisibleText("Sintetica3");
    driver.findElement(By.xpath("(//option[@value='2: Object'])[2]")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.id("inlineRadio2")).click();
    driver.findElement(By.id("nombre-text-input")).click();
    driver.findElement(By.id("nombre-text-input")).clear();
    driver.findElement(By.id("nombre-text-input")).sendKeys("snkcsdlvnsldkvlksmdvksmdvklmsdv");
    driver.findElement(By.id("descripcion")).click();
    driver.findElement(By.id("descripcion")).clear();
    driver.findElement(By.id("descripcion")).sendKeys("Entreno");
    driver.findElement(By.id("tipo")).click();
    new Select(driver.findElement(By.id("tipo"))).selectByVisibleText("Academico");
    driver.findElement(By.xpath("//option[@value='Academico']")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[12]")).click();

    //Campo Descripcion Vacio
    driver.findElement(By.id("exampleFormControlSelect1")).click();
    new Select(driver.findElement(By.id("exampleFormControlSelect1"))).selectByVisibleText("FUTBOL SALA");
    driver.findElement(By.xpath("//option[@value='0: Object']")).click();
    driver.findElement(By.id("selectEscenarioDe")).click();
    new Select(driver.findElement(By.id("selectEscenarioDe"))).selectByVisibleText("Cancha700");
    driver.findElement(By.xpath("(//option[@value='1: Object'])[2]")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.id("inlineRadio2")).click();
    driver.findElement(By.id("nombre-text-input")).click();
    driver.findElement(By.id("nombre-text-input")).clear();
    driver.findElement(By.id("nombre-text-input")).sendKeys("Julian Tomar");
    driver.findElement(By.id("tipo")).click();
    new Select(driver.findElement(By.id("tipo"))).selectByVisibleText("Academico");
    driver.findElement(By.xpath("//option[@value='Academico']")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[12]")).click();
    driver.findElement(By.name("btnEnviar")).click();
    driver.findElement(By.xpath("//div[3]/div/div[3]")).click();
    driver.findElement(By.xpath("//div/div[3]/div/div")).click();
    driver.findElement(By.xpath("//div[3]/div/mwl-calendar-month-cell[2]/div")).click();
    
    //Campo nombre vacio
     driver.findElement(By.id("exampleFormControlSelect1")).click();
    new Select(driver.findElement(By.id("exampleFormControlSelect1"))).selectByVisibleText("FUTBOL");
    driver.findElement(By.xpath("//option[@value='1: Object']")).click();
    driver.findElement(By.id("selectEscenarioDe")).click();
    new Select(driver.findElement(By.id("selectEscenarioDe"))).selectByVisibleText("cancha 1");
    driver.findElement(By.xpath("(//option[@value='3: Object'])[2]")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.id("inlineRadio2")).click();
    driver.findElement(By.id("nombre-text-input")).click();
    driver.findElement(By.id("descripcion")).click();
    driver.findElement(By.id("descripcion")).clear();
    driver.findElement(By.id("descripcion")).sendKeys("entreno");
    driver.findElement(By.id("tipo")).click();
    new Select(driver.findElement(By.id("tipo"))).selectByVisibleText("Academico");
    driver.findElement(By.xpath("//option[@value='Academico']")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[12]")).click();
    
    //Campo Tipo Vacio
    driver.findElement(By.id("exampleFormControlSelect1")).click();
    new Select(driver.findElement(By.id("exampleFormControlSelect1"))).selectByVisibleText("FUTBOL SALA");
    driver.findElement(By.xpath("//option[@value='0: Object']")).click();
    driver.findElement(By.id("selectEscenarioDe")).click();
    new Select(driver.findElement(By.id("selectEscenarioDe"))).selectByVisibleText("Sintetica3");
    driver.findElement(By.xpath("(//option[@value='2: Object'])[2]")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("//div[@id='modalCrearReserva']/div/div/div[2]/div/div/div/div[2]/label")).click();
    driver.findElement(By.id("nombre-text-input")).click();
    driver.findElement(By.id("nombre-text-input")).clear();
    driver.findElement(By.id("nombre-text-input")).sendKeys("Johan Tomar");
    driver.findElement(By.id("descripcion")).click();
    driver.findElement(By.id("descripcion")).clear();
    driver.findElement(By.id("descripcion")).sendKeys("Juego con compañeros");
    driver.findElement(By.xpath("(//button[@type='button'])[12]")).click();
    
    //Misma hora inicio Fin
    driver.findElement(By.id("exampleFormControlSelect1")).click();
    new Select(driver.findElement(By.id("exampleFormControlSelect1"))).selectByVisibleText("FUTBOL");
    driver.findElement(By.xpath("//option[@value='1: Object']")).click();
    driver.findElement(By.id("selectEscenarioDe")).click();
    new Select(driver.findElement(By.id("selectEscenarioDe"))).selectByVisibleText("Sintetica3");
    driver.findElement(By.xpath("(//option[@value='2: Object'])[2]")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.id("inlineRadio2")).click();
    driver.findElement(By.id("nombre-text-input")).click();
    driver.findElement(By.id("nombre-text-input")).clear();
    driver.findElement(By.id("nombre-text-input")).sendKeys("Lucero tomar");
    driver.findElement(By.id("descripcion")).clear();
    driver.findElement(By.id("descripcion")).sendKeys("entreno");
    driver.findElement(By.id("tipo")).click();
    new Select(driver.findElement(By.id("tipo"))).selectByVisibleText("Evento");
    driver.findElement(By.xpath("//option[@value='Evento']")).click();
    driver.findElement(By.name("btnEnviar")).click();
    driver.findElement(By.xpath("//div[3]/div/div[3]")).click();
    driver.findElement(By.xpath("//div/div[3]/div/div")).click();
    driver.findElement(By.xpath("//div[3]/div/mwl-calendar-month-cell[2]/div")).click();

    //Campo Tipo Reserva vacio
    driver.findElement(By.id("exampleFormControlSelect1")).click();
    new Select(driver.findElement(By.id("exampleFormControlSelect1"))).selectByVisibleText("FUTBOL SALA");
    driver.findElement(By.xpath("//option[@value='0: Object']")).click();
    driver.findElement(By.id("selectEscenarioDe")).click();
    new Select(driver.findElement(By.id("selectEscenarioDe"))).selectByVisibleText("Cancha700");
    driver.findElement(By.xpath("(//option[@value='1: Object'])[2]")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.id("nombre-text-input")).click();
    driver.findElement(By.id("nombre-text-input")).clear();
    driver.findElement(By.id("nombre-text-input")).sendKeys("karen tomar");
    driver.findElement(By.id("descripcion")).clear();
    driver.findElement(By.id("descripcion")).sendKeys("entreno");
    new Select(driver.findElement(By.id("tipo"))).selectByVisibleText("Academico");
    driver.findElement(By.name("btnEnviar")).click();
    driver.findElement(By.xpath("//div[3]/div/div[3]")).click();
    driver.findElement(By.xpath("//div/div[3]/div/div")).click();
    driver.findElement(By.xpath("//div[3]/div/mwl-calendar-month-cell[2]/div")).click();
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
