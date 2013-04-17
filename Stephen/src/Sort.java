import javax.swing.*;
public class Sort 
{
	public static void main(String[] args) 
	{
		int input1 = Integer.parseInt(JOptionPane.showInputDialog(null, "Input Desired Number of Numbers"));
		int array[] = new int[input1];
		for(int x=0; x!=input1; x++)
		{
			int input2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Input Number"));
			array[x]=input2;
		}
		int y = 0;
			while(y!=input1-1)//descending
			{
			
					if(array[y]>=array[y+1])
						y++;
					else
					{
						int sum = array[y] + array[y+1];
						array[y] = sum - array[y];
						array[y+1] = sum - array[y+1];
						y=0;
					}
				
			}
			System.out.print("Descending \n");
			for(int print = 0; print!=input1;print++)
				System.out.print(array[print]+" ");
			
			System.out.print("\nAscending\n");
			for(int print1 = input1; print1!=0;print1--)
				System.out.print(array[print1-1]+" ");

	}

}
