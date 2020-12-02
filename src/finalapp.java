import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Scanner;

public class finalapp {
	 static final String JDBC_DRIVER = "org.postgresql.Driver";
	 static final String DB_URL = "jdbc:postgresql://localhost:5432/finproj";
	//  Database credentials
	   static String USER = "postgres";
	   static String PASS = "1234";
	   static String input;
	   static Scanner scan = new Scanner(System.in);
	   static Connection conn = null;
	   static PreparedStatement stmt = null;
	   public static void main(String[] args) {
		   //login / register
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("org.postgresql.Driver");
			  System.out.println("input username: ");
		      USER = scan.nextLine();
			  System.out.println("input password: ");
			  PASS = scan.nextLine();
//				   System.out.println("input username: ");
//				   USER = scan.nextLine();
//				   System.out.println("input password: ");
//				   PASS = scan.nextLine();
//				   System.out.println("input role: member/non-member/staff/admin (m/n/s/a) ");
//				   String role = scan.nextLine();
//				   sql = "CREATE USER" + USER + "WITH PASSWORD"+PASS+";";

		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected database successfully...");
		    //STEP 4: Execute a query
		      System.out.println("Checking roles...");
		      stmt = conn.prepareStatement("select is_role from users where username= ?");
		      stmt.setString(1, USER);
		      ResultSet rs = stmt.executeQuery();
		      while(rs.next()){
		    	  String name = rs.getString("is_role").trim();
		    	  System.out.println(name);
		    	  if (name.equals("admin")){
		    		  adminDash();
		    	  }
		      }
		      rs.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   
		   //if statements for user role 
		   
		   //finish
		   System.out.println("Goodbye!");
		   
	  }//end main
	   
	   //member dashboard method
	   static void memberDash() {
		    System.out.println("I just got executed!");
		  }
	      //add temp plate 
	   //nonmember dashboard method
	   static void nonmemberDash() {
		    System.out.println("I just got executed!");
		  }
	      //
	   //staff dashboard
	   	  //make reservation, get or change user info
	   static void staffDash() {
		    System.out.println("I just got executed!");
		  }
	   //admin dashboard
	   static void adminDash() throws SQLException {
		    System.out.println("make reservation, update membership, change info, or report? (mr, um, ci, r)");
		    input = scan.nextLine();
		    //need to implement checking if spot is reserved at given time
		    if(input.equals("mr")){
		    	System.out.println("customer name?");
		    	String cname = scan.nextLine();
		    	System.out.println("lot #?");
		    	int lotNum = scan.nextInt();
		    	System.out.println("spot #?");
		    	int spotNum = scan.nextInt();
		    	System.out.println("start time? YYYY-MM-DD HH:MM:SS");
		    	String startTime = scan.nextLine();
		    	System.out.println("end time? YYYY-MM-DD HH:MM:SS");
		    	String endTime = scan.nextLine();
		    	makeReservation(cname, "drivein", lotNum, spotNum, startTime, endTime);
		    }
	   }
	   static void makeReservation(String cname, String type, int lotNum, int spotNum, String startTime, String endTime) throws SQLException{
		   Random rnd = new Random();
		   //need query to check if unique
		   int n = 10000 + rnd.nextInt(90000);
		   if(type.equals("drivein")){
			   stmt = conn.prepareStatement("insert into reservations(drive_in, start_time, end_time, res_num)\r\n"
				   		+ "VALUES (?, ?, ?, ?);");
				   stmt.setBoolean(1, true);
				   stmt.setTimestamp(2, java.sql.Timestamp.valueOf("startTime"));
				   stmt.setTimestamp(3, java.sql.Timestamp.valueOf("endTime"));
				   stmt.setInt(4, n);
		   }
		   
	   }
	   }//end finalapp

