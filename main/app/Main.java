package ro.main.app;
import ro.sql.oop.*;

public class Main {

	public static void main(String[] args) {
		SQLite bazaDeDate = new SQLite("angajati"); // Am creat o instanta a clasei SQLite
		bazaDeDate.createTable(); // Am utilizat metoda createTable
		bazaDeDate.insertData("Petrica", 18, "Str. Romana nr. 1", "Programator"); // Am utilizat metoda insertData
		bazaDeDate.insertData("Daniel", 30, "Str. Mihai Viteazu nr. 49", "Electrician"); // Am utilizat metoda insertData
		bazaDeDate.selectData(); // Am utilizat metoda selectData pentru a citii toate pesoanele din baza de date
	}

}
