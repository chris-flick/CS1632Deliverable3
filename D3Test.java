import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class D3Test{

	static WebDriver driver = new HtmlUnitDriver();
	
	// Start at the home page for the deliverable for each test
	@Before
	public void setUp() throws Exception {
		driver.get("https://cs1632ex.herokuapp.com");
	}

	@Test
	public void homepageWelcomeMessage(){

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
}