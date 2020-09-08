package ObjectsRepo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;

public class HomePage
{
		
		WebDriver driver;
		 public HomePage(WebDriver driver)
		 {
			// this.driver=driver;
			 PageFactory.initElements(driver, this);
		 }
		 @FindBy(xpath="//td[contains(text(),'Manger Id')]")
		 WebElement MgrId;
		 
			 
		 public WebElement mgrId()
		 {
			 return MgrId;
		 }
		 
}
