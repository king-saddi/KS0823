import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	// Method to calculate the number of weekend days when weekendCharge is set as 'No' so we can subtract them from rental days to get charge days
	public static int getElapsedWeekendDays(LocalDate start, LocalDate end) {
		int weekendDays = 0;
		while (start.isBefore(end)) {
			if (start.getDayOfWeek() == DayOfWeek.SATURDAY || start.getDayOfWeek() == DayOfWeek.SUNDAY) {
				++weekendDays;
			}
			start = start.plusDays(1);
		}
		return weekendDays;
	}
	
	// Method that determines when July 4th occurs
	public static java.util.Calendar IndependenceDayObserved(int nYear) {
		int nX;
		int nMonth = 6; // July

		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(nYear, nMonth, 4);

		nX = cal.get(Calendar.DAY_OF_WEEK);
		switch (nX) {
		case 0: // Sunday
			cal = java.util.Calendar.getInstance();
			cal.set(nYear, nMonth, 5);
			return cal;
		case 1: // Monday
		case 2: // Tuesday
		case 3: // Wednesday
		case 4: // Thursday
		case 5: // Friday
			cal = java.util.Calendar.getInstance();
			cal.set(nYear, nMonth, 4);
			return cal;
		default:
			// Saturday
			cal = java.util.Calendar.getInstance();
			cal.set(nYear, nMonth, 3);
			return cal;
		}
	}

	// Calculates first monday of september date for labor day
	public static LocalDate LaborDayObs(int nYear) {
		LocalDate ld = LocalDate.of(nYear, Month.AUGUST, 31);
		LocalDate laborDay = ld.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
		return laborDay;
	}
	
	// Method to calculate whether a specified holiday is between two dates
	// This works even if the dates span multiple years
	public static int isHolidayBetween(LocalDate startDate, LocalDate endDate) {
		int count = 0;
		int startYear = startDate.getYear();
		int endYear = endDate.getYear();
		
		while(startYear <= endYear) {
			Calendar holiday1 = DateUtil.IndependenceDayObserved(startYear);
	
			LocalDate independenceDay = holiday1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	
			// Use the isAfter() and isBefore() method to check date
			if (!independenceDay.isBefore(startDate) && !independenceDay.isAfter(endDate)) {
				count++;
			}
			
			LocalDate laborDay = DateUtil.LaborDayObs(startYear);
			
			if (!laborDay.isBefore(startDate) && !laborDay.isAfter(endDate)) {
				count++;
			}
			startYear++;
		}
		
		return count;
	}
}
