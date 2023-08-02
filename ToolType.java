// Tooltype enum that is based on the spec document provided
public enum ToolType {
	Ladder("LADDER", 1.99, "Yes", "Yes", "No"),
	Chainsaw("CHAINSAW", 1.49, "Yes", "No", "Yes"),
	Jackhammer("JACKHAMMER", 2.99, "Yes", "No", "No");
	
	private String name;
	private double dailyCharge;
	private String weekDayCharge;
	private String weekendCharge;
	private String holidayCharge;


	ToolType(String name, double dailyCharge, String weekDayCharge, String weekendCharge, String holidayCharge) {
		this.name = name;
		this.dailyCharge = dailyCharge;
		this.weekDayCharge = weekDayCharge;
		this.weekendCharge = weekendCharge;
		this.holidayCharge = holidayCharge;
	}


	public String getName() {
		return name;
	}


	public double getDailyCharge() {
		return dailyCharge;
	}


	public String getWeekDayCharge() {
		return weekDayCharge;
	}


	public String getWeekendCharge() {
		return weekendCharge;
	}


	public String getHolidayCharge() {
		return holidayCharge;
	}

}