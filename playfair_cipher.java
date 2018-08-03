import java.util.*;

/**
 * Created by Sapan on 01/08/2018
 */
class playfair_cipher
{ 
    public static void main(String[] args)
    {
		String key1;
		String encrypt="";
		String alpha = "ABCDEFGHJKLMNOPQRSTUVWXYZ";
		String unique="";
		char matrix[][] = new char[5][5];
		int k=0;
		Scanner sc = new Scanner(System.in);

		//Removing spaces and replace 'I' with 'J'
		System.out.println("Enter key");
		key1 = sc.nextLine()+alpha;
		String key = key1.replaceAll("\\s", "").replace("I","J");
		System.out.println("Enter your message");
		String msg = sc.nextLine().replaceAll("\\s", "").replace("I","J");
		
		//Getting all unique characters
		for(int i=0;i<key.length();i++)
		{
			if(!unique.contains(""+key.charAt(i)))
			{
				unique = ""+unique+key.charAt(i);
			}
		}
	
		//Creating matrix of unique chars
		for(int i=0;i<5;i++)
		{
			for(int j=0;j<5;j++)
			{
				matrix[i][j] = unique.charAt(k);
				k++;
			}
		}

		//Printing matrix
		System.out.println("Matrix");
		for(int i=0;i<5;i++)
		{
			for(int j=0;j<5;j++)
			{
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println("");
		}

		//Check msg for double occurences and add 'X' in between
		for(int i=0;i<msg.length();i+=2)
		{
			if(msg.charAt(i)==msg.charAt((i+1)%msg.length()))
			{
				msg= msg.substring(0, i+1)+"X"+msg.substring(i+1, msg.length());
			}
			//System.out.println("Separated multiple occurences = "+msg);
		}
		
		//If length is odd append 'X' at end
		if(msg.length()%2!=0)
		{
			msg+="X";
			//System.out.println("Appending X to msg = "+msg);
		}
		System.out.println("Separating occurences(if there) = "+msg);

		//Encryption Code
		if(msg.length()%2==0)
		{
			for(int i=0;i<msg.length();i+=2)
			{
				String pos =findPos(matrix,msg.charAt(i),msg.charAt(i+1));
				if(pos.charAt(0)==pos.charAt(2))
				{
					//for same row
					int r = Integer.parseInt(pos.charAt(0)+""); 
					int c = (Integer.parseInt(pos.charAt(1)+"")+1)%5;
					int c1 = (Integer.parseInt(pos.charAt(3)+"")+1)%5;
					encrypt=encrypt+matrix[r][c]+matrix[r][c1];
				}
				else if(pos.charAt(1)==pos.charAt(3))
				{
					//for same col
					int r = (Integer.parseInt(pos.charAt(0)+"")+1)%5;
					int r1 = (Integer.parseInt(pos.charAt(2)+"")+1)%5; 
					int c = Integer.parseInt(pos.charAt(1)+"");
					encrypt=encrypt+matrix[r][c]+matrix[r1][c];
				}
				else
				{
					//for diff row and col
					int r = Integer.parseInt(pos.charAt(0)+"");
					int c = Integer.parseInt(pos.charAt(3)+"");
					int r1 = Integer.parseInt(pos.charAt(2)+"");
					int c1 = Integer.parseInt(pos.charAt(1)+"");
					encrypt=encrypt+matrix[r][c]+matrix[r1][c1];
				}
				// System.out.println(pos);
				// System.out.println(msg);	
			}
			System.out.println("Encrypted msg = "+encrypt);
		}
    }

    //Function to find the position of 2 chars
    public static String findPos(char arr[][],char ch,char ch1)
    {
    	String chPos="";
    	String ch1Pos="";
    	for (int i=0;i<5;i++ ) 
    	{
    		for(int j=0;j<5;j++)
    		{
    			if(arr[i][j]==ch)
    			{
    				chPos=chPos+i+j;
    			}
    			else if(arr[i][j]==ch1)
    			{
    				ch1Pos=ch1Pos+i+j;
    			}
    		}
    	}
    	return chPos+ch1Pos; 
    }
}
