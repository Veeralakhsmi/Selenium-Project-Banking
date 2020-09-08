package Resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class base {

	public String  expTitle="Guru99 Bank Manager HomePage";
	public String  errMessage="User or Password is not valid";
	public static final String pattern=":";
	public static final String  first_pattern="mngr";
	public static final String second_pattern="";	
	public Properties prop;
	public WebDriver driver;
	public String url;
	
	@SuppressWarnings("deprecation")
	@Test
	public WebDriver initializeDriver() throws IOException
	{
		prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\veera\\eclipse-workspace\\Banking\\src\\main\\java\\Resources\\data.properties");
		prop.load(fis);
		String browser=prop.getProperty("browser");
		url=prop.getProperty("url");
		String firefox_path=prop.getProperty("firefox_path");
		String chrome_path=prop.getProperty("chrome_path");
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
		//d = new FirefoxDriver(dc);
		
		if(browser.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", chrome_path);
			driver=new ChromeDriver(dc);
			System.out.println("inside chrome if");
			
		}
		else if(browser.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",firefox_path);
			driver = new FirefoxDriver();
			
		}
		
		
		
		return driver;
	}
}
