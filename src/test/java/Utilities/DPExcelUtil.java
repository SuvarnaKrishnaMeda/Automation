package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DPExcelUtil {

public static Iterator<Object[]> getTestData(String strWorkbookPath, String strworksheetName) { 
	List<Object[]> data = new ArrayList<Object[]>();
	
	int rowCount = 0;
	String pathExtension = null;
	try {
		
		File filePath = new File(strWorkbookPath);
		pathExtension = getFileExtension(filePath);
		FileInputStream file = new FileInputStream(filePath); 
		Iterator<Row> rowIterator = null;
		if (pathExtension.equalsIgnoreCase("xls")) {
			// Get the workbook instance for XLS file 
			HSSFWorkbook workbook = new HSSFWorkbook (file);
		
		// Get first sheet from the workbook
		HSSFSheet sheet = workbook.getSheet(strworksheetName); 
		// Get iterator to all the rows in current sheet rowIterator = sheet.rowIterator();
	} else {

		// Get the workbook instance for XLSX file
		XSSFWorkbook workbook = new XSSFWorkbook (file);
	
		XSSFSheet sheet = workbook.getSheet (strworksheetName); 
		// Get iterator to all the rows in current sheet 
		rowIterator = sheet.rowIterator();
		
	}
		Row firstRow = rowIterator.next(); 
		//System.out.println(firstRow);
		
		Map<String, String> columnNamesMap = getColumnNames (firstRow);
		//System.out.println(columnNamesMap); 
		while (rowIterator.hasNext()) {
			Iterator<Cell> cellIterator = rowIterator.next().cellIterator();
			Map<String, String> rowMap = new LinkedHashMap<String, String>();
			
			for (Entry<?, ?> entry : columnNamesMap.entrySet()) {
				System.out.println();
				String strColumnName = entry.getKey().toString();
				String strValue = cellIterator.next().toString(); 
				if (!strValue.isEmpty() && strValue != null) {
					rowMap.put(strColumnName, strValue); 
					//System.out.println(rowMap);
				}
			}
			if(rowMap!=null&&!rowMap.isEmpty()){
				data.add(new Object[]{rowMap});
				}
		}
		file.close();
	}catch(Exception e){
		e.printStackTrace();
	}
		return data.iterator();
	}

public static Iterator<Object[]> getTestDataByScenario(String strWorkbookPath, String strWorksheetName,
		String strScenario) {
	List<Object[]> data = new ArrayList<Object[]>();

	int inRowCounter = 0;
	String pathExtension = null;
	try {
		File filePath = new File(strWorkbookPath); 
		pathExtension = getFileExtension(filePath);
		// pathExtension = "xlsx";
		FileInputStream file = new FileInputStream(filePath); 
		Iterator<Row> rowIterator = null;
		if (pathExtension.equalsIgnoreCase("xls")) {
			// Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheet (strWorksheetName); 
			// Get iterator to all the rows in current sheet 
			rowIterator = sheet.rowIterator();
		} else {
			// Get the workbook instance for XLSX file 
			XSSFWorkbook workbook = new XSSFWorkbook (file);
		
			// Get first sheet from the workbook
			XSSFSheet sheet = workbook.getSheet(strWorksheetName); 
			// Get iterator to all the rows in current sheet 
			rowIterator = sheet.rowIterator();
		}
			Row firstRow = rowIterator.next();
			Map<String, String> columnNamesMap = getColumnNames(firstRow);
			Object[] keys = columnNamesMap.keySet().toArray(); 
			String strValue = "";
			while (rowIterator.hasNext()) {
				// Iterator<org.apache.poi.ss.usermodel.Cell> 
				// cellIterator=rowIterator.next().cellIterator(); 
				Map<String, String> rowMap = new LinkedHashMap<String, String>();
				Row row = rowIterator.next();
				
				if ((row.getCell(0).getStringCellValue().equalsIgnoreCase(strScenario))) { 
					for (int j = 0; j < row.getLastCellNum(); j++){
						
						String strColumnName = columnNamesMap.get(keys[j].toString());
						if (row.getCell(j) != null) {
							strValue = row.getCell(j).toString();
						} else {
							strValue = "";
						}
						if (!strValue.isEmpty() && strValue != null) {
							rowMap.put(strColumnName, strValue);
						}
					}
				}
					
					if (rowMap != null && !rowMap.isEmpty()) {
						data.add(new Object[] { rowMap });
					}
				}
				
				file.close();
			} catch (Exception e) {
				e.printStackTrace();

			}
			return data.iterator();
	}

	private static Map<String, String> getColumnNames(Row row) {
		Map<String, String> columnNamesMap = new LinkedHashMap();
		
		Iterator<Cell> cells = row.cellIterator();
		
		while (cells.hasNext()) {
			String strColumnName = cells.next().toString();
			columnNamesMap.put(strColumnName,strColumnName);
		}
		
			return columnNamesMap;
	}

	private static String getFileExtension(File file) {
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) 
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
		}

	  public static Iterator<Object[]> readAndMergeSheets(String filePath, String commonKey, String... sheetNames) throws IOException {
	        FileInputStream file = new FileInputStream(filePath);
	        Workbook workbook = new XSSFWorkbook(file);
	        Map<String, Map<String, String>> mergedDataMap = new LinkedHashMap<>();

	        for (String sheetName : sheetNames) {
	            Sheet sheet = workbook.getSheet(sheetName);
	            if (sheet == null) continue;

	            // Read headers
	            Row headerRow = sheet.getRow(0);
	            int colCount = headerRow.getPhysicalNumberOfCells();
	            String[] headers = new String[colCount];

	            for (int j = 0; j < colCount; j++) {
	                headers[j] = headerRow.getCell(j).toString().trim();
	            }

	            // Read data
	            int rowCount = sheet.getPhysicalNumberOfRows();
	            for (int i = 1; i < rowCount; i++) {
	                Row row = sheet.getRow(i);
	                if (row == null) continue;

	                Map<String, String> rowData = new HashMap<>();
	                String key = ""; // Store common key value (like Product ID or Name)

	                for (int j = 0; j < colCount; j++) {
	                    Cell cell = row.getCell(j);
	                    String value = (cell != null) ? cell.toString().trim() : "";
	                    rowData.put(headers[j], value);

	                    if (headers[j].equalsIgnoreCase(commonKey)) { // Identify common key column
	                        key = value;
	                    }
	                }

	                if (!key.isEmpty()) {
	                    mergedDataMap.putIfAbsent(key, new HashMap<>());
	                    mergedDataMap.get(key).putAll(rowData); // Merge data
	                }
	            }
	        }

	        workbook.close();

	        // Convert merged data into Object[] for TestNG
	        List<Object[]> mergedDataList = new ArrayList<>();
	        for (Map<String, String> data : mergedDataMap.values()) {
	            mergedDataList.add(new Object[]{data});
	        }

	        return mergedDataList.iterator();
	    }
	  public static Iterator<Object[]> readAndMergeSheetswithOutKey(String filePath, String... sheetNames) throws IOException {
	        FileInputStream file = new FileInputStream(filePath);
	        Workbook workbook = new XSSFWorkbook(file);
	        List<Object[]> mergedDataList = new ArrayList<>();

	        // Create a single map to merge data from all sheets
	        Map<String, String> mergedRowData = new LinkedHashMap<>();

	        for (String sheetName : sheetNames) {
	            Sheet sheet = workbook.getSheet(sheetName);
	            if (sheet == null) continue;

	            // Read headers (first row)
	            Row headerRow = sheet.getRow(0);
	            int colCount = headerRow.getPhysicalNumberOfCells();
	            String[] headers = new String[colCount];

	            for (int j = 0; j < colCount; j++) {
	                headers[j] = headerRow.getCell(j).toString().trim();
	            }

	            // Read data rows
	            int rowCount = sheet.getPhysicalNumberOfRows();
	            for (int i = 1; i < rowCount; i++) { // Skip header row
	                Row row = sheet.getRow(i);
	                if (row == null) continue;

	                for (int j = 0; j < colCount; j++) {
	                    Cell cell = row.getCell(j);
	                    String value = (cell != null) ? cell.toString().trim() : "";

	                    // Store data in HashMap (avoid overwriting existing values)
	                    mergedRowData.putIfAbsent(headers[j], value);
	                }
	            }
	        }

	        workbook.close();

	        // Add merged data as a single test iteration
	        mergedDataList.add(new Object[]{new HashMap<>(mergedRowData)});
	        return mergedDataList.iterator();
	    }
}
