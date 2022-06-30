package BankaSistemi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class veritabani_baglanti {

	static Connection myConn;
	static Statement myStat;
	static ResultSet myRs;

	static ResultSet tabloyaBaglantiYap(String sql_sorgu) {

		try {
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banka", "root", "1234");
			myStat = myConn.createStatement();
			myRs = myStat.executeQuery(sql_sorgu);
		} catch (Exception e) {
			
		}
		return myRs;

	}
	
	static void update2(String sql_sorgu) {
		try {
			myStat.executeUpdate(sql_sorgu);
		} catch (SQLException e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
	}

	static void update(String sql_sorgu) {
		try {
			myStat.executeUpdate(sql_sorgu);
		} catch (SQLException e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
	}

	static void ekle(String sql_sorgu) {
		try {
			myStat.executeUpdate(sql_sorgu);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void sil(String sql_sorgu) {
		try {
			myStat.executeUpdate(sql_sorgu);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
