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
	public int getRowCount() {

		
		// TODO Auto-generated method stub
		return data.get(0).size();
	}


	@Override
	public int getColumnCount() {

		// TODO Auto-generated method stub
		return headers.size();
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
