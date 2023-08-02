import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class RentalAgreement {

	private Tool selectedTool;
	private String toolCode;
	private String toolType;
	private String toolBrand;
	private int rentalDays;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private double dailyRentalCharge;
	private int chargeDays;
	private double preDiscountCharge;
	private double discountPercent;
	private double discountAmount;
	private double finalCharge;
	
	//String toolCode, String toolType, String toolBrand, int rentalDays, LocalDate checkoutDate, LocalDate dueDate,
	//int dailyRentalCharge, int chargeDays, double preDiscountCharge, int percent, double discountAmount, double finalCharge
	
	public RentalAgreement(Checkout checkoutInformation, Tool toolSelected) {
		populateRentalAgreement(checkoutInformation, toolSelected);
	}
	
	private void populateRentalAgreement(Checkout checkoutInformation, Tool toolSelected) {
		this.selectedTool = toolSelected;
		this.toolCode = toolSelected.getToolCode();
		this.toolType = toolSelected.getToolType().getName();
		this.toolBrand = toolSelected.getBrand();
		this.rentalDays = checkoutInformation.getRentalDayCount();
		this.checkoutDate = checkoutInformation.getCheckOutDate();
		this.dueDate = calculateDueDate();
		this.dailyRentalCharge = toolSelected.getToolType().getDailyCharge();
		int chargeDaysCalculated = calculateChargeDays(toolSelected.getToolType().getWeekendCharge(), toolSelected.getToolType().getHolidayCharge());
		this.chargeDays = rentalDays - chargeDaysCalculated;
		this.preDiscountCharge = chargeDays * dailyRentalCharge;
		this.discountPercent = checkoutInformation.getPercent();
		double discountAmountCalc = (discountPercent / 100) * preDiscountCharge;
		this.discountAmount = discountAmountCalc;
		this.finalCharge = preDiscountCharge - discountAmount;
	}

	@Override
	public String toString() {
		return "RentalAgreement [\n toolCode=" + toolCode + ", \n toolType=" + toolType
				+ ", \n toolBrand=" + toolBrand + ", \n rentalDays=" + rentalDays + ", \n checkoutDate=" + checkoutDate
				+ ", \n dueDate=" + dueDate + ",\n dailyRentalCharge=$" + dailyRentalCharge + ",\n chargeDays=" + chargeDays
				+ ",\npreDiscountCharge=$" + BigDecimal.valueOf(preDiscountCharge).setScale(2, RoundingMode.CEILING) + ", \n discountPercent=" + discountPercent
				+ "%, \n discountAmount=$" + BigDecimal.valueOf(discountAmount).setScale(2, RoundingMode.CEILING)+ ", \n finalCharge=$" + BigDecimal.valueOf(finalCharge).setScale(2, RoundingMode.CEILING) + "]";
	}

	// Calculate the numnber of days that need to be charged based on which tool is selected
	private int calculateChargeDays(String weekendCharge, String holidayCharge) {
		int removeHolidayChargeDays = 0;
		if(this.selectedTool.getToolType().getHolidayCharge().equals("No")) {
			removeHolidayChargeDays = DateUtil.isHolidayBetween(this.checkoutDate, this.dueDate);
		}
		
		int elapsedWeekendDays = 0;
		if(this.selectedTool.getToolType().getWeekendCharge().equals("No")) {
			elapsedWeekendDays = DateUtil.getElapsedWeekendDays(this.checkoutDate, this.dueDate);
		}
		
		return removeHolidayChargeDays + elapsedWeekendDays;
	}

	private LocalDate calculateDueDate() {
		LocalDate dueDate = this.checkoutDate.plusDays(this.rentalDays);		
		return dueDate;
	}
	
	public String getToolCode() {
		return toolCode;
	}

	public String getToolType() {
		return toolType;
	}

	public String getToolBrand() {
		return toolBrand;
	}

	public int getRentalDays() {
		return rentalDays;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public double getDailyRentalCharge() {
		return dailyRentalCharge;
	}

	public int getChargeDays() {
		return chargeDays;
	}

	public double getPreDiscountCharge() {
		return preDiscountCharge;
	}

	public double getDiscountPercent() {
		return discountPercent;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public double getFinalCharge() {
		return finalCharge;
	}
}
