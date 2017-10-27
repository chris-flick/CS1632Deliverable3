import static org.junit.Assert.*;

import java.util.logging.*;

import org.junit.*;
import org.openqa.selenium.*;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import org.openqa.selenium.logging.*;

import org.openqa.selenium.remote.*;

public class D3Test{

	static WebDriver driver = new HtmlUnitDriver();

	@BeforeClass
		public static void setUpDriver() {

		// Note that the logging level is a Java standard (thus the
		// use of a java.util class instead of something specific
		// to Selenium.  You can modify these levels yourself if,
		// for example, you would like to see only SEVERE errors.
		// They can be set to ALL (show all messages) or OFF (no messages)
		// or to any minimum level from FINEST (most verbose) to SEVERE
		// (only show the most egregious of errors).
		// A full lists of levels and descriptions are located at:
		// https://docs.oracle.com/javase/7/docs/api/java/util/logging/Level.html

		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
		driver = new HtmlUnitDriver();

		}

	// Start at the home page for the deliverable for each test
	@Before
	public void setUp() throws Exception {
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
		driver.get("https://cs1632ex.herokuapp.com");
	}

	//If I'm on the main page, the main div should display
	//"Welcome, friend, to a land of pure calculation"
	//Test for requirement 1
	@Test //Test #1
	public void homepageWelcomeMessage(){
		try{
			WebElement e = driver.findElement(By.className("jumbotron"));
			String elementText = e.getText();
			assertTrue(elementText.contains("Welcome, friend,"));
			assertTrue(elementText.contains("to a land of pure calculation"));

			e = driver.findElement(By.className("row"));
			elementText = e.getText();
			assertTrue(elementText.contains("Used for CS1632 Software Quality Assurance, taught by Bill Laboon"));
		}
		catch(NoSuchElementException nseex){
			fail();
		}
	}


	//If im on the main page,
	//And I look on the navigation bar,
	//I should see CS1632 D3 Home, Factorial, Fibonacci, Hello, and Cathedral Pics
	//Test for requirement 2
	@Test //Test #2
	public void linkTextTest(){
		try{
			driver.findElement(By.linkText("CS1632 D3 Home"));
			driver.findElement(By.linkText("Factorial"));
			driver.findElement(By.linkText("Fibonacci"));
			driver.findElement(By.linkText("Hello"));
			driver.findElement(By.linkText("Cathedral Pics"));
		}catch(NoSuchElementException nseex){
			fail();
		}
	}

	//If im on the main page, or any other page,
	//and I click on any of the web links, it should take me to the
	//correct destination. /, /hello, /fact, /cathy, /fib
	//Test for requirement 2
	@Test //Test #3
	public void linkTest(){
		String linkHello = "https://cs1632ex.herokuapp.com/hello";
		String linkHome = "https://cs1632ex.herokuapp.com/";
		String linkFactorial = "https://cs1632ex.herokuapp.com/fact";
		String linkFib = "https://cs1632ex.herokuapp.com/fib";
		String linkCathy = "https://cs1632ex.herokuapp.com/cathy";

		WebElement link = driver.findElement(By.linkText("CS1632 D3 Home"));
		assertEquals(link.getAttribute("href"),linkHome);

		link = driver.findElement(By.linkText("Factorial"));
		assertEquals(link.getAttribute("href"),linkFactorial);

		link = driver.findElement(By.linkText("Fibonacci"));
		assertEquals(link.getAttribute("href"),linkFib);

		link = driver.findElement(By.linkText("Cathedral Pics"));
		assertEquals(link.getAttribute("href"),linkCathy);

		link = driver.findElement(By.linkText("Hello"));
		assertEquals(link.getAttribute("href"),linkHello);
	}

	//If I'm on the factorial page, and I enter 5, it should
	//produce a result that states, "Factorial of 5 is 120!"
	//I Used xpath because that specific button... has no name or id... and there isnt byValue lookup.
	//Therefore the best way to write the test is by xpath.
	//Test for requirement 3
	@Test //Test #4
	public void factTest_1(){
		driver.get("https://cs1632ex.herokuapp.com/fact");
		driver.findElement(By.name("value")).sendKeys("5");
		driver.findElement(By.xpath("/html/body/div/main/div/form/input[2]")).click();
		String result = driver.findElement(By.className("jumbotron")).getText();
		assertTrue(result.contains("Factorial of 5 is 120!"));
	}

	//If I'm on the factorial page, and I enter 0, it should
	//produce a result that states, "Factorial of 0 is 1!"
	//Any number outside of 1-100 should have a factorial result of 1.
	//Test for requirement 5
	@Test //Test #5.
	public void factTest_2(){
		driver.get("https://cs1632ex.herokuapp.com/fact");
		driver.findElement(By.name("value")).sendKeys("0");
		driver.findElement(By.xpath("/html/body/div/main/div/form/input[2]")).click();
		String result = driver.findElement(By.className("jumbotron")).getText();
		assertTrue(result.contains("Factorial of 0 is 1!"));
	}

	//If I'm on the factorial page, and I enter 1, it should
	//produce a result that states, "Factorial of 1 is 1!"
	//Test for requirement 3
	@Test //Test #6
	public void factTest_3(){
		driver.get("https://cs1632ex.herokuapp.com/fact");
		driver.findElement(By.name("value")).sendKeys("1");
		driver.findElement(By.xpath("/html/body/div/main/div/form/input[2]")).click();
		String result = driver.findElement(By.className("jumbotron")).getText();
		assertTrue(result.contains("Factorial of 1 is 1!"));
	}

	//If I'm on the factorial page, and I enter -0, it should
	//produce a result that states, "Factorial of -0 is 1!"
	//Any number outside of 1-100 should have a factorial result of 1.
	//Test for requirement 5
	@Test //Test #7
	public void factTest_4(){
		driver.get("https://cs1632ex.herokuapp.com/fact");
		driver.findElement(By.name("value")).sendKeys("-0");
		driver.findElement(By.xpath("/html/body/div/main/div/form/input[2]")).click();
		String result = driver.findElement(By.className("jumbotron")).getText();
		assertTrue(result.contains("Factorial of -0 is 1!"));
	}

	//If I'm on the fibonacci page, and I enter 1, it should
	//produce a result that states, "Fibonacci of 1 is 1!"
	//Test for requirement 4
	@Test //Test #8
	public void fibTest_1(){
		driver.get("https://cs1632ex.herokuapp.com/fib");
		driver.findElement(By.id("tb1")).sendKeys("1");
		driver.findElement(By.id("sub")).click();
		String result = driver.findElement(By.className("jumbotron")).getText();
		assertTrue(result.contains("Fibonacci of 1 is 1!"));
	}

	//If I'm on the fibonacci page, and I enter -0, it should
	//produce a result that states, "Fibonacci of -0 is 1!"
	//Any number outside of 1-100 should have a factorial result of 1.
	//Test for requirement 5
	@Test //Test #9
	public void fibTest_3(){
		driver.get("https://cs1632ex.herokuapp.com/fib");
		driver.findElement(By.id("tb1")).sendKeys("-0");
		driver.findElement(By.id("sub")).click();
		String result = driver.findElement(By.className("jumbotron")).getText();
		assertTrue(result.contains("Fibonacci of -0 is 1!"));
	}

	//If I'm on the fibonacci page, and I enter 101, it should
	//produce a result that states, "Fibonacci of 101 is 1!"
	//Any number outside of 1-100 should have a factorial result of 1.
	//Test for requirement 5
	@Test //Test #10
	public void fibTest_4(){
		driver.get("https://cs1632ex.herokuapp.com/fib");
		driver.findElement(By.id("tb1")).sendKeys("101");
		driver.findElement(By.id("sub")).click();
		String result = driver.findElement(By.className("jumbotron")).getText();
		assertTrue(result.contains("Fibonacci of 101 is 1!"));
	}

	//If I'm on the fibonacci page, and I enter -100, it should
	//produce a result that states, "Fibonacci of -100 is 1!"
	//Any number outside of 1-100 should have a factorial result of 1.
	//Test for requirement 5
	@Test //Test #11
	public void fibTest_5(){
		driver.get("https://cs1632ex.herokuapp.com/fib");
		driver.findElement(By.id("tb1")).sendKeys("-100");
		driver.findElement(By.id("sub")).click();
		String result = driver.findElement(By.className("jumbotron")).getText();
		assertTrue(result.contains("Fibonacci of -100 is 1!"));
	}

	//If I'm on the /hello page with no trailing values,
	//It should display "Hello CS1632, from Prof. Laboon!"
	//on the main div
	//Test for requirement 6
	@Test //Test #12
	public void helloTest_1(){
		driver.get("https://cs1632ex.herokuapp.com/hello");
		String result = driver.findElement(By.className("jumbotron")).getText();
		assertEquals(result,"Hello CS1632, from Prof. Laboon!");
	}

	//If i'm on the /hello page but with a trailing value of /Jazzy,
	//It should display "Hello CS1632, from Jazzy!"
	//on the main div
	//Test for requirement 7
	@Test //Test #13
	public void helloTest_2(){
		driver.get("https://cs1632ex.herokuapp.com/hello/Jazzy");
		String result = driver.findElement(By.className("jumbotron")).getText();
		assertEquals(result,"Hello CS1632, from Jazzy!");
	}


	//If i'm on the /hello page but with a trailing value of /fake news,
	//It should display "Hello CS1632, from fake news!"
	//on the main div
	//Test for requirement 7
	@Test //Test #14
	public void helloTest_3(){
		driver.get("https://cs1632ex.herokuapp.com/hello/fake news");
		String result = driver.findElement(By.className("jumbotron")).getText();
		assertEquals(result,"Hello CS1632, from fake news!");
	}


	/* -------------------------------------------------------- Defects ---------------------------------------------------- */

	//If I'm on the fibonacci page, and I enter 100, it should
	//produce a result that states, "Fibonacci of 100 is 354224848179261915075!"
	//Any number outside of 1-100 should have a factorial result of 1.
	//Fails because it does not produce 1.
	//Test for requirement 5
	@Test //Test #15
	public void fibTest_2(){
		driver.get("https://cs1632ex.herokuapp.com/fib");
		driver.findElement(By.id("tb1")).sendKeys("100");
		driver.findElement(By.id("sub")).click();
		String result = driver.findElement(By.className("jumbotron")).getText();
		assertTrue(result.contains("Fibonacci of 100 is 354224848179261915075!"));
	}


	//If I'm on the fibonacci page, and I enter a, it should
	//produce a result that states, "Fibonacci of a is 1!"
	//Any number outside of 1-100 should have a factorial result of 1.
	//Fails because it does not produce 1.
	//Test for requirement 5
	@Test //Test #16
	public void fibTest_6(){
		try{
		driver.get("https://cs1632ex.herokuapp.com/fib");
		driver.findElement(By.id("tb1")).sendKeys("a");
		driver.findElement(By.id("sub")).click();
		String result = driver.findElement(By.className("jumbotron")).getText();
		assertTrue(result.contains("Fibonacci of a is 1!"));
		}catch(NoSuchElementException e){
		fail();
		}
	}

	//If I'm on the factorial page, and I enter a, it should
	//produce a result that states, "Factorial of a is 1!"
	//Any number outside of 1-100 should have a factorial result of 1.
	//Fails because it does not produce 1.
	//Test for requirement 5
	@Test //Test #17
	public void factTest_5(){
		try{
		driver.get("https://cs1632ex.herokuapp.com/fact");
		driver.findElement(By.name("value")).sendKeys("a");
		driver.findElement(By.xpath("/html/body/div/main/div/form/input[2]")).click();
		String result = driver.findElement(By.className("jumbotron")).getText();
		assertTrue(result.contains("Factorial of a is 1!"));
	}catch(NoSuchElementException e){
		fail();
	}
	}

	//If I add the trailing value of "/Jazzy#123"
	//To /hello, it should display "Hello CS1632, from Jazzy#123!"
	//Fails because it does not displays what is expected
	//Test for requirement 7
	@Test //Test #18
	public void helloTest_4(){
		driver.get("https://cs1632ex.herokuapp.com/hello/Jazzy#123");
		String result = driver.findElement(By.className("jumbotron")).getText();
		assertEquals("Hello CS1632, from Jazzy#123!",result);
	}

	//If I add the trailing value of "/Jazzy/123"
	//To /hello, it should display "Hello CS1632, from Jazzy/123!"
	//Fails because it does not displays what is expected
	//Test for requirement 7
	@Test //Test #19
	public void helloTest_5(){
		try{
		driver.get("https://cs1632ex.herokuapp.com/hello/Jazzy/123");
		String result = driver.findElement(By.className("jumbotron")).getText();
		assertEquals("Hello CS1632, from Jazzy/123!",result);
	}catch(NoSuchElementException e){
		fail();
	}
	}

	//If I add the trailing value of "/ "
	//To /hello, it should display "Hello CS1632, from  !"
	//Fails because it does not displays what is expected
	//Test for requirement 7
	@Test //Test #20
	public void helloTest_6(){
		driver.get("https://cs1632ex.herokuapp.com/hello/ ");
		String result = driver.findElement(By.className("jumbotron")).getText();
		assertEquals("Hello CS1632, from  !",result);
	}

	//If i'm on the factorial page and I do not enter anything
	//It should display the factorial of null is 1.
	//Test for requirement 5
	@Test //Test #21
	public void factTest_6(){
		try{
			driver.get("https://cs1632ex.herokuapp.com/fact");
			driver.findElement(By.xpath("/html/body/div/main/div/form/input[2]")).click();
			String result = driver.findElement(By.className("jumbotron")).getText();
		}catch(NoSuchElementException e){
			fail();
		}
	}

	//If I'm on the fibonacci page and I do not enter anything
	//It should display the fibonacci of null is 1.
	//Test for requirement 5
	@Test //Test #22
	public void fibTest_7(){
		try{
			driver.get("https://cs1632ex.herokuapp.com/fib");
			driver.findElement(By.id("sub")).click();
			String result = driver.findElement(By.className("jumbotron")).getText();
		}catch(NoSuchElementException e){
			fail();
		}
	}

	/* ------------------------------------------------------ End Defects -------------------------------------------------- */

	//If I'm on the factorial page, and I enter 100, it should
	//produce a result that states, "Factorial of 100 is 93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000!"
	//Test for requirement 5
	@Test //Test #23
	public void factTest_7(){
		driver.get("https://cs1632ex.herokuapp.com/fact");
		driver.findElement(By.name("value")).sendKeys("100");
		driver.findElement(By.xpath("/html/body/div/main/div/form/input[2]")).click();
		String result = driver.findElement(By.className("jumbotron")).getText();
		assertTrue(result.contains("Factorial of 100 is 93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000!"));
	}











}
