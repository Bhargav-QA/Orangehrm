package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchMenuPage extends BasePage {
	
	public SearchMenuPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//input[@placeholder='Search']")
	WebElement search;
	
	@FindBy(xpath="//span[text()='Admin']")
	WebElement adminOption;
	
	@FindBy(xpath="//span[@class='oxd-topbar-header-breadcrumb']")
	WebElement Adminpage;
	
	
	public void setsearch(String module) {
		search.sendKeys(module);
	}
	
	public void selectmodule() {
		adminOption.click();
	}
	public void verifyadminpage() {
		Adminpage.isDisplayed();
	}
	
	
	

}
