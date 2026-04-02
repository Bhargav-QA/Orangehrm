package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.LoginPage;
import utilities.DataProviders;

public class Login_data_driven_TC_04 extends BaseTestCase{
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class)
	public void verify_login_DP(String email,String pwd,String exp) {
		
		try {
		logger.info("starting data drivern test case");
		LoginPage lp=new LoginPage(driver);
		lp.setOrgUserName(email);
		lp.setOrgPassword(pwd);
		lp.clickOrgLogin();
		boolean target=lp.clickOnOrgLogoutIcons();
		
		if(exp.equalsIgnoreCase("valid")) {
			if(target==true) {
				
				
				lp.clickOnOrgLogoutIcon();
				lp.clickOnOrgLogout();
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}
			
		}
		if(exp.equalsIgnoreCase("invalid")) {
			
			if(target==true) {
				lp.clickOnOrgLogout();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(false);
			}
		}}
		catch(Exception e){
			
			Assert.fail();
			
			}
		
		
		
		logger.info("test case completed");
	

}}
