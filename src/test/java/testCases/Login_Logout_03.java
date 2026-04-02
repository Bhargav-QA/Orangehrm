package testCases;

import org.testng.annotations.Test;

import pageObject.LoginPage;

public class Login_Logout_03 extends BaseTestCase{
	
		
		@Test(groups="Regression")
		public void verfy_login_logout() {
			
			logger.info("Test case started");
			LoginPage lp=new LoginPage(driver);
			lp.setOrgUserName("admin");
			lp.setOrgPassword("admin123");
			lp.clickOrgLogin();
			logger.info("Application logged in sucessfully");
			lp.clickOnOrgLogoutIcon();
			lp.clickOnOrgLogout();
			logger.info("Test Case completed");
			
		}

}
