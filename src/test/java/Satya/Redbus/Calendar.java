package Satya.Redbus;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Calendar {

	static WebDriver driver;
	public static void main(String[] args) throws InterruptedException {
	
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications"); //disable notifications
	    
		driver = new ChromeDriver(option);
		driver.manage().window().maximize(); //to maximize window
		driver.get("https://www.redbus.in/");
		
		List<String> weekends = getweekends("Mar 2024");//to get Weekends dates
		System.out.println(weekends);

	}
	
	public static List<String> getweekends(String mon)
	{
		driver.findElement(By.cssSelector("div[id='onwardCal']")).click(); // to click on date section
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); //WebDriverWait is used to wait for element to be visible
		
		//To get the month name and no of holidays
		String month = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".fxvMrr .iZpveD:nth-child(2)"))).getText();
		
		System.out.println(month);
		
		while(!(month.contains(mon)))
		{
			driver.findElement(By.cssSelector(".fxvMrr .iZpveD:nth-child(3)")).click(); // to click on next month
			month = driver.findElement(By.cssSelector(".fxvMrr .iZpveD:nth-child(2)")).getText(); // to store the month in month variable
			System.out.println(month);   //to print the update month value
		}
		
		List<WebElement> weekdays = driver.findElements(By.cssSelector(".bwoYtA")); // to store weekends dates
		
		List<String> weekends = new ArrayList<>();
		for(WebElement e:weekdays)
		{
			weekends.add(e.getText());  //to get the text and store in string list
		}
		
		int n = Integer.parseInt(weekends.get(0)); // to get the first element of list
		
		//This logic is to get the todays date. Try and catch is used because it will throw and error when we search for other month except present month
		try{
			if(driver.findElement(By.cssSelector(".fgdqFw")).isDisplayed())
		     {
		       String today = driver.findElement(By.cssSelector(".fgdqFw")).getText();
		       int t = Integer.parseInt(today); //to get today's date
		       //The logic below is to get the dates present on sunday or saturday. If someone run this code sat then n-1 condition will be true and if
		       //someone run this on Sunday then n-6 condition will be true to get present day date.
		       
		       if ((n - 6 == t) || (n - 1 == t)) 
		       {
			    weekends.add(today);
		       }
		     }
			Collections.sort(weekends); //used to sort the array
		}catch(NoSuchElementException e)
		{
			// TODO Auto-generated method stub
		}
		return weekends;
	}

}
