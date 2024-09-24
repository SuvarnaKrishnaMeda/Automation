package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import Utilities.Commonfunctions;
import Utilities.TestBase;

public class Loginpage extends TestBase {

	public Loginpage() {
		super();
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//input[@id='user-name']")
	public WebElement txt_username;

	@FindBy(how = How.XPATH, using = "//input[@id='login-button']")
	public WebElement login;

	@FindBy(how = How.XPATH, using = "//input[@id='password']")
	public WebElement txt_password;

	@FindBy(how = How.XPATH, using = "//a[text()='Logout']")
	public WebElement logout;

	@FindBy(how = How.XPATH, using = "//button[text()='Open Menu']")
	public WebElement button_menu;

	public void LoginFunctionality(String username, String password) throws InterruptedException {
		Thread.sleep(4000);
		Commonfunctions.EnterText(username, txt_username);
		test.log(Status.PASS, "Successfully entered username :" + username);
		Commonfunctions.EnterText(password, txt_password);
		test.log(Status.PASS, "Successfully entered password :" + password);
		Commonfunctions.ClickElement(login);
		test.log(Status.PASS, "Successfully clicked login button :" + login.getText());
	}

	public void Logout() {
		try {
			Commonfunctions.ClickElement(button_menu);
			Thread.sleep(4000);

			Commonfunctions.HighlightWebElement(logout);
			Thread.sleep(2000);
			Commonfunctions.ClickElement(logout);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}