import javax.swing.*;
public class Reverse 
{
	public static void main (String [] args)
	{
		String input = JOptionPane.showInputDialog(null, "Input Message");
		int y = input.length();
		for(int x=0; x!=input.length();x++)
		{
			System.out.print(input.charAt(--y));
		}
		
	}

}
