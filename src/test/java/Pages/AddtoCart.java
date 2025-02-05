package Pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[@class='inventory_item_description']")
	public List<WebElement> Items_select;

	@FindBy(how = How.XPATH, using = "//a[@data-test='shopping-cart-link']")
	public WebElement ShoppingCart;

	@FindBy(how = How.XPATH, using = "//div[@data-test='inventory-item']")
	public List<WebElement> Itemsaddedtocart;

	@FindBy(how = How.XPATH, using = "//button[@id='continue-shopping']")
	public WebElement Continueshopping;

	@FindBy(how = How.XPATH, using = "//div[@data-test='inventory-item-price']")
	public List<WebElement> Prices;

	@FindBy(how = How.XPATH, using = "//button[text()='Checkout']")
	public WebElement Checkout;

	@FindBy(how = How.XPATH, using = "//input[@data-test='firstName']")
	public WebElement FirstName;

	@FindBy(how = How.XPATH, using = "//input[@data-test='lastName']")
	public WebElement LastName;

	@FindBy(how = How.XPATH, using = "//input[@data-test='postalCode']")
	public WebElement PostalCode;

	@FindBy(how = How.XPATH, using = "//input[@data-test='continue']")
	public WebElement Continuebutton;

	@FindBy(how = How.XPATH, using = "//div[@data-test='subtotal-label']")
	public WebElement TotalPrice;
	
	@FindBy(how=How.XPATH, using="//h3[@data-test='error']")
	public WebElement Errorforcontinueshopping;

	public void ItemsAddToCart(String item) {
		try {
			WebElement Elements = driver.findElement(
					By.xpath("//div[text()='" + item + "']/ancestor::div[1]/following-sibling::div//button"));
			Commonfunctions.ClickElement(Elements);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void SelectItemsByUsingLoop(String item) {
		try {
			for (WebElement Items : Items_select) {
				System.out.println(Items.getText() + " " + item);
				if (Items.getText().contains(item)) {
					if (Items.getText().contains("Add to cart")) {
						System.out.println(item);
						Thread.sleep(2000);
						Items.findElement(By.xpath(".//button")).click();

						Thread.sleep(2000);

						if (Items.getText().contains("Remove")) {
							test.log(Status.PASS, "Successfully item was added to cart : " + item);
						} else {
							test.log(Status.FAIL, "Successfully item was not added to cart : " + item);
						}
					}

					else {

						test.log(Status.FAIL, "Already Item was added to cart : " + item);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ShoppingCart(int size) {
		int t1 = Integer.parseInt(ShoppingCart.getText());
		if (t1 == size) {
			test.log(Status.PASS, "Add to cart iteams and Shoppingcartlink items are matched : " + size);
		} else {
			test.log(Status.FAIL, "Add to cart iteams and Shoppingcartlink items are not matched");
		}

	}

	public void ValidateItemsaddedtocart(String cartitem) throws InterruptedException {
		boolean flag = false;
		Thread.sleep(2000);
		Commonfunctions.ClickElement(ShoppingCart);
		for (WebElement CartItems : Itemsaddedtocart) {
			flag = false;
			if (CartItems.getText().contains(cartitem)) {
				test.log(Status.PASS, "Itemsaddedtocart and items selected are matched: " + cartitem);
				flag = true;
				break;
			}

		}
		if (!flag) {

			test.log(Status.FAIL, "Itemsaddedtocart and items selected are not matched: " + cartitem);
		}

	}

	public void ValidateItemsremoved(String Cart) {
		try {
			int t3 = Integer.parseInt(ShoppingCart.getText());
			for (WebElement CartItems1 : Itemsaddedtocart) {
				if (CartItems1.getText().contains(Cart)) {
					if (CartItems1.getText().contains("Remove")) {
						CartItems1.findElement(By.xpath(".//button")).click();
						Thread.sleep(5000);
						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Itemsremovedfromcart(String Cart, int beforeCount) {
//		String afterCount = ShoppingCart.getText();
//		int t2 = afterCount == "" ? 0 : Integer.parseInt(afterCount);
//		if (beforeCount > t2) {
		List<WebElement> removedItem = driver.findElements(By.xpath("//div[text()='" + Cart + "']"));
		if (removedItem.size() == 0)
			test.log(Status.PASS, "Items removed from the cart successfully: " + Cart);
		else
			test.log(Status.FAIL, "Items are not removed from the cart: " + Cart);

	}

	public Float Validatepricesaddedandtotalamount() {
		
		Float expectedTotal =Float.parseFloat("0") ;
		
		try {
			Thread.sleep(2000);
			Commonfunctions.ClickElement(ShoppingCart);

//			Commonfunctions.ClickElement(ShoppingCart);
			for (WebElement price : Prices) {

				expectedTotal += Float.parseFloat(price.getText().substring(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return expectedTotal;
	}
//		for(int j=0;j<=Productprice.length;j++) {
//	
//	Productprice[j];
//		

//		}

	public void Validatetotalprice(String text) {
		try {
			Float expectedTotal = Validatepricesaddedandtotalamount();
			Commonfunctions.ClickElement(Checkout);
			Commonfunctions.EnterText("Bolisetty", FirstName);
			Commonfunctions.EnterText("Hethaswi", LastName);
			Commonfunctions.EnterText("502319", PostalCode);
			Commonfunctions.ClickElement(Continuebutton);
				if(Errorforcontinueshopping.getText().contains(text)) 
					test.log(Status.PASS, "Successfully Error is captured"+" "+text);
				else
					test.log(Status.FAIL," Error was not captured"+" "+text);
					
				
			
//			Commonfunctions.ClickElement(Continuebutton);

			Thread.sleep(2000);
System.out.println(TotalPrice.getText());
			Float actualTotal = Float.parseFloat(TotalPrice.getText().split("\\$")[1]);
			if (actualTotal == expectedTotal) {
				test.log(Status.PASS, "Both all items sum of price is matched with total price");

			} else {
				test.log(Status.FAIL, "Both all items sum of price is not matched with total price "+actualTotal +" iudncid"+expectedTotal);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
