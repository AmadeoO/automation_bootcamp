package com.PedidosYa;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.PageFactory;

public class PopUpLogin {

	protected WebDriver driver;
	public PopUpLogin(WebDriver driver) {
		
		
		this.driver=driver;
		PageFactory.initElements(driver,this);
		SeleniumUtils.waitUntilPresence(By.xpath("//h1"), driver);

	
		
	}
	
	
	
   
	
	
}
