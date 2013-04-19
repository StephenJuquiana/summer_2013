
public class Bank 
{
	   public String bname;
	   public String des;
	   public String branch;
	   public String location;
	   public Bank nextBank;
	   public Bank(String bname, String des, String branch, String location)
	   {
		   this.bname = bname;
		   this.des = des;
		   this.branch = branch;
		   this.location = location;
	   }
}
