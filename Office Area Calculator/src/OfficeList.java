import java.util.ArrayList;

public class OfficeList {
	
	public static ArrayList<Office> List() {
		
		ArrayList<Office> theList = null;
		theList = OfficeData.readList();

		return theList;
	}
	
}
