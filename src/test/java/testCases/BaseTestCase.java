package testCases;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTestCase {
	
	
	public static WebDriver driver;
	
	public Logger logger;
	
	public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws 	IOException {
		
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		
		switch(br.toLowerCase()) {
		case "chrome" :driver=new ChromeDriver();break;
		case "edge" :driver=new EdgeDriver();break;
		case "firefox" :driver=new FirefoxDriver();break;
		default : System.out.println("Invalid browser name");return;
		}
		
		
		
		
//		driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(p.getProperty("Appurl"));//reading url from properties file
//		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		
	}
	@AfterClass(groups= {"Sanity","Regression"})
	public void teardown() {
		driver.close();
		
	}
	
	public String randomestring()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}	
	
	public String randomeNumber()
	{
		String generateNumber=RandomStringUtils.randomNumeric(10);
		return generateNumber;
	}	
		
	public String randomealphanumerics()
	{
		String geneartedalphanumeric=RandomStringUtils.randomAlphanumeric(8);
		return geneartedalphanumeric;
	}
	
	public String captureScreen(String testName) throws IOException {
		
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "/Screenshots/" + testName +"_"+ timestamp+ ".png";
        File targetFile = new File(targetFilePath);

        FileUtils.copyFile(source, targetFile);

        return targetFilePath;
    }
	

}
