package Satya.AbstractComponent;

import java.time.Duration;
import java.util.stream.Collectors;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	
	WebDriver driver;
	JavascriptExecutor js;
	
	public AbstractComponent(WebDriver driver)
	{
		this.driver = driver;
		this.js = (JavascriptExecutor)driver;
		PageFactory.initElements(driver, this);
	}
	
	public String cityList(String city)
	{
		String s = null;
		List<WebElement> citylist = driver.findElements(By.cssSelector(".calc60 .blackText"));
		List<WebElement> list = citylist.stream().filter(cityl -> cityl.getText().contains(city)).collect(Collectors.toList());
		if(list.contains(city))
		{
			s = list.toString();
		}
		return s;
	}
	
    public static void slowScrollDown(JavascriptExecutor jsExecutor) throws InterruptedException 
    {	
		long height = (long) jsExecutor.executeScript("return document.body.scrollHeight");
	    long scrollAmount = 700;
	    long currentScrollPosition = (long) jsExecutor.executeScript("return window.scrollY;");
	    long currentheight = 0;
	    while(currentScrollPosition<=currentheight)
	    {
	    	Thread.sleep(100);
	    	currentScrollPosition += scrollAmount;
	    	jsExecutor.executeScript("window.scrollTo(0, " + currentScrollPosition + ");");
	    	currentheight = (long) jsExecutor.executeScript("return document.body.scrollHeight");	    	
	    }
	    long current = currentScrollPosition;
	    while(currentScrollPosition>=0)
	    {
	    	Thread.sleep(100);
	    	currentScrollPosition -= scrollAmount;
	    	jsExecutor.executeScript("window.scrollTo(" + current + ", " + currentScrollPosition + ");");
	    }
    }
	
    public static void action(WebDriver driver)
    {
    	Actions action = new Actions(driver);
		action.sendKeys(Keys.HOME).build().perform();
    }

	public void wait(WebElement FindBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(FindBy)));
	}
	
	public WebElement visibilityOfElementLocated(By FindBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
		
	}
	public List<WebElement> presenceOfAllElementsLocatedBy(By FindBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		List<WebElement> returndate = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(FindBy));
		return returndate;
	}
	
	public WebElement visibilityOfElement(By FindBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
		
	}
	public WebElement elementToBeClickable(WebElement FindBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		return wait.until(ExpectedConditions.elementToBeClickable(FindBy));
		
	}
	public void url()
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    wait.until(ExpectedConditions.urlContains("/flights/"));
	}

}
