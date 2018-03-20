package com.globant.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtils {

	public static WebElement waitUntilClickable (WebElement e, WebDriver driver) {
		return (new WebDriverWait(driver, 20)).until(ExpectedConditions.elementToBeClickable(e));
	}
	public static WebElement waitUntilClickable (By by, WebDriver driver) {
		return (new WebDriverWait(driver, 20)).until(ExpectedConditions.elementToBeClickable(by));
	}

}