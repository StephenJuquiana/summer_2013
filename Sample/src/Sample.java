import java.sql.*;
import javax.swing.JOptionPane;
public class Sample
{
    public Connection con = null;
    public Statement statement = null;
    public String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public String username = "root",password = "";
    static String dbname = "jdbc:mysql://localhost/sample?user=root";
    String sqlview="select * from item", sql="" , sqlview1="select * from customer";
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
            JOptionPane.showMessageDialog(null, "ID--ITEM NAME--Description \n"+dbtime);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"not connected\n"+e.getMessage());
        }
    }
    public void View_OrderLine() 
    {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(dbname,username,password);
            statement= con.createStatement();
            ResultSet rs = statement.executeQuery("select concat(c.fname,\" \",c.lname) as Customer,i.name as \"Item Name\",o.transaction_date,format(sum(o.qty*i.price),2) as \"Total Cost\" from customer as c, item as i, order_line as o where c.id = o.customer and i.id = o.item group by c.id,i.id,o.transaction_date order by c.id,o.transaction_date;");
            while (rs.next()){
            	dbtime = dbtime + "\n" + rs.getString(1) + "--" + rs.getString(2) + "--" + rs.getString(3) + "--" + rs.getString(4);
            }
            JOptionPane.showMessageDialog(null,"Customer--Item--Transaction Date--Total Cost \n" + dbtime);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"not connected\n"+e.getMessage());
        }
    }
    public void View_Customer() 
    {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(dbname,username,password);
            statement= con.createStatement();
            ResultSet rs = statement.executeQuery(sqlview1);
            while (rs.next()){
            	dbtime = dbtime + "\n" + rs.getString(1) + "--" + rs.getString(2) + " " + rs.getString(4) + " " + rs.getString(3) + "--" +rs.getString(5) + "--" +rs.getString(6) + "--" + rs.getString(7);
            }
            JOptionPane.showMessageDialog(null, "ID -- Customer Name -- Birthdate -- Contact Number -- Address "+dbtime);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"not connected\n"+e.getMessage());
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
            JOptionPane.showMessageDialog(null,"not connected\n"+e.getMessage());
        }
    }
    public void Add_OrderLine() 
    {
        try 
        {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(dbname,username,password);
            statement= con.createStatement();
    		String customer = JOptionPane.showInputDialog(null, "Customer ID");
    		String item = JOptionPane.showInputDialog(null, "Item ID");
    		String qty = JOptionPane.showInputDialog(null, "Quantity");
    		String date = JOptionPane.showInputDialog(null, "Transaction Date (YYYY-MM-DD)");
    		sql = "Insert into order_line(customer,item,qty,transaction_date) values("+customer+","+item+","+qty+",'"+date+"')";
    		statement.execute(sql);
	        Sample conn=new Sample();
	        conn.View_OrderLine();
 
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"not connected \n"+e.getMessage());
        }
    }
    public void Add_Customer() 
    {
        try 
        {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(dbname,username,password);
            statement= con.createStatement();
    		String fname = JOptionPane.showInputDialog(null, "First Name");
    		String lname = JOptionPane.showInputDialog(null, "Middle Name");
    		String mname = JOptionPane.showInputDialog(null, "Last Name");
    		String birthdate = JOptionPane.showInputDialog(null, "Birthdate (YYYY-MM-DD)");
    		String contactno = JOptionPane.showInputDialog(null, "Contact Number");
    		String address = JOptionPane.showInputDialog(null, "Address");
    		
    				
    		sql = "Insert into customer(fname,lname,mname,birthdate,contactno,address) values('"+fname+"','"+lname+"','"+mname+"','"+birthdate+"',"+contactno+",'"+address+"'"+")";
    		statement.execute(sql);
	        Sample conn=new Sample();
	        conn.View_Customer();
 
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"not connected\n"+e.getMessage());
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
	           	String input = JOptionPane.showInputDialog(null, "Insert Number You Want to Edit: \n1. Name \n2. Description \n3. Price \n4. Quantity \n5. Nothing to do here");
	        	if(input == null)
		    		quit=1;
		    	else if(input.equals(""));
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
	    		else if(input.equals("5"))
	    			quit = 1;
	        	
	    		int temp = JOptionPane.showConfirmDialog(null, "Update Another Item?" , "" , JOptionPane.YES_NO_OPTION);
	        	if(temp == JOptionPane.YES_OPTION);
	        	else if(temp == JOptionPane.NO_OPTION)
	        		quit =1;
	        }
	        catch (Exception e) 
	        {
	        	JOptionPane.showMessageDialog(null,"not connected \n"+e.getMessage());
	        }
    	}while(quit==0);
    }
    public void Update_OrderLine() 
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
		        conn.View_OrderLine();
	            String input = JOptionPane.showInputDialog(null, "Enter ID number of Customer");
	            String input1 = JOptionPane.showInputDialog("Enter Transaction Date","YYYY-MM-DD");
	            sql = "select concat(c.fname,\" \",c.lname),i.name,o.qty,o.transaction_date,format(o.qty*i.price,2) as \"Total Cost\" from customer as c, item as i, order_line as o where c.id = "+input+" and o.transaction_date = '"+input1+"' group by c.id,o.transaction_date;";
	            ResultSet rs = statement.executeQuery(sql);
	            while (rs.next())
	            	dbtime = dbtime + "\n" + rs.getString(1) + "--" + rs.getString(2) + "--" + rs.getString(3) + "--" + rs.getString(4) + "--" + rs.getString(5);
	            String input2 = JOptionPane.showInputDialog(null, "1.ITEM  \n2.Quantity \n3. Nothing to do here");
	        	if(input == null)
		    		quit=1;
		    	else if(input.equals(""));
	            else if(input2.equals("1"))
	            {
	            	String input3 = JOptionPane.showInputDialog(null, "ID of Item");
	            	sql = "Update order_line set item ="+input3+" where customer = "+input+" and transaction_date = '"+input1+"';";
		    		statement.execute(sql);
	            }
	            else if(input2.equals("2"))
	            {
	            	String input3 = JOptionPane.showInputDialog(null, "Quantity");
	            	sql = "Update order_line set qty ="+input3+" where customer = "+input+" and transaction_date = '"+input1+"';";
		    		statement.execute(sql);
	            }
	            int temp = JOptionPane.showConfirmDialog(null, "Update Another Order Line?" , "" , JOptionPane.YES_NO_OPTION);
	        	if(temp == JOptionPane.YES_OPTION);
	        	else if(temp == JOptionPane.NO_OPTION)
		        	quit=1;
		    }
	        catch (Exception e) 
	        {
	        	JOptionPane.showMessageDialog(null,"not connected\n"+e.getMessage());
	        }
    	}while(quit==0);
    }
    public void Update_Customer() 
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
		        conn.View_Customer();
		        
	            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Input ID of Customer to Update"));
	           	String input = JOptionPane.showInputDialog(null, "Insert Number You Want to Edit: \n1. First Name \n2. Middle Name \n3. Last Name \n4. Birthdate \n5. Contact Number \n6. Address \n7. Nothing to do here");
	        	if(input == null)
		    		quit=1;
		    	else if(input.equals(""));
	    		else if(input.equals("1"))
	    		{
	    			String fname = JOptionPane.showInputDialog(null, "First Name");
	    			sql = "Update customer set fname ='"+fname+"'"+" where id ="+id;
		    		statement.execute(sql);
	    		}
	    		else if(input.equals("2"))
	    		{
	    			String mname = JOptionPane.showInputDialog(null, "Middle Name");
	    			sql = "Update customer set mname = '"+mname+"'"+" where id ="+id;
		    		statement.execute(sql);
	    		}
	    		else if(input.equals("3"))
	    		{
	    			String lname = JOptionPane.showInputDialog(null, "Last Name");
	    			sql = "Update customer set lname ='"+lname+"' where id ="+id;
		    		statement.execute(sql);
	    		}
	    		else if(input.equals("4"))
	    		{
	    			String birthdate = JOptionPane.showInputDialog(null, "Birthdate (YYYY-MM-DD)");
	    			sql = "Update customer set birthdate = '"+birthdate+"' where id =" + id;
		    		statement.execute(sql);
	    		}
	    		else if(input.equals("5"))
	    		{
	    			String contactno = JOptionPane.showInputDialog(null, "Contact Number");
	    			sql = "Update customer set contactno = "+contactno+" where id =" + id;
		    		statement.execute(sql);
	    		}
	    		else if(input.equals("6"))
	    		{
	    			String address = JOptionPane.showInputDialog(null, "Address");
	    			sql = "Update item set qty = '"+address+"' where id =" + id;
		    		statement.execute(sql);
	    		}
	    		else if(input.equals("7"))
	    			quit = 1;

	        	int temp = JOptionPane.showConfirmDialog(null, "Update Another Customer?" , "" , JOptionPane.YES_NO_OPTION);
	        	if(temp == JOptionPane.YES_OPTION);
	        	else if(temp == JOptionPane.NO_OPTION)
	        		quit=1;
	        }
	        catch (Exception e) 
	        {
	        	JOptionPane.showMessageDialog(null,"not connected\n"+e.getMessage());
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
            JOptionPane.showMessageDialog(null,"not connected\n"+e.getMessage());
        }
    }
    public void Delete_Customer() 
    {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(dbname,username,password);
            statement= con.createStatement();
            Sample conn1=new Sample();
	        Sample conn=new Sample();
	        conn.View_Customer();
            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Input ID of Customer to Delete"));
    		sql = "Delete from customer where id = "+id;
    		statement.execute(sql);
	        conn1.View_Customer();
        }
        catch (Throwable e) {
            JOptionPane.showMessageDialog(null,"not connected\n"+e.getMessage());
        }
    }
    public static void main( String args[] )
    {
    	int x1 = 0;
    	do{
    		String input111 = JOptionPane.showInputDialog(null, "1. Customer \n2. Item \n3. Order Line \n4. Exit");
        	if(input111 == null)
	    		x1=1;
	    	else if(input111.equals("1"))
	    	{
	    		int x = 0;
		    	do
		    	{
			    	String input = JOptionPane.showInputDialog(null,"Choose what to do: \n1. View All Customers\n2. Add New Customer\n3. Update Customer\n4. Delete Customer \n5. Nothing to do here");
			    	if(input == null)
			    		x=1;
			    	else  if(input.equals("1"))
			    	{
				        Sample conn=new Sample();
				        conn.View_Customer();
			    	}
			    	else if(input.equals("2"))
			    	{
				        Sample conn=new Sample();
				        conn.Add_Customer();
			    	}
			    	else if(input.equals("3"))
			    	{
				        Sample conn=new Sample();
				        conn.Update_Customer();
			    	}
			    	else if(input.equals("4"))
			    	{
				        Sample conn=new Sample();
				        conn.Delete_Customer();
			    	}
			    	else if(input.equals("5"))
			    		x=1;
		    	}while(x==0);
	    	}
	    	else if(input111.equals("2"))
	    	{
	    		int x = 0;
		    	do
		    	{
			    	String input = JOptionPane.showInputDialog(null,"Choose what to do: \n1. View All Items\n2. Add New Item\n3. Update Item\n4. Delete Item \n5. Nothing to do here");
			    	if(input == null)
			    		x=1;
			    	else if(input.equals("1"))
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
	    	else if(input111.equals("3"))
	    	{
	    		int x = 0;
		    	do
		    	{
			    	String input = JOptionPane.showInputDialog(null,"Choose what to do: \n1. View \n2. Add\n3. Update\n4. Nothing to do here");
			    	if(input == null)
			    		x=1;
			    	else  if(input.equals("1"))
			    	{
				        Sample conn=new Sample();
				        conn.View_OrderLine();
			    	}
			    	else if(input.equals("2"))
			    	{
				        Sample conn=new Sample();
				        conn.Add_OrderLine();
			    	}
			    	else if(input.equals("3"))
			    	{
				        Sample conn=new Sample();
				        conn.Update_OrderLine();
			    	}
			    	else if(input.equals("4"))
			    		x=1;
		    	}while(x==0);
	
	    	}
	    	else if(input111.equals("4"))
	    		x1=1;
    	}while(x1==0);
    }
} 