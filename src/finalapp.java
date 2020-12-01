import java.sql.*;
import java.util.Scanner;

public class finalapp {
	 static final String JDBC_DRIVER = "org.postgresql.Driver";
	 static final String DB_URL = "jdbc:postgresql://localhost:5432/finproj";
	//  Database credentials
	   static String USER = "postgres";
	   static String PASS = "1234";
	   static String sql;
	   public static void main(String[] args) {
		   //login / register
		   Connection conn = null;
		   PreparedStatement stmt = null;
		   
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("org.postgresql.Driver");
		      Scanner scan = new Scanner(System.in);
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
		    		  System.out.println("I just got executed!");
		    		  adminDash();
		    	  }
		    	  else{
		    		  System.out.println(name + "what");
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
	   static void adminDash() {
		    System.out.println("I just got executed!");
		  }
	   
}//end finalapp

