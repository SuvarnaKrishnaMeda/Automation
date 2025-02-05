package Script;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.AddtoCart;
import Pages.Homepage;
import Utilities.TestBase;

public class ValidateHomePage extends TestBase {
	public Homepage hp;
	@BeforeClass
	public void method() {

		try {
			parenttest = TestBase.extent.createTest("validation home page");
			driver = TestBase.LaunchUrl("https://www.saucedemo.com/", "chrome");
            hp=new Homepage();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void method1() {
	test = parenttest.createNode("homepage funtionality");
	hp.validatehomepage();
	
	}
}
