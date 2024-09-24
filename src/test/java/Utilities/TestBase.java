package Utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Pages.Loginpage;

public class TestBase {
	public static WebDriver driver = null;
	public static Properties pr;
	public static long PAGE_LOAD_TIMEOUT = 30;
	public static long IMPLICIT_WAIT = 30;
	public static ExtentHtmlReporter htmlreporter = null;
	public static ExtentReports extent = null;
	public static ExtentTest test = null;
	public static ExtentTest parenttest = null;
	public static WebDriverWait wait;

	public static File reportDirectery;
	public static String downloadFilepath = null;
	public static String pillType;
	public boolean isVerified = false;
	public static String path = System.getProperty("user.dir");

	public static WebDriver LaunchUrl(String url, String browser) throws IOException {
		if (browser.equalsIgnoreCase("chrome")) {

			// String path= System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver", path + "\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get(url);
		} else if (browser.equalsIgnoreCase("edge")) {
			// edgedriver code
		} else {
			System.out.println("no such driver to invoke");
		}
		return driver;
	}

	public static void Property() {
		try {
			pr = new Properties();
			FileReader reader = new FileReader(path + "\\resources\\config.properties");
			pr.load(reader);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@BeforeSuite()
	public void init(ITestContext context) throws FileNotFoundException, IOException {

		
		File destFile = null;

		try {
			destFile = new File(
					"results/" + "Ericson_Test_Automation_Results_" + UUID.randomUUID().toString() + ".html");
			System.out.println("I am a destination file");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// htmlreporter = new ExtentHtmlReporter("result/extentreport.html");

		htmlreporter = new ExtentHtmlReporter(destFile);
		htmlreporter.loadXMLConfig("resources/extent-config.xml");

		extent = new ExtentReports();
		extent.attachReporter(htmlreporter);

	}

	public static String getResourcePath(String path) {
		String basePath = System.getProperty("user.dir");
		System.out.println(basePath + "/" + path);
		return basePath + "/" + path;
	}

}
