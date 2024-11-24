package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Dataprovider {

    @DataProvider(name = "Data")
    public String[][] getAllFData() throws IOException {
       // String path = System.getProperty("user.dir") + "//testData//userdata.xlsx";
        String path="C:\\Eclipse\\petstoreAutomation\\testData\\userdata.xlsx";
    	XLutility xl = new XLutility(path);
        int rownum = xl.getrowCount("Sheet1");
        int colcount = xl.getcolCount("Sheet1");
        String[][] apidata = new String[rownum][colcount];
        for (int i = 1; i <= rownum; i++) {
            for (int j = 0; j < colcount; j++) {
                apidata[i - 1][j] = xl.getCellData("Sheet1", i, j);
            }
        }
        return apidata;
    }

    @DataProvider(name = "Username")
    public String[] getUserNames() throws IOException {
       // String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
        String path="C:\\Eclipse\\petstoreAutomation\\testData\\userdata.xlsx";
    	XLutility xl = new XLutility(path);

        int rownum = xl.getrowCount("Sheet1"); // Correct method to get the number of rows
        String[] apidata = new String[rownum];
        for (int i = 1; i <= rownum; i++) {
            apidata[i - 1] = xl.getCellData("Sheet1", i, 1);
        }
        return apidata;
    }
}
