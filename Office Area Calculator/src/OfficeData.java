import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import helpers.DatabaseHelpers;
import helpers.FileHelpers;
import helpers.OutputHelpers;

public class OfficeData {
	
	private static int DEFAULT_MIN = 5;
	private static int DEFAULT_MAX = 100 ;
	private static int MIN = DEFAULT_MIN;
	private static int MAX = DEFAULT_MAX;
		
	public static int getMIN() {
		return MIN;
	}

	public static void setMIN(int mIN) {
		MIN = mIN;
	}

	public static int getMAX() {
		return MAX;
	}

	public static void setMAX(int mAX) {
		MAX = mAX;
	}	
	public static void retrieveConfiguration() {
		PreparedStatement statement = null;
		StringBuilder sqlStr = new StringBuilder();
		ResultSet resultSet;
		Boolean success = false;
		try {
			//open the database connection
			success = openDataBaseConnection();
			if(success) {
				sqlStr.append("SELECT * FROM Configuration");
				statement = DatabaseHelpers.getConnection().prepareStatement(sqlStr.toString());
				resultSet = statement.executeQuery();
				if (resultSet.next()) {
					setMIN(resultSet.getInt(2));
					setMAX(resultSet.getInt(3));
				}
			}
			else {
				setMIN(DEFAULT_MIN);
				setMAX(DEFAULT_MAX);
			}
		}
		catch  (SQLException e) {
			String prompt = e.getMessage() + " cannot open configuration data";
			setMIN(DEFAULT_MIN);
			setMAX(DEFAULT_MAX);
			OutputHelpers.showExceptionDialog(prompt, "SQL Server Error");
		}
		finally {
			closeDataBaseConnection();
		}
	}
	
	public static boolean saveOfficeData(Office officeData) {
		Boolean success = false;
		PreparedStatement statement = null;
		StringBuilder sqlStr = new StringBuilder();
		
		try {
			success = openDataBaseConnection();
			if(success)
			{
				//build the SQL query string
				sqlStr.append("INSERT INTO ");
				sqlStr.append("Office");
				sqlStr.append(" (Name, Length, Width)");
				sqlStr.append(" values(?, ?, ?)");
				
				//get the connection and set the statement to connect to the database/table
				statement = DatabaseHelpers.getConnection().prepareStatement(sqlStr.toString());
				statement.setString(1, officeData.getRoomName());
				statement.setDouble(2, officeData.getLength());
				statement.setDouble(3, officeData.getWidth());
				
				//execute the query
				statement.executeUpdate();
				
			}
			//save to file as a redundancy
			writeRecord(officeData);
			success = true;
		}
		catch  (SQLException e) {
			//something went wrong with database, open up backup file
			writeRecord(officeData);
			success = false;
		}
		finally {
			closeDataBaseConnection();
		}
		return success;
	}
	
	public static ArrayList<Office> readList() {
		ArrayList<Office> dataList = null;
		PreparedStatement statement = null;
		StringBuilder sqlStr = new StringBuilder();
		ResultSet resultSet;
		boolean success = false;
		int id;
		String name;
		double length;
		double width;
		
		try {
			success = openDataBaseConnection();
			if(success) {
				sqlStr.append("SELECT * FROM Office");
				statement = DatabaseHelpers.getConnection().prepareStatement(sqlStr.toString());
				resultSet = statement.executeQuery();
				dataList = new ArrayList<Office>();
				if (resultSet.next()) {
					id = resultSet.getInt("Id");
					name = resultSet.getString("Name");
					length = resultSet.getDouble("Length");
					width = resultSet.getDouble("Width");
					dataList.add(new Office(id, name, length, width));
				}
			}
			else {
				//something went wrong, read the backup data
				dataList = readRecords();
			}
		}
		catch  (SQLException e) {
			dataList = null;
			dataList = readRecords();
		}
		finally {
			closeDataBaseConnection();
		}
		return dataList;
	}
	
	private static boolean openDataBaseConnection()
	{
		Boolean success = false;
		success = DatabaseHelpers.setConnection("1009", "devry_1234");
		return success;
	}
	private static void closeDataBaseConnection()
	{
		try {
			DatabaseHelpers.closeConnection();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//object serialization
	public static void writeRecord(Office officeData) {
		try {
			StringBuilder str = new StringBuilder();
			str.append(officeData.getId() + ",");
			str.append(officeData.getRoomName() + ",");
			str.append(officeData.getLength() + ",");
			str.append(officeData.getWidth());
			FileHelpers.writeData(str.toString(), "officeData.txt");
		}
		catch (Exception ex) {
			String prompt = "Office Data WriteRecord: cannot write to file\n";
			OutputHelpers.showConsole(prompt);
		}
	}
	public static ArrayList<Office> readRecords() {
		
		int id = -1;
		String name ="";
		double length = 0 ;
		double width = 0 ;
		ArrayList<Office> theList = new ArrayList<Office>();
		
		String deliminator = "[,]";
		StringTokenizer row;
		try {
			ArrayList<String> listString = FileHelpers.readList("officeData.txt");
			for(String str : listString) {
				row = new StringTokenizer(str, deliminator);
				id = Integer.parseInt(row.nextToken());
				name = row.nextToken();
				length = Double.parseDouble(row.nextToken());
				width = Double.parseDouble(row.nextToken());
				theList.add(new Office(id, name, length, width));
			}
		}
		catch (Exception ex) {
			String prompt = "Office Data read records: cannot read file\n";
			OutputHelpers.showConsole(prompt);
		}
		return theList;
	}
}
