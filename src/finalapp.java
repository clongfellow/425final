import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Scanner;
//need to populate database with spots/lots 
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
		    	  if (name.equals("non_member")){
		    		  nonmemberDash();
		    	  }
		    	  if (name.equals("a_member")){
		    		  memberDash();
		    	  }
		    	  if (name.equals("staff")){
		    		  staffDash();
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
	   static void memberDash() throws SQLException{
		   System.out.println("make reservation, or add plate? (mr, ap)");
		    input = scan.nextLine();
		    if(input.equals("mr")){
		    	String cname = USER;
		    	System.out.println("lot #?");
		    	int lotNum = scan.nextInt();
		    	System.out.println("spot #?");
		    	int spotNum = scan.nextInt();
		    	System.out.println("start time? YYYY-MM-DD HH:MM:SS");
		    	String startTime = scan.nextLine();
		    	System.out.println("end time? YYYY-MM-DD HH:MM:SS");
		    	String endTime = scan.nextLine();
		    	makeReservation(cname, "member", lotNum, spotNum, startTime, endTime);
		    }
		  }
	      //add temp plate 
	   //nonmember dashboard method
	   static void nonmemberDash() throws SQLException{
		   System.out.println("make reservation:");
		   String cname = USER;
	    	System.out.println("lot #?");
	    	int lotNum = scan.nextInt();
	    	System.out.println("spot #?");
	    	int spotNum = scan.nextInt();
	    	System.out.println("start time? YYYY-MM-DD HH:MM:SS");
	    	String startTime = scan.nextLine();
	    	System.out.println("end time? YYYY-MM-DD HH:MM:SS");
	    	String endTime = scan.nextLine();
	    	makeReservation(cname, "nonmember", lotNum, spotNum, startTime, endTime);
		  }
	      //
	   //staff dashboard
	   	  //make reservation, get or change user info
	   static void staffDash() {
		   System.out.println("make reservation, view info, or change info? (mr, vi, ci)");
		    input = scan.nextLine();
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
		    if (input.equals("um")) {
		    	System.out.println("enter username to have membership updated:");
		    	String cname = scan.nextLine();
		    	System.out.println("change to member or nonmember: (m, n)");
		    	String member = scan.nextLine();
		    	changeMembership(cname, member);
		    }
	   }
	   static void makeReservation(String cname, String type, int lotNum, int spotNum, String startTime, String endTime) throws SQLException{
		   Random rnd = new Random();
		   //need query to check if unique
		   //need to check if timeslot available for spot
		   int n = 10000 + rnd.nextInt(90000);
		   System.out.println("Attempting reservation...");
		   if(type.equals("drivein")){
			   stmt = conn.prepareStatement("insert into reservations(drive_in, start_time, end_time, res_num, lot_id, spot_num)\r\n"
				   		+ "VALUES (?, ?, ?, ?);");
					   //how to implement availability at specific timeslots);
		   }
		   if(type.equals("member")){
			   stmt = conn.prepareStatement("insert into reservations(member, start_time, end_time, res_num, lot_id, spot_num)\r\n"
				   		+ "VALUES (?, ?, ?, ?);");
					   //how to implement availability at specific timeslots);
		   }
		   if(type.equals("nonmember")){
			   stmt = conn.prepareStatement("insert into reservations(online, start_time, end_time, res_num, lot_id, spot_num)\r\n"
				   		+ "VALUES (?, ?, ?, ?);");
					   //how to implement availability at specific timeslots);
		   }
		   stmt.setBoolean(1, true);
		   stmt.setTimestamp(2, java.sql.Timestamp.valueOf("startTime"));
		   stmt.setTimestamp(3, java.sql.Timestamp.valueOf("endTime"));
		   stmt.setInt(4, n);
		   stmt.setInt(5, lotNum);
		   stmt.setInt(6, spotNum);
		   stmt.setInt(7, spotNum);
		   stmt.setInt(8, lotNum);
		   stmt.execute();
		   
		   System.out.println("Reservation Complete!");
	   }//end makeReservation
	   static void changeMembership(String user, String member) throws SQLException {
		   if(member.equals("m")) {
			   stmt = conn.prepareStatement("update users set is_role = a_member where username = ?;"
			   		+ "revoke non_member from ?;"
			   		+ "grant a_member to ?");
			   stmt.setString(1, user);
			   stmt.setString(2, user);
			   stmt.setString(3, user);
			   stmt.execute();
		   }
		   if(member.equals("n")) {
			   stmt = conn.prepareStatement("update users set is_role = non_member where username = ?;"
			   		+ "revoke a_member from ?;"
			   		+ "grant non_member to ?");
			   stmt.setString(1, user);
			   stmt.setString(2, user);
			   stmt.setString(3, user);
			   stmt.execute();
	       }
	    }//end changeMembership
	   //todo: viewUserInfo, changeUserInfo, report, (maybe CreateUser?)
}//end finalapp

