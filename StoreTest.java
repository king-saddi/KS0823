import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class StoreTest {

	@Test
	void test() {
		Store st = new Store();
		String toolCode = "CHNS";
		int rentalDays = 5;
		String startDate = "02/07/2015";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		double discountPercent = 25.0;
        LocalDate checkoutDate = LocalDate.parse(startDate, formatter);
        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        
        Checkout currentOrder = new Checkout(toolCode, rentalDays, discountPercent, checkoutDate);
        st.initializeTools();
		RentalAgreement rAgmnt = st.generateRentalAgreement(currentOrder);
		System.out.println(rAgmnt.toString());
		
		assertEquals(rAgmnt.getDueDate(), dueDate);
		assertEquals(rAgmnt.getPreDiscountCharge(), 4.47, 0.01);
		assertEquals(rAgmnt.getFinalCharge(), 3.36, 0.01);
	}
	

	@Test
	void test2() {
		Store st = new Store();
		String toolCode = "LADW";
		int rentalDays = 3;
		String startDate = "02/07/2020";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		double discountPercent = 10.0;
        LocalDate checkoutDate = LocalDate.parse(startDate, formatter);
        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        
        Checkout currentOrder = new Checkout(toolCode, rentalDays, discountPercent, checkoutDate);
        st.initializeTools();
		RentalAgreement rAgmnt = st.generateRentalAgreement(currentOrder);
		System.out.println(rAgmnt.toString());
		
		assertEquals(rAgmnt.getDueDate(), dueDate);
		assertEquals(rAgmnt.getPreDiscountCharge(), 3.98, 0.01);
		assertEquals(rAgmnt.getFinalCharge(), 3.59, 0.01);
	}
	
	@Test
	void test3() {
		Store st = new Store();
		String toolCode = "JAKR";
		int rentalDays = 5;
		String startDate = "03/09/2015";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		double testDiscountPercent = 101.0;

        String warning = processDiscountPercentPartialCode(testDiscountPercent);
        assertEquals(warning, "Invalid discount percentage, must be whole number between 0 and 100");
	}
	
	public String processDiscountPercentPartialCode(double testDiscountPercent) {
		if(testDiscountPercent<0 || testDiscountPercent>100) {
			return "Invalid discount percentage, must be whole number between 0 and 100";
		}
		return "";
	}

	@Test
	void test4() {
		Store st = new Store();
		String toolCode = "JAKD";
		int rentalDays = 6;
		String startDate = "03/09/2015";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		double discountPercent = 0.0;
        LocalDate checkoutDate = LocalDate.parse(startDate, formatter);
        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        
        Checkout currentOrder = new Checkout(toolCode, rentalDays, discountPercent, checkoutDate);
        st.initializeTools();
		RentalAgreement rAgmnt = st.generateRentalAgreement(currentOrder);
		System.out.println(rAgmnt.toString());
		
		assertEquals(rAgmnt.getDueDate(), dueDate);
		assertEquals(rAgmnt.getPreDiscountCharge(), 8.97, 0.01);
		assertEquals(rAgmnt.getFinalCharge(), 8.97, 0.01);
	}
	

	@Test
	void test5() {
		Store st = new Store();
		String toolCode = "JAKD";
		int rentalDays = 9;
		String startDate = "02/07/2015";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		double discountPercent = 0.0;
        LocalDate checkoutDate = LocalDate.parse(startDate, formatter);
        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        
        Checkout currentOrder = new Checkout(toolCode, rentalDays, discountPercent, checkoutDate);
        st.initializeTools();
		RentalAgreement rAgmnt = st.generateRentalAgreement(currentOrder);
		System.out.println(rAgmnt.toString());
		
		assertEquals(rAgmnt.getDueDate(), dueDate);
		assertEquals(rAgmnt.getPreDiscountCharge(), 17.94, 0.01);
		assertEquals(rAgmnt.getFinalCharge(), 17.94, 0.01);
	}
	
	@Test
	void test6() {
		Store st = new Store();
		String toolCode = "JAKD";
		int rentalDays = 4;
		String startDate = "02/07/2020";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		double discountPercent = 50.0;
        LocalDate checkoutDate = LocalDate.parse(startDate, formatter);
        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        
        Checkout currentOrder = new Checkout(toolCode, rentalDays, discountPercent, checkoutDate);
        st.initializeTools();
		RentalAgreement rAgmnt = st.generateRentalAgreement(currentOrder);
		System.out.println(rAgmnt.toString());
		
		assertEquals(rAgmnt.getDueDate(), dueDate);
		assertEquals(rAgmnt.getPreDiscountCharge(), 2.99, 0.01);
		assertEquals(rAgmnt.getFinalCharge(), 1.50, 0.01);
	}
}
