# KS0823
PointOfSale tool

Example run through:

Welcome to the tool store, here are your options:
ToolCode     Brand	  Tool Type	 Daily Charge	WeekDay Charge	 Weekend Charge	 Holiday Charge	
CHNS	     Stihl        CHAINSAW	 $1.49		Yes		 No		 Yes
LADW	     Werner       LADDER	 $1.99		Yes		 Yes		 No
JAKD	     DeWalt       JACKHAMMER	 $2.99		Yes		 No		 No
JAKR	     Ridgid       JACKHAMMER	 $2.99		Yes		 No		 No
Enter the desired toolCode: 
LADW
Enter the number of days for which you want to rent the tool: 
400
Enter the discount percent as a whole number: 
20
Please enter date in format(dd/MM/yyyy), which means if entering month of august use '08' instead of '8'
01/07/2023
Holiday Count:3
RentalAgreement [
 toolCode=LADW, 
 toolType=LADDER, 
 toolBrand=Werner, 
 rentalDays=400, 
 checkoutDate=2023-07-01, 
 dueDate=2024-08-04,
 dailyRentalCharge=$1.99,
 chargeDays=397,
preDiscountCharge=$790.03, 
 discountPercent=20.0%, 
 discountAmount=$158.01, 
 finalCharge=$632.03]
