package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import Utilities.Commonfunctions;
import Utilities.TestBase;

public class AddtoCart extends TestBase {
	public AddtoCart() {
		super();
		PageFactory.initElements(driver,this);
	}
   @FindBy(how=How.XPATH,using="//div[@class='inventory_item_description']")
   public List<WebElement> Items_select;
	
	public void ItemsAddToCart(String item) {
		try {
	WebElement Elements = driver.findElement(By.xpath("//div[text()='"+item+"']/ancestor::div[1]/following-sibling::div//button"));
		Commonfunctions.ClickElement(Elements);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void SelectItemsByUsingLoop(String item) {
		try {
			for(WebElement Items: Items_select) {
				if(Items.getText().contains(item)) {
					Items.findElement(By.xpath(".//button")).click();
					test.log(Status.PASS, "Successfully item was added to cart");
					Thread.sleep(2000);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
