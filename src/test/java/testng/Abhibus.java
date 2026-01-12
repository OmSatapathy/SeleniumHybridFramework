package testng;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

@Test
public class Abhibus {

	WebDriver driver;

	@BeforeClass
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.abhibus.com/");

	}

	// input[contains(@placeholder,'Leaving From')]

	@Test(enabled = false)
	public void findLinks() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Leaving From']")));
		driver.findElement(By.xpath("//input[@placeholder='Leaving From']")).click();

		List<WebElement> fromLocations = driver.findElements(By.xpath("//div[@class='text-neutral-800 col']"));
		for (int i = 0; i < fromLocations.size(); i++) {

			System.out.println(fromLocations.get(i).getText());
		}

		driver.findElement(By.xpath("//input[@placeholder='Leaving From']")).sendKeys("Pune");

		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='text-neutral-800 col']")));

		List<WebElement> puneLocations = driver.findElements(By.xpath("//div[@class='text-neutral-800 col']"));

		for (int i = 0; i < puneLocations.size(); i++) {

			System.out.println(puneLocations.get(i).getText());
			puneLocations.get(3).click();

		}

		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Going To']")));
		driver.findElement(By.xpath("//input[@placeholder='Going To']")).click();

		List<WebElement> mumbaiLocations = driver.findElements(By.xpath("//div[@class='text-neutral-800 col']"));
		driver.findElement(By.xpath("//input[@placeholder='Going To']")).sendKeys("Mumbai");
		for (int i = 0; i < mumbaiLocations.size(); i++) {

			System.out.println(mumbaiLocations.get(i).getText());

			mumbaiLocations.get(2).click();

		}

	}

	@Test
	public void findWorkingLinks() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		WebElement link = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//a[normalize-space()='Hyderabad to Bangalore Bus']")));

		wait.until(ExpectedConditions.elementToBeClickable(link)).click();

		wait.until(ExpectedConditions.urlContains("Hyderabad-Bangalore"));

		Assert.assertEquals(driver.getCurrentUrl(), "https://www.abhibus.com/buses/1/Hyderabad-Bangalore");
	}

	@Test
	public void findFooterlinks() {

		List<WebElement> allfooterLinks = driver
				.findElements(By.xpath("//div[@class='container footer-container-padding   landscape ']//a"));
	
		
		Map<String, String> linkMap = new HashMap<>();
		for(WebElement ele : allfooterLinks) {
			linkMap.put(ele.getText(), ele.getAttribute("herf"));
		}
		
		linkMap.forEach((k, v) ->
        System.out.println(k + " --> " + v)
);

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
