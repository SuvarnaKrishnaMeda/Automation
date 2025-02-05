package Script;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.AddtoCart;
import Pages.Loginpage;
import Utilities.EricssonDataProvider.StaticProviderMultipleSheetsHomePageContinueShoppingpage;
import Utilities.TestBase;

public class ValidateTotalPrice extends TestBase {
	public Loginpage lp1;
	public AddtoCart AddtoCartobj;

	@BeforeClass
	public void method() {
		try {
			parenttest = TestBase.extent.createTest("validate total price");
			driver = TestBase.LaunchUrl("https://www.saucedemo.com/", "chrome");
			AddtoCartobj = new AddtoCart();
			lp1 = new Loginpage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(dataProvider = "HomeAndShoppingPage", dataProviderClass = StaticProviderMultipleSheetsHomePageContinueShoppingpage.class)
	public void methodd(Map<String, String> Exceldata) {
		try {
			test = parenttest.createNode("price funtionality");
			System.out.println(configMap.get("Username") + "" + configMap.get("Password"));
			lp1.LoginFunctionality(configMap.get("Username"), configMap.get("Password"));
			System.out.println(Exceldata.get("HomePage_Product_names"));
			String[] iteamss = Exceldata.get("HomePage_Product_names").split(",");
			for (String item : iteamss) {
				AddtoCartobj.SelectItemsByUsingLoop(item);
			}			AddtoCartobj.Validatetotalprice(Exceldata.get("Error_message"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test(dataProvider="HomeAndShoppingPage",dataProviderClass = .class)

	@AfterClass
	public void method2() {
		lp1.Logout();
		extent.flush();
		driver.close();
	}

}
