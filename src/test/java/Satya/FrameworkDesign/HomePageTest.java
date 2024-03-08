package Satya.FrameworkDesign;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Satya.TestComponents.BaseTest;
import Satya.pageobjects.FlightPage;

public class HomePageTest extends BaseTest {
	
	@Test(priority=1)
	public void flightSearch() throws IOException, InterruptedException
	{
		String menu = "Flights";
		String trip = "Round Trip";
		landingPage.searchFlight("Delhi", "Bengaluru",menu, trip);
	}
	
	@Test(priority=2)
	public void HotelSearch() throws InterruptedException
	{
		String menu ="Hotels";
		landingPage.selectMenu(menu);
	}
	
	@Test(priority=3)
	public void selectTrip() throws InterruptedException
	{
		String menu ="Flights";
		landingPage.selectMenu(menu);
		landingPage.Trip("Multi City");
	}
	
}

