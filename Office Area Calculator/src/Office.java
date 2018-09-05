import helpers.StringHelpers;

public class Office {
	
	private int id = -1;
	private String roomName = "";
	private double length = 0;
	private double width = 0;
	
	public Office() {
		this(-1, "", 0, 0);
	}
	public Office(String name, double length, double width) {
		setRoomName(name);
		setLength(length);
		setWidth(width);
	}
	public Office(int id, String name, double length, double width) {
		setId(id);
		setRoomName(name);
		setLength(length);
		setWidth(width);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		if (!StringHelpers.IsNullOrEmpty(roomName))
			this.roomName = roomName;
		else
			this.roomName = "Not provided";
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		if (length >= OfficeData.getMIN() && length <= OfficeData.getMAX())
			this.length = length;
		else if (length < OfficeData.getMIN())
			this.length = OfficeData.getMIN();
		else
			this.length = OfficeData.getMAX();
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		
		if (width >= OfficeData.getMIN() && width <= OfficeData.getMAX())
			this.width = width;
		else if (width < OfficeData.getMIN())
			this.width = OfficeData.getMIN();
		else
			this.width = OfficeData.getMAX();
	}
	public double getArea() {
		return getLength() * getWidth();
	}
	public boolean Save() {
		boolean success = false;
		success = OfficeData.saveOfficeData(this);
		
		return success;
	}
}
