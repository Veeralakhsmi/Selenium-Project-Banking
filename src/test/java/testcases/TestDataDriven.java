package testcases;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
public class TestDataDriven {

	@Test
	public void testDataDriven() throws IOException {
		// TODO Auto-generated method stub
TestDataDriven dd=new TestDataDriven();
		ArrayList result= dd.getData("update");
		System.out.println(result.get(0));
		System.out.println(result.get(1));
		System.out.println(result.get(2));
		System.out.println(result.get(3));
	}

	public ArrayList getData(String testname) throws IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\veera\\Selenium\\TestDataToDrive.xlsx");
		XSSFWorkbook wk = new XSSFWorkbook(fis);
		int sheets = wk.getNumberOfSheets(); // Get the number of sheets
		ArrayList al = new ArrayList();

	System.out.println(sheets);
		int column = 0;

		for (int i = 0; i < sheets; i++) // loop through the sheets to get the sheet name "testdata"
		{
			if (wk.getSheetName(i).equalsIgnoreCase("testdata")) {
				XSSFSheet sheet = wk.getSheetAt(i); // got the sheet testdata
				Iterator<Row> rows = sheet.iterator(); //
				
				Row firstrow = rows.next();
				Iterator<Cell> cells = firstrow.cellIterator();
				int k = 0;

				while (cells.hasNext()) {
					if (cells.next().getStringCellValue().equalsIgnoreCase("testcases")) {
						column = k;
					}
					k++;

				}

				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testname)) {
						Iterator<Cell> rowcells = r.cellIterator();
						while (rowcells.hasNext()) {
							Cell cv=rowcells.next();
							if(cv.getCellTypeEnum()==CellType.STRING)
								{
									al.add(cv.getStringCellValue());
								}
						if(cv.getCellTypeEnum()==CellType.NUMERIC)
							{
								al.add(cv.getNumericCellValue());
							}
						}
					}

				}
				
				
			}
		}
		System.out.println("Array size"+al.size());
		return al;
	}
}
