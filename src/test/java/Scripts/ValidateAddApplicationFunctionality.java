package Scripts;

import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.AddApplicationPage;
import Pages.LoginPage;
import Utilities.GrowerDataProvider.StaticProviderAddApplicationPage;
import Utilities.GrowerDataProvider.StaticProviderLoginPage;
import Utilities.TestBase;


public class ValidateAddApplicationFunctionality extends TestBase {
	AddApplicationPage addapplicationpage;
	LoginPage loginpage;

	@BeforeClass()
	public void method() {

		parenttest = TestBase.extent.createTest("add application Functionality");
		driver = TestBase.launchURL(TestBase.configMap.get("browser"), TestBase.configMap.get("ApplicationURL"));
		addapplicationpage = new AddApplicationPage();
		loginpage = new LoginPage();

	}

	@Test(priority = 1)
	public void validatelogin() {
		TestBase.test = parenttest.createNode("Validating with the provided credentials");
		loginpage.loginmethod(TestBase.configMap.get("UserName"), TestBase.configMap.get("Password"));
	}

	@Test(dataProvider = "AddApplication", dataProviderClass = StaticProviderAddApplicationPage.class,priority = 2)
	public void validateaddapplication(Map<String, String> dataMap) {
		TestBase.test = parenttest.createNode("Validating add application functionality");
		addapplicationpage.addApplicationmethod(dataMap);
	}

	@Test(dataProvider = "AddApplication", dataProviderClass = StaticProviderAddApplicationPage.class,priority = 3)
	public void validatefarmandTractDetails(Map<String, String> dataMap) {
		TestBase.test = parenttest.createNode("Validating add application functionality");
		addapplicationpage.farmandTractDetailsmethod(dataMap);
	}

	@Test(dataProvider = "AddApplication", dataProviderClass = StaticProviderAddApplicationPage.class,priority=4)
	public void validategetPositionofTheHeader(Map<String, String> dataMap) {
		TestBase.test = parenttest.createNode("Validating add application functionality");
		// addapplicationpage.getPositionofTheHeader(dataMap);
	}

	@Test(dataProvider = "AddApplication", dataProviderClass = StaticProviderAddApplicationPage.class,priority = 5)
	public void validatecommodityInformation(Map<String, String> dataMap) {
		TestBase.test = parenttest.createNode("Validating add application functionality");
		addapplicationpage.commodityInformationmethod(dataMap);
	}

	@Test(dataProvider = "AddApplication", dataProviderClass = StaticProviderAddApplicationPage.class,priority = 6)
	public void validateliveStockInformation(Map<String, String> dataMap) {
		TestBase.test = parenttest.createNode("Validating add application functionality");
		addapplicationpage.liveStockInformationmethod(dataMap);
	}

	@Test(dataProvider = "AddApplication", dataProviderClass = StaticProviderAddApplicationPage.class,priority = 7)
	public void validateforestInformation(Map<String, String> dataMap) {
		TestBase.test = parenttest.createNode("Validating add application functionality");
		addapplicationpage.forestInformationmethod(dataMap);
	}

	@AfterClass()
	public void cleanup() {
		TestBase.extent.flush();
		driver.quit();
	}
}