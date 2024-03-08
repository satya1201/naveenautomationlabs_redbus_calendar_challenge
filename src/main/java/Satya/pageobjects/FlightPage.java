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

public class FlightPage extends AbstractComponent {
	
    static WebDriver driver;
    JavascriptExecutor js;
	
	public FlightPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		this.js = (JavascriptExecutor)driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".splitVw .paneView")
	List<WebElement> pane;
	
	@FindBy(css = ".shadowList ")
	List<WebElement> groups;
	
	@FindBy(css = ".splitVw .paneView:nth-child(1) .listingCard ")
	List<WebElement> departurelist;
	
	@FindBy(css = ".splitVw .paneView:nth-child(2) .listingCard ")
	List<WebElement> arrivallist;
	
	@FindBy(css = ".itemsWrapper .filtersOuter .makeFlex .checkboxContainer .sizeSm input[type='checkbox']")
	List<WebElement> checklist;
	
	@FindBy(css = ".clearFilter")
	WebElement clearfilter;
	
	@FindBy(css = ".splitVw .paneView:nth-child(1) .customRadioBtn input[type='radio']")
	static List<WebElement> left;
	
	@FindBy(css = ".splitVw .paneView:nth-child(1) .priceInfo .column .blackText")
	List<WebElement> dprice;
	
	@FindBy(css = ".splitVw .paneView:nth-child(2) .customRadioBtn input[type='radio']")
	static List<WebElement> right;
	
	@FindBy(css = ".splitVw .paneView:nth-child(2) .priceInfo .column .blackText")
	static
	List<WebElement> aprice;
	
	@FindBy(css = ".splitviewSticky .appendRight15 .spaceBetween div:nth-child(2) p:nth-child(1)")
	static
	List<WebElement> price;
	
	@FindBy(css = ".pushRight .appendRight10 .whiteText")
	static
	WebElement finalprice;
	
	@FindBy(css = ".pushRight .appendRight10 .lightGreenText")
	static
	WebElement discountamount;
	
	@FindBy(css = ".error-title")
	WebElement error;

	By listingcard = By.cssSelector(".listingCard .arrowDown");
	
	public void FlightList() throws InterruptedException
	{
		Thread.sleep(5000);
	    js.executeScript("document.querySelector('.overlayCrossIcon').click()");
	}
	
	public void checkGroup() throws InterruptedException
	{
		slowScrollDown(js);
		for(WebElement p:pane)
		{
		if(groups.size()>0)
		{
		for(WebElement g:groups)
		{
			Thread.sleep(5000);
			visibilityOfElement(listingcard).click();
			action(driver);
		}
		}
		}
	}
	
	public int departureList()
	{
		return departurelist.size();
	}
	
	public int arrivalList()
	{
		return arrivallist.size();
	}
	public void NonStop() throws InterruptedException
	{
		checklist.get(0).click();
		Thread.sleep(2000);
		checklist.get(2).click();
		slowScrollDown(js);
	}
	
	public void OneStop() throws InterruptedException
	{
		checklist.get(1).click();
		Thread.sleep(2000);
		checklist.get(3).click();
		slowScrollDown(js);
	}
	
	public String departureFlight(int d) throws InterruptedException
	{
		String pricedep = "0,0";
		if(d>0 && d<=10)
		{
		left.get(d-1).click();
		Thread.sleep(2000);
		pricedep = dprice.stream().skip(d-1).findFirst().map(WebElement::getText)
		.map(text -> text.split(" ")).filter(parts -> parts.length > 1)
		.map(parts -> parts[1].split("per")[0].trim()).orElse("");
		System.out.println("The price of departure flight present at " + d + " is " + pricedep);
		}
		else {
			System.out.println("Only top 10 flights can be selected");
			driver.close();
		}
		return pricedep;
	}
	
	
	public String arrivalFlight(int a) throws InterruptedException
	{
		String pricearr = null;
		if(a>0 && a<=10)
		{
		action(driver);
		right.get(a-1).click();
		Thread.sleep(2000);
		pricearr = aprice.stream().skip(a-1).findFirst().map(WebElement::getText)
				.map(text -> text.split(" ")).filter(parts -> parts.length > 1)
				.map(parts -> parts[1].split("per")[0].trim()).orElse("");
		System.out.println("The price of arrival flight present at " + a + " is " + pricearr);
		}
		else {
			System.out.println("Only top 10 flights can be selected");
			driver.close();
		}
		return pricearr;
	}
	
	public static void priceCheck(String depprice, String arrprice)
	{
		int depamount = Integer.parseInt(depprice.replaceAll(",", ""));
		int arramount = Integer.parseInt(arrprice.replaceAll(",", ""));
		String stickydep = price.stream().skip(1).findFirst().map(WebElement::getText)
				.map(text->text.split(" ")).filter(parts->parts.length>1).map(parts->parts[1].trim()).orElse("");
		String stickyarr = price.stream().skip(3).findFirst().map(WebElement::getText)
				.map(text->text.split(" ")).filter(parts->parts.length>1).map(parts->parts[1].trim()).orElse("");
		int dep = Integer.parseInt(stickydep.replaceAll(",", ""));
		int arr = Integer.parseInt(stickyarr.replaceAll(",", ""));
		int total = dep + arr;
		String Final = finalprice.getText();
		String[] flightprice  = Final.split(" ");
		String value = flightprice[1].trim();
		int totalvalue = Integer.parseInt(value.replaceAll(",", ""));
		int discount = Discount(driver);
		if(discount!=0)
		{
			System.out.println("Your final value after applying discount of " + discount + " is " + totalvalue);
		}
		else
		{
			System.out.println("Your final value is " + totalvalue);
		}
		
	}
	public static int Discount(WebDriver driver) throws NumberFormatException
	{
		int discount = 0;
		try {
			if(discountamount.isDisplayed())
			{
				String disc = discountamount.getText();
				String[] disco = disc.split("₹");
				String discou = disco[1].trim();
				discount = Integer.parseInt(discou);
			}
		} catch (NoSuchElementException | StaleElementReferenceException | ElementNotInteractableException e) {
			// TODO Auto-generated catch block
		}
		return discount;
	}
	
	public void errorMessage()
	{
		try {
		     if(error.isDisplayed())
		      {
			    System.out.println(error.getText());
		      }
		}catch (NoSuchElementException | StaleElementReferenceException | ElementNotInteractableException e) {
			// TODO Auto-generated catch block
		}
	}
}
