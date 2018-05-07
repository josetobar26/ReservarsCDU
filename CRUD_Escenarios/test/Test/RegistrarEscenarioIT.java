package Test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class RegistrarEscenarioIT {
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
  public void testRegistrarEscenarioIT() throws Exception {
    driver.get("http://localhost:4200/espaciodeportivos");
    driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
    driver.findElement(By.id("nombre")).click();
    driver.findElement(By.id("nombre")).clear();
    driver.findElement(By.id("nombre")).sendKeys("Sintetica 4");
    driver.findElement(By.id("ubicacion")).click();
    new Select(driver.findElement(By.id("ubicacion"))).selectByVisibleText("CDU");
    driver.findElement(By.id("ubicacion")).click();
    driver.findElement(By.id("estado")).click();
    new Select(driver.findElement(By.id("estado"))).selectByVisibleText("En Servicio");
    driver.findElement(By.id("estado")).click();
    driver.findElement(By.id("descripcion")).click();
    driver.findElement(By.id("descripcion")).clear();
    driver.findElement(By.id("descripcion")).sendKeys("Cancha para entrenamiento seleccion");
    driver.findElement(By.id("deporte")).click();
    new Select(driver.findElement(By.id("deporte"))).selectByVisibleText("FUTBOL SALA");
    driver.findElement(By.id("deporte")).click();
    driver.findElement(By.linkText("Anexar Deporte")).click();
    acceptNextAlert = true;
    driver.findElement(By.name("btnEnviar")).click();
    assertTrue(closeAlertAndGetItsText().matches("^¿ DESEA REGISTRAR ESTE ESPACIO DEPORTIVO [\\s\\S]$"));
    
    //Mas de 30 caracteres en el nombre
    driver.get("http://localhost:4200/espaciodeportivos");
    driver.findElement(By.id("nombre")).click();
    driver.findElement(By.id("nombre")).clear();
    driver.findElement(By.id("nombre")).sendKeys("ssssssssssssssssssssssssssssss");
    driver.findElement(By.id("ubicacion")).click();
    new Select(driver.findElement(By.id("ubicacion"))).selectByVisibleText("CDU");
    driver.findElement(By.id("ubicacion")).click();
    driver.findElement(By.id("estado")).click();
    new Select(driver.findElement(By.id("estado"))).selectByVisibleText("En Servicio");
    driver.findElement(By.id("estado")).click();
    driver.findElement(By.id("descripcion")).click();
    driver.findElement(By.id("descripcion")).clear();
    driver.findElement(By.id("descripcion")).sendKeys("cascasca");
    driver.findElement(By.linkText("Anexar Deporte")).click();
    driver.findElement(By.id("deporte")).click();
    new Select(driver.findElement(By.id("deporte"))).selectByVisibleText("FUTBOL SALA");
    driver.findElement(By.id("deporte")).click();
    driver.findElement(By.linkText("Anexar Deporte")).click();
    acceptNextAlert = true;
    driver.findElement(By.name("btnEnviar")).click();
    assertTrue(closeAlertAndGetItsText().matches("^¿ DESEA REGISTRAR ESTE ESPACIO DEPORTIVO [\\s\\S]$"));
    
    //Solamente deporte
    driver.findElement(By.id("nombre")).clear();
    driver.findElement(By.id("nombre")).sendKeys("");
    driver.findElement(By.id("ubicacion")).click();
    new Select(driver.findElement(By.id("ubicacion"))).selectByVisibleText("Seleccione...");
    driver.findElement(By.id("ubicacion")).click();
    driver.findElement(By.linkText("Anexar Deporte")).click();
    assertEquals("EL DEPORTE << FUTBOL SALA >> YA SE ENCUENTRA SELECCIONADO", closeAlertAndGetItsText());
    
    //solamente ubicacion
    driver.findElement(By.id("estado")).click();
    new Select(driver.findElement(By.id("estado"))).selectByVisibleText("Seleccione...");
    driver.findElement(By.id("estado")).click();
    driver.findElement(By.id("ubicacion")).click();
    new Select(driver.findElement(By.id("ubicacion"))).selectByVisibleText("CDU");
    driver.findElement(By.id("ubicacion")).click();
    driver.findElement(By.linkText("Anexar Deporte")).click();
    assertEquals("EL DEPORTE << FUTBOL SALA >> YA SE ENCUENTRA SELECCIONADO", closeAlertAndGetItsText());
    
    //Solamente estado y deporte
    driver.findElement(By.id("nombre")).click();
    driver.findElement(By.id("ubicacion")).click();
    driver.findElement(By.xpath("//form[@id='frmEspaciodeportivo']/fieldset/div[4]/div/div/div")).click();
    driver.findElement(By.id("estado")).click();
    new Select(driver.findElement(By.id("estado"))).selectByVisibleText("En Servicio");
    driver.findElement(By.id("estado")).click();
    driver.findElement(By.id("deporte")).click();
    new Select(driver.findElement(By.id("deporte"))).selectByVisibleText("FUTBOL");
    driver.findElement(By.id("deporte")).click();
    driver.findElement(By.linkText("Anexar Deporte")).click();
    driver.findElement(By.id("nombre")).click();
    driver.findElement(By.id("nombre")).clear();
    driver.findElement(By.id("nombre")).sendKeys("cancha 1");
    acceptNextAlert = true;
    driver.findElement(By.name("btnEnviar")).click();
    assertTrue(closeAlertAndGetItsText().matches("^¿ DESEA REGISTRAR ESTE ESPACIO DEPORTIVO [\\s\\S]$"));
    
    driver.findElement(By.name("btnCancelar")).click();
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
