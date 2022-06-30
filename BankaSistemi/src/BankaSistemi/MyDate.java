package BankaSistemi;

import java.time.LocalDate;

public class MyDate {
	static LocalDate date = LocalDate.now();;
	static LocalDate mydate = date.plusMonths(1);
	static LocalDate date2() {
		date = LocalDate.now();
		return date;
	}
	
	
	static void TarihiIleriSar() {
		date = LocalDate.now();
		date=date.plusMonths(1);
		
	}

}
