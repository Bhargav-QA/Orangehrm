package testCases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObject.LoginPage;

public class LoginToOrangeHrm_01 extends BaseTestCase {
	
	@Test(groups="Sanity")
	public void verfylogin() {
		
		logger.info("Test case started");
		LoginPage lp=new LoginPage(driver);
		lp.setOrgUserName(p.getProperty("OrgUserName"));
		lp.setOrgPassword(p.getProperty("OrgPassword"));
		lp.clickOrgLogin();
		logger.info("Application logged in sucessfully");
		logger.info("Test Case completed");
		
	}}
	
	
//	@Parameters({"OrgUserName","OrgPassword"})
//	@Test(groups="Sanity")
//	public void verfylogin(String uname, String pwd) {
//	    
//	    logger.info("Test case started");
//
//	    LoginPage lp = new LoginPage(driver);
//
//	    // Use XML values instead of config
//	    lp.setOrgUserName(uname);
//	    lp.setOrgPassword(pwd);
//
//	    lp.clickOrgLogin();
//
//	    logger.info("Application logged in sucessfully");
//	    logger.info("Test Case completed");
//
//}}
