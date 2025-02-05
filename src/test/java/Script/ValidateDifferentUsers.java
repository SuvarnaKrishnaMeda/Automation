package Script;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.AddtoCart;
import Pages.Homepage;
import Pages.Loginpage;
import Utilities.EricssonDataProvider.StaticProviderLoginPage;
import Utilities.TestBase;

public class ValidateDifferentUsers extends TestBase{
	public Loginpage lp1;
	public Homepage hp;
	@BeforeClass
	public void method() {

		try {
			parenttest = TestBase.extent.createTest("Login with Different users");
			driver = TestBase.LaunchUrl("https://www.saucedemo.com/", "chrome");

			 lp1=new Loginpage();
			 hp=new Homepage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

@Test
public void method1() throws InterruptedException {
  test=parenttest.createNode("Login with standard user ");
   lp1.LoginFunctionality(configMap.get("Username"), configMap.get("Password"));
   hp.validatehomepage();
   hp.Filter(hp.FilterAtoZ);
   hp.Comparetosort();
}
@Test(dataProvider="loginPage",dataProviderClass = StaticProviderLoginPage.class)
public void method3(Map<String,String> Exceldata) throws InterruptedException {
	lp1.Logout();
	test=parenttest.createNode("Login with locked user ");
	lp1.LoginFunctionality(configMap.get("lockedUsername"), configMap.get("Password"));
	lp1.ErrorDisplay(Exceldata.get("lockedOutUser_ErrorMessage"));
}
@AfterClass
public void method2() {
	extent.flush();
	driver.close();
}
}
