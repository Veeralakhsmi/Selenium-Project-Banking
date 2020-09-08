package testcases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ObjectsRepo.HomePage;
import ObjectsRepo.LoginPage;
import Resources.*;
import org.testng.Assert;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.apache.commons.io.FileUtils;

public class LoginApplicationScreenShot extends base {

	public WebDriver driver;
	String altMessage;
	String actualTitle;

	@BeforeTest
	public void initialize() throws IOException {
		
		System.out.println("Before test start");
		driver = initializeDriver();
		System.out.println("Before test end");
	}

	@Test(dataProvider = "loginInfo")
	public void loginApplication(String username, String password) throws Exception {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println(url);
		driver.get(url);
		LoginPage lp = new LoginPage(driver);
		HomePage hp = new HomePage(driver);
		lp.userid().clear();
		lp.userid().sendKeys(username);
		lp.password().clear();
		lp.password().sendKeys(password);

		TakesScreenshot SchShot = ((TakesScreenshot) driver);
		File source;

		try {
			lp.login().click();
			Alert alt = driver.switchTo().alert();
			altMessage = alt.getText();
			alt.accept();
			Assert.assertEquals(errMessage, altMessage);
		}
	
			catch(NoAlertPresentException e) {
				source = SchShot.getScreenshotAs(OutputType.FILE);
				System.out.println("Inside No alert");
				actualTitle = driver.getTitle();
				Assert.assertEquals(actualTitle, expTitle);
				String managerId = hp.mgrId().getText();
				String parts[] = managerId.split(pattern);
				String mgrIdDisp = parts[1].trim();
				System.out.println("username:" + username);
				System.out.println("mnagerid displayed:" + mgrIdDisp);
				Assert.assertEquals(mgrIdDisp, username);
				FileUtils.copyFile(source, new File("C:\\Users\\veera\\screenshotsuccess.png"));
			}
		}
	

	@DataProvider()
	public String[][] loginInfo() throws IOException {

		String[][] data = new String[4][2];
		data[0][0] = "mngr279461";
		data[0][1] = "ytedygA";
		data[1][0] = "mngr279461";
		data[1][1] = "ytedyg";
		data[2][0] = "mngr27946";
		data[2][1] = "ytedygA";
		data[3][0] = "mngr27946";
		data[3][1] = "ytedyg"; 

		return data;

	} // end of method

	@AfterTest
	public void tearDown() throws Exception {
		driver.quit();
	}
}// end of class