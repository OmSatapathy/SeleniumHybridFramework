package testng;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Paytm {

	WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://paytm.com/");
		driver.manage().window().maximize();
		
	}
	
	
	
	@Test(priority = 1)
	public void header() {
		WebElement header =driver.findElement(By.xpath("//div[@class='_3aL54']"));
		System.out.println(header.getText());
	}
	
	
	@Test(priority = 2)
	public void rechargeOption() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    WebElement rechargeMenu = wait.until(
	            ExpectedConditions.elementToBeClickable(
	                    By.xpath("(//li[@class='_2Szju'])[1]")
	            )
	    );
	    rechargeMenu.click();

	    WebElement dthRecharge = wait.until(
	            ExpectedConditions.presenceOfElementLocated(
	                    By.xpath("//a[normalize-space()='DTH recharge']")
	            )
	    );
	    
	    ((JavascriptExecutor) driver)
	            .executeScript("arguments[0].scrollIntoView(true);", dthRecharge);


	    ((JavascriptExecutor) driver)
	            .executeScript("arguments[0].click();", dthRecharge);


	    String parentWindow = driver.getWindowHandle();

	    for (String window : driver.getWindowHandles()) {
	        if (!window.equals(parentWindow)) {
	            driver.switchTo().window(window);
	            break;
	        }
	    }

	    wait.until(ExpectedConditions.urlContains("dth-recharge"));
	    System.out.println("Navigated URL: " + driver.getCurrentUrl());

	    Assert.assertTrue(driver.getCurrentUrl().contains("dth-recharge"));
	    System.out.println(driver.getTitle());
	    
	    List<WebElement> itemas =  driver.findElements(By.xpath("//div[@class='_2sG9']//a"));
	    
	    for(int i=0; i< itemas.size(); i++) {
	    	System.out.println(itemas.get(i).getText());
	    	if(itemas.get(i).getText().contains("Metro")) {
	    		itemas.get(i).click();
	    		System.out.println("metro clicked");
	    		break;
	    	}
	    }
	}

	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
