package pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        
    }

    @FindBy(name = "username")
     WebElement username;

    @FindBy(name = "password")
     WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
     WebElement loginButton;

    @FindBy(xpath = "//div[@class='orangehrm-login-form']//p[contains(@class,'oxd-alert-content-text')]")
     WebElement invalidCredentials;

    @FindBy(xpath = "//span[@class='oxd-userdropdown-tab']//i")
     WebElement logoutIcon;

    @FindBy(xpath = "//a[text()='Logout']")
     WebElement logout;

    
    // Login actions
    public void setOrgUserName(String uname) {
        username.sendKeys(uname);
    }

    public void setOrgPassword(String pwd) {
        password.sendKeys(pwd);
    }

    public void clickOrgLogin() {
        loginButton.click();
    }

    public boolean isErrorMessageDisplayed() {
            return invalidCredentials.isDisplayed();
    }

    public String getToasterMessage() {
        return invalidCredentials.getText();
    }


    public void clickOnOrgLogoutIcon() {
        logoutIcon.click();
    }

    public void clickOnOrgLogout() {
        logout.click();
    }
    
    public boolean clickOnOrgLogoutIcons() {
        return logoutIcon.isDisplayed();
    }
}