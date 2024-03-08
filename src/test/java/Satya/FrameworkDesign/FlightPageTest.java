package Satya.FrameworkDesign;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Satya.TestComponents.BaseTest;
import Satya.pageobjects.FlightPage;

public class FlightPageTest extends BaseTest {
	
	@Test(priority=1)
	public void nofilter() throws InterruptedException, IOException
	{
		String menu = "Flights";
		String trip = "Round Trip";
		FlightPage flightpage = landingPage.searchFlight("Delhi", "Bengaluru",menu, trip);
		flightpage.errorMessage();
		flightpage.FlightList();
		flightpage.checkGroup();
		System.out.println("No of flight present in departure list without filter is " + flightpage.departureList());
		System.out.println("No of flight present in departure list without filter is " + flightpage.arrivalList());
		
	}
	
	@Test(priority=2)
	public void nonstopfilter() throws InterruptedException, IOException
	{
		String menu = "Flights";
		String trip = "Round Trip";
		FlightPage flightpage = landingPage.searchFlight("Delhi", "Bengaluru",menu, trip);
		flightpage.errorMessage();
		flightpage.FlightList();
		flightpage.NonStop();
		System.out.println("No of Non-Stop flight present in departure list is " + flightpage.departureList());
		System.out.println("No of Non-Stop flight present in departure list is " + flightpage.arrivalList());
	}
	
	@Test(priority=3)
	public void onestopfilter() throws InterruptedException, IOException
	{
		String menu = "Flights";
		String trip = "Round Trip";
		FlightPage flightpage = landingPage.searchFlight("Delhi", "Bengaluru",menu, trip);
		flightpage.errorMessage();
		flightpage.FlightList();
		flightpage.OneStop();
		System.out.println("No of One-Stop flight present in departure list is " + flightpage.departureList());
		System.out.println("No of One-Stop flight present in departure list is " + flightpage.arrivalList());
	}
	
	
	/*public void selectNonStopFlight() throws InterruptedException
	{
		String menu = "Flights";
		String trip = "Round Trip";
		FlightPage flightpage = landingPage.searchFlight("Delhi", "Bengaluru",menu, trip);
		flightpage.FlightList();
		flightpage.NonStop();
		String depprice = flightpage.departureFlight(2);
		String arrprice = flightpage.arrivalFlight(2);
		flightpage.priceCheck(depprice, arrprice);
		
	}
	
	public void selectOneStopFlight() throws InterruptedException
	{
		String menu = "Flights";
		String trip = "Round Trip";
		FlightPage flightpage = landingPage.searchFlight("Delhi", "Bengaluru",menu, trip);
		flightpage.FlightList();
		flightpage.OneStop();
		String depprice = flightpage.departureFlight(5);
		String arrprice = flightpage.arrivalFlight(2);
		flightpage.priceCheck(depprice, arrprice);
		
	}*/
	
	
	@Test(priority=4, dataProvider="getData")
	public void selectFlightwithoutFilter(int dep, int arr) throws InterruptedException, IOException
	{
		String menu = "Flights";
		String trip = "Round Trip";
		FlightPage flightpage = landingPage.searchFlight("Delhi", "Bengaluru",menu, trip);
		flightpage.errorMessage();
		flightpage.FlightList();
		flightpage.checkGroup();
		System.out.println("No of flight present in departure list without filter is " + flightpage.departureList());
		System.out.println("No of flight present in departure list without filter is " + flightpage.arrivalList());
		String depprice = flightpage.departureFlight(dep);
		String arrprice = flightpage.arrivalFlight(arr);
		flightpage.priceCheck(depprice, arrprice);
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		return new Object[][] {{5,3}, {10,9}, {12,11}};
	}
		
}
