package Script;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.AddtoCart;
import Pages.Loginpage;
import Utilities.Commonfunctions;
import Utilities.TestBase;
import Utilities.EricssonDataProvider.StaticProviderLoginPage;
import Utilities.EricssonDataProvider.StaticProviderLoginPage.StaticProviderHomePage;

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
		loginpageObj.LoginFunctionality(pr.getProperty("Username"), pr.getProperty("Password"));

	}

	@Test(dataProvider = "homePage",dataProviderClass = StaticProviderHomePage.class, priority = 2)
	public void addToCart(Map<String,String> Exceldata) {
		try {
			test = parenttest.createNode("Add to cart functionality");
			
		String[] iteamss = Exceldata.get("HomePage_Product_names").split(",");
		System.out.println(Arrays.asList(iteamss));
			for (String each : iteamss ) {
			
				AddtoCartobj.SelectItemsByUsingLoop(each);
				
				
			}
			AddtoCartobj.ShoppingCart(iteamss.length);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(dataProvider = "homePage",dataProviderClass = StaticProviderHomePage.class, priority = 3)
	public void Itemssuccefullyaddedtocart(Map<String,String> Exceldata) throws InterruptedException {
		test=parenttest.createNode("Items added to cart successfully");
		String[] iteamss1 = Exceldata.get("HomePage_Product_names").split(",");
		for(String s:iteamss1) {
			AddtoCartobj.ValidateItemsaddedtocart(s);
		}
		test=parenttest.createNode("Items added to cart are removed successfully");
		String[] iteamss2 = Exceldata.get("Remove_cart_items").split(",");
		for(String s1:iteamss2) {
		AddtoCartobj.ValidateItemsremoved(s1);
	}
		
		Commonfunctions.ClickElement(AddtoCartobj.Continueshopping);
		
	}
	

	@Test(dataProvider = "homePage",dataProviderClass = StaticProviderHomePage.class, priority = 4)
	public void addToCart2(Map<String,String> Exceldata) {
		try {
			test = parenttest.createNode("Add to cart functionality");
			
		String[] iteamss = Exceldata.get("HomePage_Product_names").split(",");
		System.out.println(Arrays.asList(iteamss));
			for (String each : iteamss ) {
			
				AddtoCartobj.SelectItemsByUsingLoop(each);
				
				
			}
			AddtoCartobj.ShoppingCart(iteamss.length);
			
			
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
