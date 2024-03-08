package Satya.FrameworkDesign;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import Satya.pageobjects.FlightPage;
import Satya.pageobjects.LandingPage;

public class SingleTest {
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		landingPage.searchFlight("Delhi", "Bengaluru", "Flights", "Round Trip");
	    Thread.sleep(20000);
		FlightPage flightPage = new FlightPage(driver);
		flightPage.FlightList();
		driver.close();
	}
}

