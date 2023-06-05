package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class ExcelDataProvider {

//    @DataProvider(name = "Data")
//    public Object[][] getAllData() throws IOException {
//
//        String path = System.getProperty("user.dir") + "//testData//UserData.xlsx";
//        Object[][] apiData = null;
//        try (XLUtility xl = new XLUtility(path)) {
//
//            int rowCount = xl.getRowCount("Sheet1");
//            int cellCount = xl.getCellCount("Sheet1", 1);
//            Object[][] apiData = new String[rowCount][cellCount];
//
//            for (int i = 1; i <= rowCount; i++) {
//                for (int j = 1; j <= cellCount; j++) {
//                    apiData[i - 1][j - 1] = xl.getCellData("Sheet1", i, j);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return apiData;
//    }


    @DataProvider(name = "AllData")
    public static Object[][] getAllDataRows() {
        String path = System.getProperty("user.dir") + "//testData//UserData.xlsx";
        Object[][] apiData = null;
        XLUtility xl = null;

        try {
            xl = new XLUtility(path);
            String sheetName = "Sheet1";
            int rowCount = xl.getRowCount(sheetName);
            int cellCount = xl.getCellCount(sheetName, 1);
            apiData = new Object[rowCount][cellCount];

            for (int i = 1; i <= rowCount; i++) {
                for (int j = 0; j < cellCount; j++) {
                    apiData[i - 1][j] = xl.getCellData(sheetName, i, j);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return apiData;
    }


//    @DataProvider(name = "UserNames")
//    public Object[][] getUserNames() {
//        String path = System.getProperty("user.dir") + "//testData//UserData.xlsx";
//        List<Object[]> apiDataList = new ArrayList<>();
//        XLUtility xl = null;
//
//        try {
//            xl = new XLUtility(path);
//            String sheetName = "Sheet1";
//            int rowCount = xl.getRowCount(sheetName);
//
//            for (int i = 1; i <= rowCount; i++) {
//                Object[] rowData = { xl.getCellData(sheetName, i, 1) };
//                apiDataList.add(rowData);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return apiDataList.iterator();
//    }

    @DataProvider(name = "UserNames")
    public Object[][] getUserNames() {
        String path = System.getProperty("user.dir") + "//testData//UserData.xlsx";
        Object[][] apiData = null;

        try {
            XLUtility xl = new XLUtility(path);
            String sheetName = "Sheet1";
            int rowCount = xl.getRowCount(sheetName);
            apiData = new Object[rowCount][1];

            for (int i = 1; i <= rowCount; i++) {
                apiData[i-1][0] = xl.getCellData(sheetName, i, 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return apiData;
    }
}
