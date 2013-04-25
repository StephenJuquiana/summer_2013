import java.sql.*;
import javax.swing.JOptionPane;
public class Sample
{
    public Connection con = null;
    public Statement statement = null;
    public String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public String username = "root",password = "";
    static String dbname = "jdbc:mysql://localhost/sample?user=root";
    String sqlview="select * from item", sql="";
    String dbtime = "";

    public void View_Item() 
    {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(dbname,username,password);
            statement= con.createStatement();
            ResultSet rs = statement.executeQuery(sqlview);
            while (rs.next()){
            	dbtime = dbtime + "\n" + rs.getString(1) + "--" + rs.getString(2) + "--" + rs.getString(3) + "--" + rs.getString(4) + "--" +rs.getString(5);
            }
            JOptionPane.showMessageDialog(null, "ID--ITEM NAME--Description"+dbtime);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"not connected"+e.getMessage());
        }
    }
    public void Add_Item() 
    {
        try 
        {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(dbname,username,password);
            statement= con.createStatement();
    		String iname = JOptionPane.showInputDialog(null, "Input Name of Item");
    		String idesc = JOptionPane.showInputDialog(null, "Input Description of Item");
    		String iprice = JOptionPane.showInputDialog(null, "Input Price of Item");
    		String iqty = JOptionPane.showInputDialog(null, "Input Number of Item");
    		sql = "Insert into item(name,description,price,qty) values('"+iname+"','"+idesc+"',"+iprice+","+iqty+")";
    		statement.execute(sql);
	        Sample conn=new Sample();
	        conn.View_Item();
 
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"not connected"+e.getMessage());
        }
    }
    
    public void Update_Item() 
    {
    	int quit = 0;
    	do
    	{
	        try 
	        {
	            Class.forName(JDBC_DRIVER);
	            con = DriverManager.getConnection(dbname,username,password);
	            statement= con.createStatement();
		        Sample conn=new Sample();
		        conn.View_Item();
		        
	            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Input ID of Item to Update"));
	           	String input = JOptionPane.showInputDialog(null, "Insert Number You Want to Edit: \n1. Name \n2. Description \n3. Price \n4. Quantity \nInput any to exit");
	    		if(input==null);
	    		else if(input.equals("1"))
	    		{
	    			String iname = JOptionPane.showInputDialog(null, "Input Name of Item");
	    			sql = "Update item set name ='"+iname+"'"+" where id ="+id;
		    		statement.execute(sql);
	    		}
	    		else if(input.equals("2"))
	    		{
	    			String idesc = JOptionPane.showInputDialog(null, "Input Description of Item");
	    			sql = "Update item set description = '"+idesc+"'"+" where id ="+id;
		    		statement.execute(sql);
	    		}
	    		else if(input.equals("3"))
	    		{
	    			String iprice = JOptionPane.showInputDialog(null, "Input Price of Item");
	    			sql = "Update item set price ="+iprice+" where id ="+id;
		    		statement.execute(sql);
	    		}
	    		else if(input.equals("4"))
	    		{
	    			String iqty = JOptionPane.showInputDialog(null, "Input Number of Item");
	    			sql = "Update item set qty = "+iqty+" where id =" + id;
		    		statement.execute(sql);
	    		}
	    		else
	    			quit = 1;
	        }
	        catch (Exception e) 
	        {
	        	JOptionPane.showMessageDialog(null,"not connected"+e.getMessage());
	        }
    	}while(quit==0);
    }
    public void Delete_Item() 
    {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(dbname,username,password);
            statement= con.createStatement();
            Sample conn1=new Sample();
	        Sample conn=new Sample();
	        conn.View_Item();
            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Input ID of Item to Delete"));
    		sql = "Delete from item where id = "+id;
    		statement.execute(sql);
	        conn1.View_Item();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"not connected"+e.getMessage());
        }
    }
   
    public static void main( String args[] )
    {
    	x1=0;
    	do
    	{
    		String input1 = JOptionPane.showInputDialog(null, "1. Customer \n2. Item \3. Order Line");
    		if(input1 == null);
    		else if(input.equals("1"))
    		{
		    	int x = 0;
		    	do
		    	{
			    	String input = JOptionPane.showInputDialog(null,"Choose what to do: \n1. View All Customers \n2. Add New Customer \n3. Update Customer\n4. Delete Customer \n5. Nothing to do Here!");
			    	if(input == null);
			    	else  if(input.equals("1"))
			    	{
				        Sample conn=new Sample();
				        conn.View_Item();
			    	}
			    	else if(input.equals("2"))
			    	{
				        Sample conn=new Sample();
				        conn.Add_Item();
			    	}
			    	else if(input.equals("3"))
			    	{
				        Sample conn=new Sample();
				        conn.Update_Item();
			    	}
			    	else if(input.equals("4"))
			    	{
				        Sample conn=new Sample();
				        conn.Delete_Item();
			    	}
			    	else if(input.equals("5"))
			    		x=1;		    	
		    	}while(x==0);
    		}
    		else if(input.equals("2"))
    		{
    		}
    		else if(input.equals("3"));
    	}while(x1==0);
    }
}