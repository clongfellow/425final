import java.sql.*;

public class finalapp {
	 static final String JDBC_DRIVER = "org.postgresql.Driver";
	 static final String DB_URL = "jdbc:postgresql://localhost:5432/finproj";
	//  Database credentials
	   static final String USER = "postgres";
	   static final String PASS = "1234";
	   
	   public static void main(String[] args) {
		   //login / register
		   Connection conn = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("org.postgresql.Driver");
	
		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected database successfully...");
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
	      //add temp plate 
	   //nonmember dashboard method
	      //
	   //staff dashboard
	   	  //make reservation, get or change user info
	   //administrator dashboard
	   	  //
	   
}//end finalapp

