class Info
{
   public String fname;
   public String lname;
   public String address;
   public String bday;
   public int AccountNumber;
   public int money;
   public Info nextInfo;
   Bank identifier3;
   public Info(String fname, String lname, String address, String bday, int AccountNumber, Bank identifier3)
   {
	   this.fname = fname;
	   this.lname = lname;
	   this.address = address;
	   this.bday = bday;
	   this.AccountNumber = AccountNumber;
	   this.identifier3 = identifier3;
   }
}