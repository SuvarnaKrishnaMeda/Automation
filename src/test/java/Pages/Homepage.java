package Pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Utilities.Commonfunctions;
import Utilities.TestBase;

public class Homepage extends TestBase{
	public Homepage() {
		super();
		PageFactory.initElements(driver, this);
	}
	
@FindBy(how=How.XPATH,using="//div[text()='Swag Labs']")
public WebElement Swaglabs;

@FindBy(how=How.XPATH,using="//select[@class='product_sort_container']")
public WebElement SortContainer;

@FindBy(how=How.XPATH,using="//option[text()='Price (low to high)']")
public WebElement Filter;

@FindBy(how=How.XPATH,using="//span[text()='Name (A to Z)']")
public WebElement FilterAtoZ;

@FindBy(how=How.XPATH,using="//div[@data-test='inventory-item-price']")
public List<WebElement> Actual;

@FindBy(how=How.XPATH,using="//div[@data-test='inventory-item-name']")
public List<WebElement> HomePageProducts;

public void validatehomepage() {
	if(Swaglabs.isDisplayed()) {
		test.pass("Successfully logged in to HomePage");
	}
	else {
		test.fail("Not logged in to HomePage");
	}
}
public void Filter(WebElement option) throws InterruptedException {
	Commonfunctions.ClickElement(SortContainer);
	Thread.sleep(5000);
	Commonfunctions.ClickElement(option);
}
public void Comparetosort() {
	List<Float> Actual1=new ArrayList();
	List<Float> Expected=new ArrayList();
	for(WebElement wb: Actual) {
	Actual1.add(Float.parseFloat(wb.getText().substring(1)));
	Expected.add(Float.parseFloat( wb.getText().substring(1)));
	
	}
	Collections.sort(Expected);
	System.out.println("gfjvhu"+Actual1);
	System.out.println(Expected);
	if(Actual1.equals(Expected)) {
		
		test.pass("Prices are in sorted order");
	}
	else {
		
		test.fail("Prices are not in sorted order");
	}
	
}
public void ValidateAddTocart() throws InterruptedException {
	Filter(FilterAtoZ);
}	
}
