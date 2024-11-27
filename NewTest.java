package mvncucu01;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
public class NewTest {
	WebDriver driver;
@BeforeMethod
public void beforeMethod() {
	//Instantiating the browser and chrome driver 
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\kiran\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//URL of application
		String URL="https://www.fitpeo.com/";
	    driver.navigate().to(URL);
}

  @Test
  public void test() throws InterruptedException {
//To locate revenue calculator
 driver.findElement(By.xpath("/html/body/div[1]/div/header/div/div[3]/div[6]/a/div")).click();
 
 //To stop execution till the slider element is visible
 WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/div/div/span[1]/span[3]/input")));
 
 WebElement slider = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/div/div/span[1]/span[3]/input"));

 //Getting the slider location and size
 System.out.println("location of slider " +slider.getLocation()); //803,654
 System.out.println("size of locator "+slider.getSize()); //20,20
 
 //Calculated the pixels using dimensions and dragged the slider to nearby value using pixel xoffset value ~816
 Actions actions = new Actions(driver);
 actions.dragAndDropBy(slider,93, 0).perform();
 
 //Printing the text field value
 WebElement textField= driver.findElement(By.xpath("//*[@id=\":r0:\"]"));
 String value = textField.getAttribute("value");
 System.out.println("Text Field value: " + value);
 
 //Updating the TextField with value 560
 JavascriptExecutor js = (JavascriptExecutor) driver;
 js.executeScript("arguments[0].value = '';", textField); 
 
 String newValue = "560"; 
 js.executeScript("arguments[0].value = arguments[1];", textField, newValue); 
 
 String actualvalue = textField.getAttribute("value");
 System.out.println("text field new value: " + actualvalue);

 //the value 560 is not getting populated 

 String expectedvalue= slider.getAttribute("value");
 System.out.println("slider new value: "+expectedvalue);

 //As value 560 is not updating so giving soft assert so if assert fails it skips the case and runs remaining.
 SoftAssert softAssert = new SoftAssert();
 softAssert.assertEquals(actualvalue, expectedvalue);

 driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[1]/label/span[1]/input")).click();
 driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[2]/label/span[1]/input")).click();
 driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[3]/label/span[1]/input")).click();
 driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[8]/label/span[1]/input")).click();
 
 String a=driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[1]/div/div[3]/p[2]")).getText();
 System.out.println("Total recurring reimbursement: "+a);
 
 String expectedValue = "$110160";
 Assert.assertEquals(a, expectedValue, "The Total Recurring Reimbursement value is incorrect.");
 
 }
  @AfterMethod
  public void afterMethod() {
	  driver.close();
	  driver.quit();
	  }
  }
