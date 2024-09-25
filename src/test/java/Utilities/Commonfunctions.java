package Utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class Commonfunctions extends TestBase {
	public static void ClickElement(WebElement element) {
		element.click();
	}
	public static void EnterText(String text,WebElement element) {
		element.sendKeys(text);
		
	}
	public static void HighlightWebElement(WebElement element) {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: red; border: 5px solid yellow;");
	}
	}
