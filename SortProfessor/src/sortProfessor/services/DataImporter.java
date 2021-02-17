package sortProfessor.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataImporter {
	private ServiceManager serviceManager;
	
	public DataImporter(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}
	
	
	public void importAllData() {
		 boolean success = true;
		 ArrayList<ArrayList<String>> data = null;
		 data = importSheetData(0);
		 for (int i = 0; i < data.size(); i++) { //loop across the rows
			 ArrayList<String> rowData = data.get(i);
			 String username = rowData.get(0);
			 System.out.println(username);
			 String fName = rowData.get(1);
			 String lName = rowData.get(2);
			 String password = rowData.get(3);
			 success = serviceManager.register(username, password, fName, lName);
			 if (!success) {
				 System.out.println("failed to add student with username " + username);
			 }
		 }
		
	}
	
	public ArrayList<ArrayList<String>> importSheetData(int sheetNum) {

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File("./SortProfData.xlsx"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		//creating workbook instance that refers to .xls file  
		XSSFWorkbook wb = null;
		try {
			wb = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		//creating a Sheet object to retrieve the object  
		//use this to get each sheet per sproc
		XSSFSheet sheet=wb.getSheetAt(sheetNum);  
		//evaluating cell type   
		FormulaEvaluator formulaEvaluator=wb.getCreationHelper().createFormulaEvaluator();  
		boolean getHeader = true;
		ArrayList<String> header = new ArrayList<String>();
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		for(Row row: sheet) {    
			ArrayList<String> rowData = new ArrayList<String>();

			for(Cell cell: row) {   
				switch(formulaEvaluator.evaluateInCell(cell).getCellType())  {
				case Cell.CELL_TYPE_NUMERIC:
					rowData.add(Double.toString(cell.getNumericCellValue()));
					break;
				case Cell.CELL_TYPE_STRING:    //field that represents string cell type  
				//getting the value of the cell as a string  
				if (getHeader) {
					header.add(cell.getStringCellValue());
				}
				else {
					rowData.add(cell.getStringCellValue());
				}
				break;  
				}

			}
			if (!getHeader) {
				data.add(rowData);
			}
			getHeader = false;
		}
		
		for (int i = 0; i < header.size(); i++) {
			System.out.println(header.get(i));
		}
		

		//we don't really need the header
		return data;
	}
	
	


}
