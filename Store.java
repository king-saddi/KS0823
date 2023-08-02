import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Store {

	public static List<Tool> tools = new ArrayList<Tool>();
	public static Map<String, Tool> toolCodeToTool = new HashMap<>();

	public static void main(String[] args) {

		initializeTools();
		printOutToolsAndDetails();
		Checkout checkoutInfo = null;
		try {
			checkoutInfo = intakeCheckout();
		} catch (Exception e) {
			System.out.println("Invalid checkout input entered, please try again");
			e.printStackTrace();
		}
				
		RentalAgreement rAgmnt = generateRentalAgreement(checkoutInfo);
		System.out.println(rAgmnt.toString());
		
	}
	
	// Initialize tool objects here
	static void initializeTools() {
		Tool CHNS = new Tool("CHNS", ToolType.Chainsaw, "Stihl ");
		Tool LADW = new Tool("LADW", ToolType.Ladder, "Werner");
		Tool JAKD = new Tool("JAKD", ToolType.Jackhammer, "DeWalt");
		Tool JAKR = new Tool("JAKR", ToolType.Jackhammer, "Ridgid");

		tools.add(CHNS);
		tools.add(LADW);
		tools.add(JAKD);
		tools.add(JAKR);

		toolCodeToTool.put("CHNS", CHNS);
		toolCodeToTool.put("LADW", LADW);
		toolCodeToTool.put("JAKD", JAKD);
		toolCodeToTool.put("JAKR", JAKR);
	}

	// Print out the tools and details 
	private static void printOutToolsAndDetails() {
		System.out.println("Welcome to the tool store, here are your options:");
		System.out.println("ToolCode " + "    Brand	" + "  Tool Type	" + " Daily Charge	" + "WeekDay Charge	"
				+ " Weekend Charge	" + " Holiday Charge	");
		for (Tool t : tools) {
			System.out.println(t.getToolCode() + "	     " + t.getBrand() + "       " + t.getToolType().getName()
					+ "	 $" + t.getToolType().getDailyCharge() + "		" + t.getToolType().getWeekDayCharge()
					+ "		 " + t.getToolType().getWeekendCharge() + "		 " + t.getToolType().getHolidayCharge());
		}
	}

	// Prompt user for necessary information and record input in Checkout object
	private static Checkout intakeCheckout() throws Exception {
		Scanner sc = new Scanner(System.in); // System.in is a standard input stream

		System.out.println("Enter the desired toolCode: ");
		String toolCode = sc.nextLine().trim();
		
		System.out.println("Enter the number of days for which you want to rent the tool: ");
		int rentalDayCount = sc.nextInt();
		if(rentalDayCount < 1) {
			System.out.println("Invalid number of desired rental days, must be greater than 0.");
			throw new Exception();
		}
				
		System.out.println("Enter the discount percent as a whole number: ");
		double discountPercent = sc.nextDouble();
		if(discountPercent<0 || discountPercent>100) {
			System.out.println("Invalid discount percentage, must be whole number between 0 and 100");
			throw new Exception();
		}
			
		System.out.println("Please enter date in format(dd/MM/yyyy), which means if entering month of august use '08' instead of '8'");
		String startDateUnparsed = sc.next();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate startDate = LocalDate.parse(startDateUnparsed, formatter);
		
		Checkout currentOrder = new Checkout(toolCode, rentalDayCount, discountPercent, startDate);
		
		sc.close();
		
		return currentOrder;
		
	}
	

	static RentalAgreement generateRentalAgreement(Checkout checkoutInfo) {
		Tool toolSelected = toolCodeToTool.get(checkoutInfo.getToolCode());
		RentalAgreement currentOrderRentalAgreement = new RentalAgreement(checkoutInfo, toolSelected);
		return currentOrderRentalAgreement;
	}

}
