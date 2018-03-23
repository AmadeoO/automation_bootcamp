package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonSearchPage {
	public AmazonSearchPage(WebDriver d) {
		driver = d;
		PageFactory.initElements(driver, this);
	}

	private WebDriver driver;

	@FindBy(css = "li[class='s-result-item celwidget']")
	private WebElement item;

	public AmazonItemPage clickFirstItem() {
		item.click();
		return new AmazonItemPage(driver);
	}

	// @FindBy(css = "li[class='s-result-item celwidget']")
	// private List<WebElement> itemList;
	//
	// public void clickFirstItem() {
	// for (int i = 0; i < itemList.size(); i++) {
	// String []temp = itemList.get(i).getAttribute("class").split(" ");
	// if (temp.length < 3) {
	// itemList.get(i).click();
	// return;
	// }
	// }
	// }
}
