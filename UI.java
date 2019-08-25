/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zhumanov;

import java.sql.DatabaseMetaData;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UI {

	private Databaze db;

	public UI() {
		db = new Databaze();
	}

	private Date enterDate() {
		Scanner sc = new Scanner(System.in);
		Date dt = null;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("Datum narozeni v formatu yyyy-mm-dd:");
		String date = sc.nextLine();
		try {
			dt = df.parse(date);
		} catch (ParseException ex) {
			System.out.println("Chyba daty!");
			dt = enterDate();
		}
		return dt;
	}

	public void run() {
		menu();
	}

	private void menu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("----------------------------------------------------------");
		while (true) {
			System.out.println("1-Novy Student");
			System.out.println("2-Nova Znamka");
			System.out.println("3-Odeber Studenta");
			System.out.println("4-Vypis Studenta");
			System.out.println("5-Vypis vsech");
			System.out.println("6-Prumer v technickem a humanitnem oboru");
			System.out.println("7-Vypis vsech studentu");
			System.out.println("8-Ulozeni do souboru");
			System.out.println("9-Nacteni ze souboru");
			System.out.println("10-Vymazani studenta ze souboru podle ID");
			System.out.println("11-Nacteni studenta ze souboru");
			System.out.println("12-Generuj studenta");
			if (sc.hasNextInt()) {
				System.out.println("");
				switch (sc.nextInt()) {
				case (1):
					newStudent();
					break;
				case (2):
					newMark();
					break;
				case (3): {
					removeStudent();
					break;
				}
				case (4): {
					findStudent();
					break;
				}
				case (5): {
					writeListStudents();
					break;
				}
				case (6): {
					writeAVG();
					break;
				}
				case (7): {
					writeCountOfStudents();
					break;
				}
				case (8): {
					saveDatabase();
					break;
				}
				case (9): {
					readDatabase();
					break;
				}
				case (10): {
					removeStudentfromFile();
					break;
				}
				case (11): {
					readStudentFromFile();
					break;
				}
				case (12): {
					generateSudent();
					break;
				}
				default:
					System.out.println("Nespravny vyber");
					break;
				}
			}
		}
	}

	private void newStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Pridat do oboru:");
		System.out.println("1-Technicky obor");
		System.out.println("2-Humanitni obor");
		System.out.println("3-Kombinovany obor");
		System.out.println("Vas vyber:");
		int choose = 1;
		while (true) {
			if (sc.hasNextInt()) {
				choose = sc.nextInt();
				if (choose > 0 && choose < 6) {
					break;
				}else {
					System.out.println("Chyba cisla oboru! Opakuj!");
				}
			}
		}
		sc = new Scanner(System.in);
		System.out.println("Jmeno:");
		String name = sc.nextLine();
		System.out.println("Prijmeni:");
		String surname = sc.nextLine();
		Date dt = enterDate();
		switch (choose) {
		case (1): {
			db.addNewStudentT(name, surname, dt);
			break;
		}
		case (2): {
			db.addNewStudentH(name, surname, dt);
			break;
		}
		case (3): {
			db.addNewStudentK(name, surname, dt);
			break;
		}
		}
	}

	private void newMark() {
		int id = -1;
		int mark = -1;
		System.out.println("Zadej ID studenta:");
		Scanner sc = new Scanner(System.in);
		if (sc.hasNextInt()) {
			id = sc.nextInt();
		}

		System.out.println("Zadej znamku(1-5):");
		if (sc.hasNextInt()) {
			mark = sc.nextInt();
		}
		
		if (db.addMark(id, mark)) {
			System.out.println("Znamka pridana");
		} else {
			System.out.println("Student v seznamu neexistuje nebo znamka neni v rozsahu 1-5");
		}
	}

	private void removeStudent() {
		System.out.println("Zadej ID studenta:");
		Scanner sc = new Scanner(System.in);
		int id = -1;
		if (sc.hasNextInt()) {
			id = sc.nextInt();
		}
		Student st = db.removeStudent(id);
		if (st != null) {
			System.out.println(st.toString() + "\nbyl odebran");
		} else {
			System.out.println("Student v seznamu neexistuje");
		}
	}

	private void findStudent() {
		System.out.println("Zadej ID studenta:");
		Scanner sc = new Scanner(System.in);
		int id = -1;
		if (sc.hasNextInt()) {
			id = sc.nextInt();
		}
		Student st = db.searchStudent(id);
		if (st != null) {
			System.out.println(st.toString());
			ArrayList<Integer> marks = st.getMarks();
			System.out.print("Znamky: ");
			for (int i = 0; i < marks.size(); i++) {
				System.out.print(marks.get(i) + " ");
			}
			System.out.println();
		} else {
			System.out.println("Student v seznamu neexistuje");
		}
	}

	private void writeListStudents() {
		ArrayList<Student> students = db.getSortedList();
		for (int i = 0; i < students.size(); i++) {
			System.out.println(students.get(i).toString());
		}
	}

	private void writeAVG() {
		System.out.println("Studijni prumer:" + db.getAVGTechHum());
	}

	private void writeCountOfStudents() {
		System.out.println("Tech.studium: " + db.getCountT());
		System.out.println("Hum.studium: " + db.getCountH());
		System.out.println("Komb.studium: " + db.getCountK());
	}

	private void saveDatabase() {
		db.saveDatabase();
	}

	private void readDatabase() {
		db.readDatabase();
	}

	private void removeStudentfromFile() {
		System.out.println("Zadej ID studenta:");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		Student st = db.removeStudent(id);
		if (st != null) {
			System.out.println(st.toString() + " odebran z souboru");
		} else {
			System.out.println("Student v souboru neexistuje");
		}
	}

	private void readStudentFromFile() {
		System.out.println("Zadej jmeno studenta:");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		System.out.println("Zadej prijmeni:");
		String surname = sc.nextLine();
		Student st = db.readStudentFromFile(name, surname);
		if (st != null) {
			System.out.println(st.toString() + " nacten");
		} else {
			System.out.println("Student v souboru neexistuje");
		}
	}

	private void generateSudent() {
		String name = "TestName";
		String surname = "TestSurname";
		Date date = new Date(111, 11, 11);
		db.addNewStudentH(name, surname, date);
	}
}
