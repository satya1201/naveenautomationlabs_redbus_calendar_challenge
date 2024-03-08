package Satya.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Satya.AbstractComponent.AbstractComponent;


public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	JavascriptExecutor js;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		this.js = (JavascriptExecutor)driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".headerIconTextAlignment")
	List<WebElement> header_menu;
	
	@FindBy(css = "div[id='webengage-notification-container'] iframe[title='notification-frame-3176854a']")
	WebElement ads;
	
	@FindBy(css = ".close")
	WebElement close;
	
	@FindBy(css = ".primaryTraveler .fswTabs li")
	List<WebElement> selectTrip;
	
	@FindBy(css = ".tabsCircle ")
	List<WebElement> radio_button;
	
	@FindBy(css = "li[data-cy='roundTrip']")
	WebElement roundTrip;
	
	@FindBy(xpath = "//label[@for='fromCity']")
	WebElement fromCity;
	
	@FindBy(className = "react-autosuggest__input")
	WebElement suggestionList;
	
	@FindBy(css = "li[id='react-autowhatever-1-section-0-item-0']")
	WebElement citySelect;
	
	@FindBy(xpath = "//label[@for='toCity']")
	WebElement toCity;
	
	@FindBy(className = "primaryBtn")
	WebElement submit;
	
	By depDate = By.cssSelector(".DayPicker-Day--today");
	By retDate = By.cssSelector("div[aria-disabled='false']");
	
	WebElement depdate;
	List<WebElement> arrDate;
	
	
	public FlightPage searchFlight(String FromCity, String ToCity, String menu, String Trips) throws InterruptedException
	{
		homepageAd();
		selectMenu(menu);
		Trip(Trips);
		fromCity.click();
		selectCity(FromCity);
		toCity.click();
		selectCity(ToCity);
		depdate = visibilityOfElementLocated(depDate);
		depdate.click();
		arrDate = presenceOfAllElementsLocatedBy(retDate);
		arrDate.get(7).click();
		submit.click();
		FlightPage flightpage = new FlightPage(driver);
		return flightpage;
		
	}
	
	public void homepageAd() throws InterruptedException
	{
		try {
		if(ads.isDisplayed())
		{
			driver.switchTo().frame(ads);
			close.click();
			driver.switchTo().defaultContent();
		}
		}catch (NoSuchElementException | StaleElementReferenceException | ElementNotInteractableException e) {
			// TODO Auto-generated catch block
		}
	}
	
	public void selectMenu(String option) throws InterruptedException
	{
		Thread.sleep(2000);
		if(option.equalsIgnoreCase("Flights"))
		{
		   for(WebElement e: header_menu)
		   {
			   try {
			         if(e.getText().equalsIgnoreCase(option))
			           {
				         e.click();
			           }
			   }catch (NoSuchElementException | StaleElementReferenceException | ElementNotInteractableException ex) {
					String url = driver.getCurrentUrl();
					PageFactory.initElements(driver, this);
				}
		   }
		}
		else 
		{
			System.out.println("Other Options Not Available");
		}
		
	}
	
	public void Trip(String trip) throws InterruptedException
	{
		
		if(trip.equalsIgnoreCase("Round Trip"))
		{
		  for(WebElement e: selectTrip)
		   {
			  int count = 0;
			  if(e.getText().contentEquals(trip))
			  {
				count++;
				radio_button.get(count).click();
			  }
		   }
		}
		else {
			System.out.println("Other Options Not Available");
		}
	}
	
	public void selectCity(String city) throws InterruptedException
	{
		suggestionList.sendKeys(city);
		Thread.sleep(1500);
		citySelect.click();
	}
	
	public void goTo()
	{
		driver.get("https://www.makemytrip.com/");
	}
	
	

}
