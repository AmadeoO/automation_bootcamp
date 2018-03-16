package com.globant.automation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EngineCompare {

    public EngineCompare() {

    }

    private static final Logger LOG;
    private WebDriver driver;

    static {
        LOG = LogManager.getLogger(EngineCompare.class);
    }

	@DataProvider(name = "Search")
	public Object[][] provider() {
		return new Object[][] { { "Pink Floyd" }};
	}
	
    @Test(dataProvider = "Search")
    public void searchTest(String search) throws InterruptedException {
        LOG.info("Search test with dataProvider");
        driver.get("https://google.com");
        WebElement searchBar = driver.findElement(By.name("q"));
        searchBar.sendKeys(search);
        searchBar.submit();
        SeleniumUtils.waitUntilClickable(By.id("gsri_ok0"), driver);
        WebElement firstResult = driver.findElement(By.xpath("//div[@id='rso']//child::a[1]"));
        String link = firstResult.getAttribute("href");
        LOG.info(link);
        
        
    }

    @BeforeMethod
    public void prepareTest() {
        LOG.info("Prepare test");
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void cleanup() {
        driver.quit();
    }

    @BeforeClass
    private void prepareClass() {
        LOG.info("Prepare class");
        WebDriverManager.chromedriver().setup();
    }

}
