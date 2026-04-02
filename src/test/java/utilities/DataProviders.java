package utilities;

	import java.io.IOException;

	import org.testng.annotations.DataProvider;

	public class DataProviders {

	    // DataProvider for Login
	    @DataProvider(name = "LoginData")
	    public String[][] getLoginData() throws IOException {

	        String path = ".\\testData\\Test_Data.xlsx";

	        ExcelUtility xlutil = new ExcelUtility(path);

	        int totalrows = xlutil.getRowCount("Sheet1");
	        int totalcols = xlutil.getCellCount("Sheet1", 1);

	        String loginData[][] = new String[totalrows][totalcols];

	        for (int i = 1; i <= totalrows; i++) {
	            for (int j = 0; j < totalcols; j++) {
	                loginData[i - 1][j] = xlutil.getCellData("Sheet1", i, j);
	            }
	        }

	        return loginData;
	    }

	    
	    
	    // Another DataProvider example (for registration, search, etc.)
	    @DataProvider(name = "RegisterData")
	    public String[][] getRegisterData() throws IOException {

	        String path = System.getProperty("user.dir") + "\\testData\\Test_Data.xlsx";

	        ExcelUtility xlutil = new ExcelUtility(path);

	        int totalrows = xlutil.getRowCount("Sheet2");
	        int totalcols = xlutil.getCellCount("Sheet2", 1);

	        String data[][] = new String[totalrows][totalcols];

	        for (int i = 1; i <= totalrows; i++) {
	            for (int j = 0; j < totalcols; j++) {
	                data[i - 1][j] = xlutil.getCellData("Sheet2", i, j);
	            }
	        }

	        return data;
	    }
	}


