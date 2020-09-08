package testcases;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;


public class LoginApplicationDataProvider extends base {

	public WebDriver driver;
	String altMessage;
	String actualTitle;
	String[][] loginInfo = new String[4][2];
	int x=0;
	int y=0;
	 
	@BeforeTest
	public void initialize() throws IOException {
		System.out.println("Before test start");
		driver = initializeDriver();
		System.out.println("Before test end");
		
	}

	@Test(dataProvider="getloginInfo")
	public void loginApplication(String username, String password) throws Exception 
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);		
		LoginPage lp = new LoginPage(driver);
		HomePage hp=new HomePage(driver);
		lp.userid().clear();
		lp.userid().sendKeys(username);
		lp.password().clear();
		lp.password().sendKeys(password);
		
			lp.login().click();	
		try
		{	
			Alert alt= driver.switchTo().alert();
			altMessage= alt.getText();
			alt.accept();
			Assert.assertEquals(errMessage, altMessage);	
		}
		
		catch(NoAlertPresentException e)
		{
			actualTitle= driver.getTitle();
			Assert.assertEquals(actualTitle, expTitle);
			String managerId= hp.mgrId().getText();
			String parts[]=managerId.split(pattern);
			String mgrIdDisp= parts[1].trim();			
			System.out.println("username:"+ username);
			System.out.println("mnagerid displayed:"+mgrIdDisp);			
			Assert.assertEquals(mgrIdDisp, username);									
		} 
		
	}
	
	@DataProvider()
	public String[][] getloginInfo() throws IOException {
			
		FileInputStream fis = new FileInputStream("testData.xlsx");
		XSSFWorkbook wk = new XSSFWorkbook(fis);
		XSSFSheet sheet=wk.getSheet("Data");
		Iterator<Row> rows = sheet.iterator(); //sheet.iterator();
		Row firstRow=rows.next(); // moving to first row
		Iterator<Cell> cells = firstRow.cellIterator();
		
		int startColumn=0;
		int k=0;
		while(cells.hasNext())
		{
			if (cells.next().getStringCellValue().equalsIgnoreCase("testData"))
			{
				startColumn= k;
			}
			k++;
		}
		int lastRow=sheet.getLastRowNum();		
		System.out.println("rowcount"+lastRow);
		while (rows.hasNext()) {
				Row r = rows.next();
				String username=r.getCell(startColumn+1).getStringCellValue();
				String password=r.getCell(startColumn+2).getStringCellValue();
				System.out.println(x);
				System.out.println(y);
				loginInfo[x][y]= username;
				loginInfo[x][y+1]=password;
				System.out.println(loginInfo[x][y]);
				System.out.println(loginInfo[x][y+1]);
				x=x+1;
								
				
		} // end of while
		
		return loginInfo;
		
	} // end of method
	
	
@AfterTest
	public void tearDown() throws Exception {
		driver.quit();
	} 
}// end of class