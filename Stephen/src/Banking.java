import javax.swing.*;
public class Banking 
{
	public static void main(String []args)
	{
		List identifier1 = new List();
		ListBank identifier3 = new ListBank();
		int quit = 0;
		int quit1 = 0;
		int marker = 0;
		do
		{
			try
			{
				String input = JOptionPane.showInputDialog(null, "Enter number of Transaction" +
						"\n1. Add Bank \n2. Add Account ("+marker+")"+"\n3. Deposit \n4. Withdraw \n5. View All Transaction");
				if(input == null)
					JOptionPane.showMessageDialog(null, "There's no way out!");
				else if(input.equals("1"))
				{
					String bname = JOptionPane.showInputDialog(null, "Enter Bank Name");
					if(bname.equals(""))
						JOptionPane.showMessageDialog(null, "Registration Failed\nBank Name Empty");
					else
					{
						String des = JOptionPane.showInputDialog(null, "Description of Bank");
						if(des.equals(""))
							JOptionPane.showMessageDialog(null, "Registration Failed\nDescription Empty");
						else
						{
							String branch = JOptionPane.showInputDialog(null, "Branch of Bank");
							Bank pointer1;
							for(pointer1 = identifier3.firstnode; pointer1 != null && pointer1.branch != branch; pointer1 = pointer1.nextBank);
							if(branch.equals(""))
								JOptionPane.showMessageDialog(null, "Registration Failed\nBranch not Specified");
							else
							{
								String location = JOptionPane.showInputDialog(null, "Location of Bank");
								if(location.equals(""))
									JOptionPane.showMessageDialog(null, "Registration Failed\nLocation not Specified");
								else
								{
									Bank identifier4 = new Bank(bname,des,branch,location);
									if(identifier3.firstnode == null)
									{
										identifier3.firstnode = identifier3.lastnode = identifier4;
										identifier3.lastnode.nextBank= null;
									}
									else if(identifier3.firstnode == identifier3.lastnode)
									{
										identifier3.firstnode.nextBank=identifier3.lastnode=identifier4;
										identifier3.lastnode.nextBank = null;
									}
									else
									{
										identifier3.lastnode.nextBank = identifier4;
										identifier3.lastnode = identifier4;
										identifier3.lastnode.nextBank = null;
									}
									JOptionPane.showMessageDialog(null, "Bank Registration Successful! \n" +
											"Bank Name: "+bname.toUpperCase()+"\nBranch: "+branch+"\nLocation: "+location+"\nBank Description: "+des);
								}
							}
						}
					}
				}
				else if(input.equals("2"))
				{
					int x = 0;
					do
					{
						String bankprinter = "";
						Bank pointer12;
						int abs = 1;
						for(pointer12 = identifier3.firstnode; pointer12 != null; pointer12 = pointer12.nextBank)
							bankprinter = bankprinter + "\n"+ abs++ +". "+ pointer12.bname.toUpperCase() +" -- "+ pointer12.branch;
						quit1=0;
						int bank= Integer.parseInt(JOptionPane.showInputDialog(null, "Choose bank you want to transact with:"+bankprinter));
						Bank pointer;
						int checker = 1;
						for(pointer = identifier3.firstnode; pointer != null && checker!=bank; pointer = pointer.nextBank)
							checker++;
						if(pointer==null)
							JOptionPane.showMessageDialog(null, "Bank Account not Registered");
						else
						{
							int AccountNumber = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Your Desired Account Number"));
							String fname = JOptionPane.showInputDialog(null, "Enter Your First Name");
							String lname = JOptionPane.showInputDialog(null, "Enter Your Last Name");
							String address = JOptionPane.showInputDialog(null, "Enter Your Address");
							String bday = JOptionPane.showInputDialog(null, "Enter Your Birthday");
							int y = JOptionPane.showConfirmDialog(null, "Hello: \n"+fname+" "+lname+ "\nAddress: "+address+"\nBirthday: "+bday+"\nBank Name: "+bank+"\nAccount Number: "+AccountNumber+"\nIS THE ABOVE INFORMATION CORRECT?", "WARNING", JOptionPane.YES_NO_OPTION);
							if(y==JOptionPane.YES_OPTION)
							{
								Info identifier = new Info(fname,lname,address,bday,AccountNumber, pointer);
								if(identifier1.firstnode == null)
								{
									identifier1.firstnode = identifier1.lastnode = identifier;
									identifier1.lastnode.nextInfo = null;
									marker++;
									do
									{
										int dvalue = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter initial deposit\nMinimum of 100!"));
										if(dvalue>=100)
										{
											identifier1.firstnode.money = identifier1.firstnode.money + dvalue;
											JOptionPane.showMessageDialog(null, "Your balance is "+identifier1.firstnode.money);
											quit1=1;
										}
										else
											JOptionPane.showMessageDialog(null, "Minimum Deposit is 100");
									}while(quit1==0);
								}
								else if(identifier1.firstnode == identifier1.lastnode)
								{
									if(identifier1.firstnode.AccountNumber == identifier.AccountNumber)
										JOptionPane.showMessageDialog(null, "Account Number Already Taken");
									else
									{
										identifier1.firstnode.nextInfo=identifier1.lastnode=identifier;
										identifier1.lastnode.nextInfo = null;
										marker++;
										do
										{
											int dvalue = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter initial deposit\nMinimum of 100!"));
											if(dvalue>=100)
											{
												identifier1.lastnode.money = identifier1.lastnode.money + dvalue;
												JOptionPane.showMessageDialog(null, "Your balance is "+identifier1.lastnode.money);
												quit1=1;
											}
											else
												JOptionPane.showMessageDialog(null, "Minimum Deposit is 100");
										}while(quit1==0);
									}
								}
								else
								{
									Info pointer1;
									for(pointer1 = identifier1.firstnode; pointer1 != null && pointer1.AccountNumber != AccountNumber; pointer1 = pointer1.nextInfo);
									if(pointer1==null)
									{
										identifier1.lastnode.nextInfo=identifier;
										identifier1.lastnode = identifier;
										identifier1.lastnode.nextInfo = null;
										marker++;
										do
										{
											int dvalue = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter initial deposit\nMinimum of 100!"));
											if(dvalue>=100)
											{
												identifier1.lastnode.money = identifier1.lastnode.money + dvalue;
												JOptionPane.showMessageDialog(null, "Your balance is "+identifier1.lastnode.money);
												quit1=1;
											}
											else
												JOptionPane.showMessageDialog(null, "Minimum Deposit is 100");
										}while(quit1==0);
									}
									else
										JOptionPane.showMessageDialog(null, "Account Number Already Taken");
								}
								x=1;
							}
						}
					}while(x==0);
				}
				else if(input.equals("3"))
				{
					int x = 0;
					do
					{
						int ANumber = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter Your Account Number"));
						Info pointer;
						for(pointer = identifier1.firstnode; pointer.AccountNumber != ANumber && pointer != null; pointer = pointer.nextInfo);
						if(pointer == null)
							JOptionPane.showMessageDialog(null, "Account Number not Registered");
						else
						{
							int dvalue = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter desired value to deposit"));
							pointer.money = pointer.money + dvalue;
							JOptionPane.showMessageDialog(null, "Your new balance is "+pointer.money);
							x=1;
						}
					}while(x==0);
				}
				else if(input.equals("4"))
				{
					int x = 0;
					do
					{
						int ANumber = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter Your Account Number"));
						Info pointer;
						for(pointer = identifier1.firstnode; pointer.AccountNumber != ANumber && pointer != null; pointer = pointer.nextInfo);
						if(pointer == null)
							JOptionPane.showMessageDialog(null, "Account Number not Registered");
						else
						{
							int dvalue = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter desired value to withraw\nEnter '0' (Zero) to Cancel"));
							
							if(pointer.money - dvalue <100)
								JOptionPane.showMessageDialog(null, "Transaction Failed \nMust Maintain 100");
							else
							{
								pointer.money = pointer.money - dvalue;
								JOptionPane.showMessageDialog(null, "Your new balance is "+pointer.money);
								x=1;
							}
						}
					}while(x==0);
				}
				else if(input.equals("5"))
				{
					if(identifier1.firstnode == null)
						JOptionPane.showMessageDialog(null, "There is no Account Registered");
					else
					{
						String printer = "";
						Info pointer12;
						for(pointer12 = identifier1.firstnode; pointer12 != null; pointer12 = pointer12.nextInfo)
							printer = printer + pointer12.fname + " " + pointer12.lname + " - " + pointer12.address + " - " + pointer12.bday
									 + " - " + pointer12.identifier3.bname  + " - " + pointer12.identifier3.branch + " - " + pointer12.identifier3.location
									 + " - " + pointer12.identifier3.des + " - " + pointer12.AccountNumber + " - " + pointer12.money + "\n";
						
						System.out.println("----------------------------------");
						System.out.println("----------------------------------");
						System.out.println(printer);
					}
				}
			}catch(Throwable xD){
				JOptionPane.showMessageDialog(null,"Error!");
			}
		}while(quit==0);
	}
}