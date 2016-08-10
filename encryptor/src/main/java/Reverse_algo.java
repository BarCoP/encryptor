import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.omg.Messaging.SyncScopeHelper;

public class Reverse_algo extends MoreEncAlgo {
	byte key;
/**
 * Constructor
 * @param f - the File
 * @param p - path of file
 */
	public Reverse_algo(File f, String p) {
		super(f, p);
		 activateReverseAlgo();
	}
/**
 * Activate the Reverse algorithm
 * if user chose 'E' - the algorithm will perform decryption
 * if user chose 'D' - the algorithm will perform encryption
 */
	public void activateReverseAlgo()
	{
		//encryption with decryption
		
		//reading the generate key for encryption
		try {
			this.theKey = readKey(key,pathOfKeyFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if(this.method =='e' || this.method=='E')
		{
			if(this.algoUserChoose == 'c' || this.algoUserChoose =='C')
			{
				System.out.println("Encrypt Ceaser reverse with decrypt action: ");
				c=new Ceaser_Algo('d', this.theFile, this.pathOfFile,this.theKey.getFirstKey()); // <= the reverse action indicator
			}
			else if(this.algoUserChoose == 'x' || this.algoUserChoose =='X')
			{
				System.out.println("Encrypt XOR reverse with decrypt action: ");
				xorA=new XOR_Algo('d', this.theFile, this.pathOfFile,this.theKey.getFirstKey()); // <= the reverse action indicator
			}
			else //this.algoUserChoose == 'm' || this.algoUserChoose =='M'
			{
				System.out.println("Encrypt MWO reverse with decrypt action: ");
				mwo=new MWO_algo('d', this.theFile, this.pathOfFile,this.theKey.getFirstKey()); // <= the reverse action indicator	
			}
		}
		//decryption with encryption
		else // this.method =='d' || this.method=='D'
		{
			System.out.println("Enter the path of the key - file :");
			Scanner sc = new Scanner(System.in);
			this.pathOfKeyFile = sc.nextLine();
			try {
				this.theKey = readKey(key,pathOfKeyFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if(this.algoUserChoose == 'c' || this.algoUserChoose =='C')
			{
				System.out.println("Decrypt Ceaser reverse with decrypt action: ");
				c=new Ceaser_Algo('e', this.theFile, this.pathOfFile,this.theKey.getFirstKey()); // <= the reverse action indicator
			}
			else if(this.algoUserChoose == 'x' || this.algoUserChoose =='X')
			{
				System.out.println("Decrypt XOR reverse with decrypt action: ");
				xorA=new XOR_Algo('e', this.theFile, this.pathOfFile,this.theKey.getFirstKey()); // <= the reverse action indicator
			}
			else //this.algoUserChoose == 'm' || this.algoUserChoose =='M'
			{
				System.out.println("Decrypt MWO reverse with decrypt action: ");
				mwo=new MWO_algo('e', this.theFile, this.pathOfFile,this.theKey.getFirstKey()); // <= the reverse action indicator	
			}
		}
	}
	
}
