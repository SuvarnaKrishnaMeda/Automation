package Script;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.AddtoCart;
import Pages.Loginpage;
import Utilities.TestBase;

public class ValidateLoginFunctionality extends TestBase {
	public Loginpage loginpageObj;
	public AddtoCart AddtoCartobj;

	@BeforeClass
	public void method() {

		try {
			parenttest = TestBase.extent.createTest("Items Adding to Cart");
			driver = TestBase.LaunchUrl("https://www.saucedemo.com/", "chrome");

			AddtoCartobj = new AddtoCart();
			loginpageObj = new Loginpage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(priority = 1)
	public void methodd() throws InterruptedException {
		test = parenttest.createNode("Login funtionality");
		System.out.println("login");
		loginpageObj.LoginFunctionality("standard_user", "secret_sauce");

	}

	@Test(priority = 2)
	public void addToCart() {
		try {
			test = parenttest.createNode("Add to cart functionality");
			String[] iteamss = { "Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket" };
			for (String each : iteamss) {
				AddtoCartobj.SelectItemsByUsingLoop(each);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void Endscript() {
		loginpageObj.Logout();
		extent.flush();
		driver.close();

	}
}
