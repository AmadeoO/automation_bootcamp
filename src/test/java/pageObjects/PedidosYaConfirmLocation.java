package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.globant.automation.SeleniumUtils;

public class PedidosYaConfirmLocation {
	protected WebDriver driver;
	
	PedidosYaConfirmLocation(WebDriver driver){
		this.driver = driver;
		SeleniumUtils.waitUntilVisible(By.cssSelector(".gmnoprint>img"), driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "a#confirm.button")
	WebElement confirmButton;
	
	//WebElement confirmButton = mapContainer.findElement(By.cssSelector("a#confirm.button"));
	
	public Boolean confirmLocationPresence() {
		return confirmButton.isDisplayed();
	}
	
	public PedidosYaFoodSuggestions navigateToFoodSuggestions(WebDriver driver) {
		confirmButton.click();
		return new PedidosYaFoodSuggestions(driver);
	}
}