package testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ObjectsRepo.LoginPage;
import Resources.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;


public class LoginApplicationParam extends base {

	public WebDriver driver;
	String altMessage;
	String[][] loginInfo = new String[4][2];
	int x=0;
	int y=0;
	 
	@BeforeTest
	public void initialize() throws IOException {
		System.out.println("Before test start");
		driver = initializeDriver();
		System.out.println("Before test end");
		
	}

	@Test
	public void loginApplication() throws IOException {
			
	
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
				System.out.println( "For username "+ username + " and password "+ password );
				//System.out.println(password);
				//prop.getProperty("url");
				
				driver.get("http://www.demo.guru99.com/V4/");		
				LoginPage lp = new LoginPage(driver);
				lp.userid().clear();
				lp.userid().sendKeys(username);
				lp.password().clear();
				lp.password().sendKeys(password);
				lp.login().click();				
				
				try
				{
					Alert alt= driver.switchTo().alert();
					altMessage= alt.getText();
					
					System.out.println("Alert Message:"+ altMessage);
					System.out.println("Error Message:"+ errMessage);
					
					alt.accept();
					
					
					if(altMessage.equalsIgnoreCase(errMessage))
					{
						System.out.println("row"+r.getRowNum()+"passed");	
					}
					else
					{
						System.out.println("row"+r.getRowNum()+"Failed");
					}
					
				}
				
				catch(NoAlertPresentException e)
				{
					String actualTitle= driver.getTitle();
					if(actualTitle.equalsIgnoreCase(expTitle))
					{
						System.out.println("row"+r.getRowNum()+"passed");
					}	
					else
					{
						
					System.out.println("row"+r.getRowNum()+"failed");	
					}
											
				}
				
				
		} // end of while
		
		System.out.println(loginInfo[0][0]);
		System.out.println(loginInfo[0][1]);
		System.out.println(loginInfo[1][0]);
		System.out.println(loginInfo[1][1]);
		System.out.println(loginInfo[2][0]);
		System.out.println(loginInfo[2][1]);
		driver.close();
	} // end of method
}// end of class