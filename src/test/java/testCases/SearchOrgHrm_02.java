package testCases;

import org.testng.annotations.Test;

import pageObject.LoginPage;
import pageObject.SearchMenuPage;

public class SearchOrgHrm_02 extends BaseTestCase {
	
	@Test(groups="Regression")
	public void verifySerch() {
		logger.info("starting the test case 02");
		LoginPage lp=new LoginPage(driver);
		lp.setOrgUserName(p.getProperty("OrgUserName"));
		lp.setOrgPassword(p.getProperty("OrgPassword"));
		lp.clickOrgLogin();
		SearchMenuPage sch=new SearchMenuPage(driver);
		sch.setsearch(p.getProperty("OrgSerch"));
		sch.selectmodule();
		sch.verifyadminpage();
	}

}
