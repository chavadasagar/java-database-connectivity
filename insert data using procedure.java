import java.sql.*;
import java.util.*;
class sagar
{
	public static void main(String args[])
	{
		Scanner obj = new Scanner(System.in);
		int id;
		String name;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/sagar","root","");
				
			CallableStatement stmt = con.prepareCall("{call indata(?,?)}");
			System.out.print("Enter id:");
			id = obj.nextInt();
			System.out.print("Enter Name :");
			name = obj.next();
	
			stmt.setInt(1,id);
			stmt.setString(2,name);
		
			stmt.execute();
			
		}
		catch(Exception ex)
		{
			System.out.print(ex);
		}	
	}
}
