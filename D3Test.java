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
		driver.get("https://cs1632ex.herokuapp.com");
	}



	@Test
	public void homepageWelcomeMessageTest(){

		try{
			WebElement e = driver.findElement(By.className("jumbotron"));
			String elementText = e.getText();
			assertTrue(elementText.contains("Welcome, friend,"));
			assertTrue(elementText.contains("to a land of pure calculation"));
		}
		catch(NoSuchElementException nseex){
			fail();
		}
	}

	@Test
	public void homeLinkTest(){
		driver.findElement(By.linkTest("CS1632 D3 Home")).click();
		String url = driver.getCurrentURL();
		assertEquals("https://cs1632ex.herokuapp.com/", url);
	}
}