package Utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.iterators.EntrySetMapIterator;
import org.testng.annotations.DataProvider;

public class EricssonDataProvider {
	public static class StaticProviderLoginPage {
		@DataProvider(name = "loginPage", parallel = false)
		public static Iterator<Object[]> getdata() {
			Iterator<Object[]> testData = DPExcelUtil.getTestData("Input/TestData.xlsx", "LoginPage");
			return testData;
		}
	}

	public static class StaticProviderHomePage {
		@DataProvider(name = "homePage", parallel = false)
		public static Iterator<Object[]> getdata() {
			Iterator<Object[]> testData = DPExcelUtil.getTestData("Input/TestData.xlsx", "HomePage");
			return testData;
		}
	}

	public static class StaticProviderContinueShoppingPage {
		@DataProvider(name = "ContinueShoppingPage", parallel = false)
		public static Iterator<Object[]> getdata() {
			Iterator<Object[]> testData = DPExcelUtil.getTestData("Input/TestData.xlsx", "ContinueShoppingPage");
			return testData;
		}

	}

	public static class StaticProviderMultipleSheetsHomePageContinueShoppingpage {
		@DataProvider(name = "HomeAndShoppingPage", parallel = false)
		   public Iterator<Object[]> getData() throws IOException {
		        String filePath = "Input/TestData.xlsx";
		        
		        // Read both sheets in a single call
		        return DPExcelUtil.readAndMergeSheetswithOutKey(filePath, "ContinueShoppingPage", "HomePage");
		    }
	}

}
