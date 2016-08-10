import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Split_Algo extends MoreEncAlgo {
	private byte key1;
	private byte key2;
	/**
	 * constructor
	 * @param f - the File
	 * @param p - path of file
	 */
	public Split_Algo(File f, String p) {
		super(f, p);
		activateSplitAlgorithm();
	}
/**
 * The function activate the Split algorithm
 */
	private void activateSplitAlgorithm() {
		//encryption
		if(this.method == 'e' || this.method=='E')
		{

			System.out.println("Encryption");
			try{
				this.theKey = readKey(key1, key2,pathOfKeyFile);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			if(this.algoUserChoose == 'c' || this.algoUserChoose =='C')
			{
				System.out.println("Encrypt Ceaser  with decrypt action: ");
				c=new Ceaser_Algo(this.method, this.theFile, this.pathOfFile,this.theKey.getFirstKey(),this.theKey.getSecondKey()); // <= the reverse action indicator
			}
			else if(this.algoUserChoose == 'x' || this.algoUserChoose =='X')
			{
				System.out.println("Encrypt XOR  with decrypt action: ");
				xorA=new XOR_Algo(this.method, this.theFile, this.pathOfFile,this.theKey.getFirstKey(),this.theKey.getSecondKey()); // <= the reverse action indicator
			}
			else //this.algoUserChoose == 'm' || this.algoUserChoose =='M'
			{
				System.out.println("Encrypt MWO  with decrypt action: ");
				mwo=new MWO_algo(this.method, this.theFile, this.pathOfFile,this.theKey.getFirstKey(),this.theKey.getSecondKey()); // <= the reverse action indicator	
			}
			
			
			
			
		}
		//decryption
		else //this.method == 'd' || this.method=='D'
		{
			System.out.println("Decryption");
			
			System.out.println("Enter the path of the key - file :");
			Scanner sc = new Scanner(System.in);
			this.pathOfKeyFile = sc.nextLine();
			try {
				this.theKey = readKey(key1,key2,pathOfKeyFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(this.algoUserChoose == 'c' || this.algoUserChoose =='C')
		{
			System.out.println("Encrypt Ceaser  with decrypt action: ");
			c=new Ceaser_Algo(this.method, this.theFile, this.pathOfFile,this.theKey.getFirstKey(),this.theKey.getSecondKey()); // <= the reverse action indicator
		}
		else if(this.algoUserChoose == 'x' || this.algoUserChoose =='X')
		{
			System.out.println("Encrypt XOR  with decrypt action: ");
			xorA=new XOR_Algo(this.method, this.theFile, this.pathOfFile,this.theKey.getFirstKey(),this.theKey.getSecondKey()); // <= the reverse action indicator
		}
		else //this.algoUserChoose == 'm' || this.algoUserChoose =='M'
		{
			System.out.println("Encrypt MWO  with decrypt action: ");
			mwo=new MWO_algo(this.method, this.theFile, this.pathOfFile,this.theKey.getFirstKey(),this.theKey.getSecondKey()); // <= the reverse action indicator	
		}

	}




}



