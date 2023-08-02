import java.time.LocalDate;

// Class that keeps track of necessary inputted data that helps generate the rental agreement
public class Checkout {
	private String toolCode;
	private int rentalDayCount;
	private double percent;
	private LocalDate checkOutDate;
	
	public Checkout(String toolCode, int rentalDayCount, double percent, LocalDate startDate) {
		this.toolCode = toolCode;
		this.rentalDayCount = rentalDayCount;
		this.percent = percent;
		this.checkOutDate = startDate;
	}

	public String getToolCode() {
		return toolCode;
	}

	public int getRentalDayCount() {
		return rentalDayCount;
	}

	public double getPercent() {
		return percent;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}
}
