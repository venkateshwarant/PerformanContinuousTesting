# PerformantContinuousTesting

## Prerequisite
1. Follow tutorial in https://github.com/venkateshwarant/UAT to create a UAT using testNG to run test sequentially
2. Follow tutorial in https://github.com/venkateshwarant/SeleniumGrid to create a selenium grid, so that we can run automation in that node
3. Follow tutorial in https://github.com/venkateshwarant/Creating_Integration_Server to create an integration server and integrate our test cases in pipeline
4. Follow tutorial in https://github.com/venkateshwarant/Creating-Stage-VM, so that we can deploy our product in the stage-vm before starting automation


## Running test cases parallely in TestNG

TestNG is a testing framework for Java that helps to organize tests in a structured way and enhances maintainability and readability to the scripts. TestNG has made it easier for automation testers owing to its large feature set. One of which is parallel testing or parallel execution. TestNG provides an auto-defined XML file, where one can set the parallel attribute to method/tests/classes and by using the concept of multi-threading of Java, one can set the number of threads, one wants to create for parallel execution. Below is the structure for defining this attribute in the TestNG XML:

```
<suite name="Parallel_Testing" parallel="methods" thread-count="2">
```
The parallel attribute can be extended for multiple values, as below:

```
Methods: Helps run methods in separate threads
Tests: Help to run all methods belonging to the same tag in the same thread
Classes: Helps to run all methods belonging to a class in a single thread
Instances: Helps run all methods in the same instance in the same thread
```
Along with the parallel attribute, the thread-count attribute helps in defining the number of threads one wishes to create while running the tests in parallel. For example, in case one has three methods, with thread count as two, then during execution, two threads shall start in parallel with the corresponding methods. As the first method execution is completed, and the thread gets free, it takes up the next method in the queue.


Sample test suite file:

```
<?xml version="1.0" encoding="UTF-8"?>
<suite name="Group of group Suite" verbose="1" parallel="methods" thread-count="5">
  <test name="Group of group Test">
    <classes>
         <class name="performantTesting.TestNG.RemoteParallelTestExecution" />
    </classes>
  </test>
</suite>
```

Sample test case:
```
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

```

In this you can see that there are 5 test methods, each doing the same job of testing our product helloworld page.

If we run the test case via the testNG.xml file as a test suite, we can see that all the 5 test methods run parallely and the order of completion is totally random, as it is running parallely

sample output: 

```
Dec 27, 2019 3:11:17 PM org.openqa.selenium.remote.DesiredCapabilities chrome
INFO: Using `new ChromeOptions()` is preferred to `DesiredCapabilities.chrome()`
Dec 27, 2019 3:11:17 PM org.openqa.selenium.remote.ProtocolHandshake createSession
INFO: Detected dialect: OSS
Hello world!  Thread 2
Hello world!  Thread 5
Hello world!  Thread 4
Hello world!  Thread 3
Hello world!  Thread 1

===============================================
Group of group Suite
Total tests run: 5, Failures: 0, Skips: 0
===============================================
```

## Integrating test cases in pipeline

To integrate test cases in pipeline, there is no difference between sequential and parallel test execution, so follow tutorial in https://github.com/venkateshwarant/UAT to integrate it in pipeline
