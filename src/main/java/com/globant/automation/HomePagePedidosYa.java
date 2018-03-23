package com.globant.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePagePedidosYa {

	@FindBy(how= How.ID,using = "linkUy")
	private WebElement btnUruguay;
	
	 WebDriver driver;

	public HomePagePedidosYa(WebDriver driver) {
		this.driver = driver;
		if(!SeleniumUtils.isPresent(By.id("linkUy"), driver)) {
			throw new IllegalStateException("Button did not load.");
		}
		PageFactory.initElements(driver, this);		
	}
	
	
	public direYComida apretarBandera() {
		btnUruguay.click();
		return new direYComida(driver);
	}
}