package com.globant.automation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.PedidosYaAddToOrder;
import pageObjects.PedidosYaCheckout;
import pageObjects.PedidosYaConfirmLocation;
import pageObjects.PedidosYaCountryPage;
import pageObjects.PedidosYaFoodSuggestions;
import pageObjects.PedidosYaHome;
import pageObjects.PedidosYaOrderOverview;
import pageObjects.PedidosYaSignInForm;

public class PedidosYaOrderTest {
	public PedidosYaOrderTest() {
	}
	

	private static final Logger LOG;
	
	static {
		LOG = LogManager.getLogger(MercadoLibreOfertasTest.class);
	}
	
	protected static WebDriver driver;
	
	@Test
	public void pedidosYaTest() {
		String address = "Nicaragua 1666";
		String foodType = "milanesas";
		String email = "s.blancofretes@gmail.com";
		String password = "testing123";
		String name  = "Santiago"; //Nombre a validar
		
		driver.get("http://www.pedidosya.com/");
		driver.manage().window().maximize();
		
		PedidosYaCountryPage countryPage = new PedidosYaCountryPage(driver);
		PedidosYaHome home = countryPage.navigateToUyHome(driver);
		home.fillForm(address, foodType);
		PedidosYaConfirmLocation confirmLocation = home.navigateToConfirmLocation(driver);
		
		Assert.assertTrue(confirmLocation.confirmLocationPresence());
		
		PedidosYaFoodSuggestions suggestions = confirmLocation.navigateToFoodSuggestions(driver);
		PedidosYaAddToOrder addToOrder = suggestions.navigateToAddToOrder(driver);
		String price = addToOrder.getPrice(); //Almaceno el precio mostrado para validar posteriormente
		PedidosYaOrderOverview orderOverview = addToOrder.navigateToOrderOverview(driver);
		
		Assert.assertEquals(address,orderOverview.getAddress());
		Assert.assertEquals(price, orderOverview.getPrice());
		
		PedidosYaSignInForm signInForm = orderOverview.navigateToSignInForm();
		signInForm.fillLogInForm(email , password);
		PedidosYaCheckout checkout = signInForm.navigateToCheckout(driver);
		orderOverview = checkout.navigateToOrderOverview(driver);
		Assert.assertEquals(name,orderOverview.getLoginName());
		LOG.info(orderOverview.getLoginName());
		

	
	}
	
	@BeforeClass
	public void prepareClass() {
		WebDriverManager.chromedriver().setup();
	}
	
	@BeforeMethod
	public void prepareTest() {
		driver = new ChromeDriver();
	}
	@AfterMethod
	public void cleanUp() {
		driver.close();
	}
    
}