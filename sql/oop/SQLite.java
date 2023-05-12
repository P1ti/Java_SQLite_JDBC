package ro.sql.oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLite {
	private Connection conn;
	private String tableName;

	public SQLite(String tableName) { // Am creat constructorul, care are ca parametru numele bazei de date ce va fi
										// creata, odata cu instantierea clasei SQLite
		try {
			Class.forName("org.sqlite.JDBC"); // metoda forName() este folosita pentru a citii instanta clasei care este
												// data ca parametru
			conn = DriverManager.getConnection("jdbc:sqlite:database.db"); // realizez conectarea la baza de date
																			// database.db
			conn.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.tableName = tableName;
	}

	public void createTable() {
		// Daca exista deja un tabel care are numele pe care vreau eu sa-l dau, voi da
		// respectivului tabel drop
//		String sqlDropTable = "DROP TABLE " + this.tableName;
		// Creez tabelul
		String createTable = "CREATE TABLE " + this.tableName + "(nume TEXT, varsta INT, adresa TEXT, locDeMunca TEXT)";

		try {
			Statement st = conn.createStatement();
//			st.executeUpdate(sqlDropTable);
			st.executeUpdate(createTable);
			st.close();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertData(String nume, int varsta, String adresa, String locDeMunca) {
		String insertData = "INSERT INTO " + tableName + "(nume, varsta, adresa, locDeMunca) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement pSt = conn.prepareStatement(insertData);
			pSt.setString(1, nume);
			pSt.setInt(2, varsta);
			pSt.setString(3, adresa);
			pSt.setString(4, locDeMunca);
			pSt.execute();
			pSt.close();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void selectData() {
		String selectData = "SELECT * FROM " + tableName;

		try {
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery(selectData);
			while (res.next()) {
				String nume = res.getString("nume");
				int varsta = res.getInt("varsta");
				String adresa = res.getString("adresa");
				String locDeMunca = res.getString("locDeMunca");

				System.out.println("Name: " + nume + " | Varsta: " + varsta + " | Adresa: " + adresa
						+ " | Loc de munca: " + locDeMunca);
			}
			res.close();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
