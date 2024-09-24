package Network.Ericsson;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Hello world!
 *
 */
public class App 
{
	public static String path= System.getProperty("user.dir");
    public static void main( String[] args )
    {
    	System.setProperty("webdriver.chrome.driver",path +"\\driver\\chromedriver.exe");
        ChromeOptions co=new ChromeOptions();
        co.setExperimentalOption("debuggerAddress", "localhost:8888");
        ChromeDriver cd = new ChromeDriver(co);
       System.out.println(cd.getCurrentUrl()); 
     System.out.println(cd.findElement(By.xpath("//div[@class='inventory_item_description']")).getText());
    }
}
