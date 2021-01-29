package userInterface;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
	private ArrayList<ArrayList<String>> data;
	private ArrayList<String> headers;
	
	public TableModel(ArrayList<ArrayList<String>> data, ArrayList<String> headers) {
		this.data = data;
		this.headers = headers;
	
	}
	
	@Override
	public String getColumnName(int column) {
	    return headers.get(column);
	}
	
	
	public void setData(ArrayList<ArrayList<String>> data){
		this.data = data;
		this.fireTableDataChanged();
	}
	
	public void setHeaders(ArrayList<String> headers) {
		this.headers = headers;
		this.fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		if (data.size() > 0) {
     		return data.get(0).size();
			
		}
		else {
			return 0;
		}
	}

	@Override
	public int getColumnCount() {

		// TODO Auto-generated method stub
		return headers.size();
	}
	
	public Object getValueAtByColumnString(int rowIndex, String columnHeader) {
		return getValueAt(rowIndex, headers.indexOf(columnHeader));
		
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data.get(columnIndex).get(rowIndex);
	}
	
	//Might implement later if we want to modify tables
    //public void setValueAt(String value, int row, int col) {
//        data.get(col).get(row);
//        fireTableCellUpdated(row, col);
//    }

}
