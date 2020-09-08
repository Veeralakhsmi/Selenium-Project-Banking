package testcases;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Resources.*;
import org.testng.Assert;
import ObjectsRepo.LoginPage;

public class LoginApplication extends base {

	public WebDriver driver;
	 String  expTitle="Guru99 Bank Manager HomePage";
	
	@BeforeTest
	public void initialize() throws IOException {
		System.out.println("Before test start");
		driver = initializeDriver();
		System.out.println("Before test end");
		
	}

	@Test
	public void loginApplication() {
		
		driver.get("http://www.demo.guru99.com/V4/");
		LoginPage lp = new LoginPage(driver);
		lp.userid().sendKeys("mngr279461");
		lp.password().sendKeys("ytedygA");
		lp.login().click();
		String actualTitle= driver.getTitle();
		Assert.assertEquals(actualTitle, expTitle);
		//System.out.println(actualTitle);
	}
}