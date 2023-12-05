package com.curd_project.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MainClass {

	static String DRIVER_PATH = "com.mysql.cj.jdbc.Driver";
	static String DATABASE_URL = "jdbc:mysql://localhost:3306/Mydata";
	static String USER_NAME = "root";
	static String PASSWORD = "system";

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		while (true) {
			menu1();
			System.out.print("\n     ENTER YOUR CHOICE :- ");
			int a = scan.nextInt();
			switch (a) {
			case 1:
				insert();
				break;
			case 2:
				delete();
				break;
			case 3:
				viewById();
				break;
			case 4:
				viewAll();
				break;
			case 5:
				edit();
				break;
			case 6:
				System.exit(0);
				break;
			default:
				System.out.println("\n....ENTER A VALID OPTION....");
				break;
			}

		}
	}

	public static void menu1() {
		System.out.println("\n\n          1. Insert");
		System.out.println("          2. Delete");
		System.out.println("          3. View By Id");
		System.out.println("          4. View ");
		System.out.println("          5. Edit ");
		System.out.println("          6. Exit \n\n");
	}

	public static void insert() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);

		String query = "INSERT INTO STUDENT_INFO(STUDENT_ID,STUDENT_NAME,STUDENT_DEPT,STUDENT_CGP) VALUES(?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		System.out.print("     ENTER YOUR ID NUMBER :- ");
		int id = scan.nextInt();
		System.out.print("     ENTER YOUR NAME IN CAPITAL LETTER :- ");
		String name = scan.next();
		System.out.print("     ENTER YOUR DEPTMENT NAME :- ");
		String dept = scan.next();
		System.out.print("     ENTER YOUR PERCENTAGE :- ");
		double cgp = scan.nextDouble();

		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setString(3, dept);
		ps.setDouble(4, cgp);
		int rows = ps.executeUpdate();

		if (rows > 0) {
			System.out.println("....Data inserted successfull....");

		} else {
			System.out.println("....Failed To Insert....\n\n");

		}
		con.close();
	}

	public static void delete() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);

		System.out.print("     ENTER YOUR ID :- ");
		int id = scan.nextInt();
		String query = "DELETE FROM STUDENT_INFO WHERE STUDENT_ID = " + id;
		PreparedStatement ps = con.prepareStatement(query);
		ps.executeUpdate(query);
		con.close();
		System.out.println("....RECORD DELETED SUCCESSFULL....");

	}

	public static void viewById() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
		System.out.print("     ENTER YOUR ID :- ");
		int id = scan.nextInt();
		String query = "SELECT * FROM STUDENT_INFO WHERE STUDENT_ID = " + id;
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println("\n\n      STUDENT ID          " + rs.getInt(1) + "\n" + "      STUDENT NAME        "
					+ rs.getString(2) + "\n " + "     STUDENT DEPARTMENT  " + rs.getString(3) + "\n "
					+ "     STUDENT CGP         " + rs.getDouble(4) + "\n\n");

		}

		con.close();

	}

	public static void viewAll() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
		String query = "SELECT * FROM STUDENT_INFO";
		PreparedStatement ps = con.prepareStatement(query);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println("\n\n      STUDENT ID          " + rs.getInt(1) + "\n" + "      STUDENT NAME        "
					+ rs.getString(2) + "\n " + "     STUDENT DEPARTMENT  " + rs.getString(3) + "\n "
					+ "     STUDENT CGP         " + rs.getDouble(4) + "\n\n");

		}

		con.close();

	}

	public static void edit() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);

		System.out.print("\n\n     Enter your id :- ");
		int id = scan.nextInt();

		String query2 = "UPDATE STUDENT_INFO SET STUDENT_NAME = ?, STUDENT_DEPT = ?,STUDENT_CGP = ? WHERE STUDENT_ID = "
				+ id;
		PreparedStatement ps1 = con.prepareStatement(query2);

		System.out.print("     ENTER YOUR NAME IN CAPITAL LETTER :- ");
		String name = scan.next();
		System.out.print("     ENTER YOUR DEPTMENT NAME :- ");
		String dept = scan.next();
		System.out.print("     ENTER YOUR CGP :- ");
		double cgp = scan.nextDouble();

		// ps1.setInt(1, id);
		ps1.setString(1, name);
		ps1.setString(2, dept);
		ps1.setDouble(3, cgp);
		int rows = ps1.executeUpdate();
		if (rows > 0) {
			System.out.println("\n...Data edited successfull");
		} else {
			System.out.println("\n..failed to edit");
		}

		con.close();

	}

}
