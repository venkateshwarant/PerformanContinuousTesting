package performantTesting.TestNG;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RemoteParallelTestExecution {
private static WebDriver driver;


@BeforeTest
public static void configureDriver() throws MalformedURLException {
	final ChromeOptions chromeOptions = new ChromeOptions();
	chromeOptions.addArguments("--headless");
	chromeOptions.addArguments("--no-sandbox");
	chromeOptions.addArguments("--disable-dev-shm-usage");
	chromeOptions.addArguments("--window-size=1200x600");

	chromeOptions.setBinary("/usr/bin/google-chrome");
		DesiredCapabilities capability1 = DesiredCapabilities.chrome();
		capability1.setBrowserName("chrome");
		capability1.setPlatform(Platform.LINUX);

		capability1.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

		driver = new RemoteWebDriver(new URL("http://192.168.33.13:4444/wd/hub"), capability1);
}	

	
	@Test
	public static void testHelloWorldThread1() throws InterruptedException {
		driver.get("http://192.168.33.10/helloworld.html");
		System.out.println(driver.findElement(By.id("hello")).getText()+ "  Thread 1");
		Assert.assertEquals("Hello world!", driver.findElement(By.id("hello")).getText());
		Thread.sleep(1000);
	}
	
	@Test
	public static void testHelloWorldThread2() throws InterruptedException {
		driver.get("http://192.168.33.10/helloworld.html");
		System.out.println(driver.findElement(By.id("hello")).getText()+ "  Thread 2");
		Assert.assertEquals("Hello world!", driver.findElement(By.id("hello")).getText());
		Thread.sleep(1000);
	}
	@Test
	public static void testHelloWorldThread3() throws InterruptedException {
		driver.get("http://192.168.33.10/helloworld.html");
		System.out.println(driver.findElement(By.id("hello")).getText()+ "  Thread 3");
		Assert.assertEquals("Hello world!", driver.findElement(By.id("hello")).getText());
		Thread.sleep(1000);
	}
	@Test
	public static void testHelloWorldThread4() throws InterruptedException {
		driver.get("http://192.168.33.10/helloworld.html");
		System.out.println(driver.findElement(By.id("hello")).getText()+ "  Thread 4");
		Assert.assertEquals("Hello world!", driver.findElement(By.id("hello")).getText());
		Thread.sleep(1000);
	}
	@Test
	public static void testHelloWorldThread5() throws InterruptedException {
		driver.get("http://192.168.33.10/helloworld.html");
		System.out.println(driver.findElement(By.id("hello")).getText()+ "  Thread 5");
		Assert.assertEquals("Hello world!", driver.findElement(By.id("hello")).getText());
		Thread.sleep(1000);
	}
	@AfterTest
	public static void closeDriver() {
		driver.quit();
	}
}
