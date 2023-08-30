package com.satwa.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHRMTests {
	private OrangeHRMTests orangeHRM;
	private WebDriver driver;
	
	private static ThreadLocal<OrangeHRMTests> driverThread = new ThreadLocal<>();
	
	public static void set(OrangeHRMTests driver) {
		driverThread.set(driver);
	}
	
	public static OrangeHRMTests get() {
		return driverThread.get();
	}
	
	@BeforeMethod
	public void beforeMethod() {
		OrangeHRMTests.set(new OrangeHRMTests());
		
	}
	
	@Parameters({"browser","url"})
	@Test
	public void createAnEmployee(String browser, String url) {
		OrangeHRMTests.get().launch(browser, url);
		OrangeHRMTests.get().login("Admin", "admin123");
		OrangeHRMTests.get().createEmployee();
		OrangeHRMTests.get().logout();
		
	}
	
	@Parameters({"browser","url"})
	@Test
	public void addLogInPerformance(String browser, String url) {
		OrangeHRMTests.get().	launch(browser, url);
		OrangeHRMTests.get().login("Admin", "admin123");
		OrangeHRMTests.get().addLog();
		OrangeHRMTests.get().logout();
		
	}
	
	public void addLog() {
		OrangeHRMTests.get().driver.findElement(By.xpath("//span[text()='Performance']")).click();
		OrangeHRMTests.get().driver.findElement(By.xpath("//a[text()='Employee Trackers']")).click();
		OrangeHRMTests.get().driver.findElement(By.xpath("(//button[@name='view'])[1]")).click();
		OrangeHRMTests.get().driver.findElement(By.xpath("//h5/following-sibling::button")).click();
		OrangeHRMTests.get().driver.findElement(By.xpath("//input[@placeholder='Type here']")).sendKeys("dsadsa");
		OrangeHRMTests.get().driver.findElement(By.xpath("(//button[contains(@class,'orangehrm-tracker-rating-button')])[1]")).click();
		OrangeHRMTests.get().driver.findElement(By.xpath("//button[contains(@class,'oxd-button--ghost')]")).click();
	}
	
	
	public void logout() {
		OrangeHRMTests.get().driver.findElement(By.xpath("//i[starts-with(@class,'oxd-icon bi-caret-down-fill')]")).click();
		OrangeHRMTests.get().driver.findElement(By.xpath("//a[text()='Logout']")).click();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
		}
		
		OrangeHRMTests.get().driver.quit();
		
	}
	
	public void createEmployee() {
		OrangeHRMTests.get().driver.findElement(By.xpath("//span[text()='PIM']")).click();
		OrangeHRMTests.get().driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		OrangeHRMTests.get().driver.findElement(By.name("firstName")).sendKeys("dasdas");
		OrangeHRMTests.get().driver.findElement(By.name("lastName")).sendKeys("r3qdwa");
		String empId =OrangeHRMTests.get().driver.findElement(By.xpath("//label[text()='Employee Id']/../..//input[starts-with(@class,'oxd-input')]")).getAttribute("value");
//		System.out.println(empId);
	}
	
	public void login(String userName, String passWord) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
		}
		OrangeHRMTests.get().driver.findElement(By.name("username")).sendKeys(userName);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
		}
		OrangeHRMTests.get().driver.findElement(By.name("password")).sendKeys(passWord);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
		}
		OrangeHRMTests.get().driver.findElement(By.xpath("//button[contains(@class,'oxd-button')]")).click();
	}
	
	
	public void launch(String browser, String url) {
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			OrangeHRMTests.get().driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			OrangeHRMTests.get().driver = new FirefoxDriver();
			break;
			
		default:
			WebDriverManager.edgedriver().setup();
			OrangeHRMTests.get().driver = new EdgeDriver();
			break;
		}
		
		OrangeHRMTests.get().driver.get(url);
		
	//	driver.manage().window().maximize();
		OrangeHRMTests.get().driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
	}

}
