package ObjectsRepo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;

public class LoginPage
{
		
		WebDriver driver;
		 public LoginPage(WebDriver driver)
		 {
			// this.driver=driver;
			 PageFactory.initElements(driver, this);
		 }
		 
		 @FindBy(xpath="//*[@name='uid']")
		 WebElement userid;
		 @FindBy(name="password")
		 WebElement password;
		 @FindBy(name="btnLogin")
		 WebElement login;
		
			 
		 public WebElement userid()
		 {
			 return userid;
		 }
		 public WebElement password()
		 {
			 return password;
		 }
		 public WebElement login()
		 {
			 return login;
		 }
		 

}
