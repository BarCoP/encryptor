import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Double_Algo extends MoreEncAlgo {
	char algo2UserChoose;
	private Scanner sc2;
	byte key1;
	byte key2;
	
	
/**
 * constructor	
 * 
 * @param f - the File
 * @param p - path of file
 */
	public Double_Algo(File f, String p) {
		super(f, p);
		
		System.out.println("Enter the second algorithm: ");
		sc2 =new Scanner(System.in);
		this.algo2UserChoose = sc2.next().charAt(0);
		while(this.algoUserChoose !='c' && this.algoUserChoose !='C' 
				&& this.algoUserChoose !='x' && this.algoUserChoose !='X'
				&& this.algoUserChoose !='m' && this.algoUserChoose !='M')
		{
			System.out.println("Incorrect input ! try again :");
			System.out.println("Enter the second algorithm : ");
			System.out.println("'C' for Ceaser Algorithm,");
			System.out.println("'X' for Xor Algorithm,");
			System.out.println("or 'M' for MWO Algorithm :");
			System.out.println("Enter the second algo :");
			this.algo2UserChoose = sc2.next().charAt(0);
			while(this.algoUserChoose == this.algo2UserChoose)  // <= case the user choose the same algorithm
			{
				System.out.println("You entred the same algorithm twice ! ");
				System.out.println("The algoritms has to be diffrent !");
				System.out.println("enter algo 2 again:");
				this.algo2UserChoose = sc2.next().charAt(0);
			}
		}
		activateDoubleAlgo();
	}

	

	/**
	 * The function activates the Double Algorithm itself :
	 */
	public void activateDoubleAlgo()
	{
		//  =====  start Double Algorithm : ====
		//Encryption :
		if(this.method =='e' || this.method=='E')    
		{
			//reading the generate key for encryption
			try {
				this.theKey = readKey(key1, key2,pathOfKeyFile);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(this.algoUserChoose == 'c' || this.algoUserChoose =='C')
			{
				System.out.println("First algorithm - Ceaser :");
				c = new Ceaser_Algo(this.method,this.theFile, this.pathOfFile,this.theKey.getFirstKey());
				key1=c.getKey();
				if(algo2UserChoose == 'x' || algo2UserChoose =='X')
				{
					System.out.println("The second algorithm is XOR algorithm: ");
					xorA = new XOR_Algo(this.method,this.theFile, this.pathOfFile,this.theKey.getSecondKey());
					key2=xorA.getKey();
				}
				else //algo2 == 'm' || algo2 =='M'
				{
					System.out.println("The second algorithm is MWE algorithm: ");
					mwo= new MWO_algo(this.method,this.theFile, this.pathOfFile,this.theKey.getSecondKey());
					key2=mwo.getKey();
				}

			}
			else if(this.algoUserChoose == 'x' || this.algoUserChoose =='X')
			{
				System.out.println("First algorithm to encrypt- XOR :");
				xorA =new XOR_Algo(this.method,this.theFile, this.pathOfFile,this.theKey.getFirstKey());
				key1=xorA.getKey();
				if(algo2UserChoose == 'c' || algo2UserChoose =='C')
				{
					System.out.println("The second algorithm for encryption is Ceaser algorithm: ");
					c = new Ceaser_Algo(this.method,this.theFile, this.pathOfFile,this.theKey.getSecondKey());
					key2=c.getKey();
				}
				else //algo2 == 'm' || algo2 =='M'
				{
					System.out.println("The second algorithm for encryption is MWE algorithm: ");
					mwo= new MWO_algo(this.method,this.theFile, this.pathOfFile,this.theKey.getSecondKey());
					key2=mwo.getKey();
				}

			}
			else //algo1 == 'm' || algo1 =='M'
			{
				System.out.println("First algorithm for encryption - MWO :");
				mwo = new MWO_algo(this.method,this.theFile, this.pathOfFile,this.theKey.getFirstKey());
				key1=mwo.getKey();

				if(algo2UserChoose == 'x' || algo2UserChoose =='X')
				{
					System.out.println("The second algorithm for encryption is XOR algorithm: ");
					xorA = new XOR_Algo(this.method,this.theFile, this.pathOfFile,this.theKey.getSecondKey());
					key2=xorA.getKey();
				}
				else //algo2 == 'c' || algo2 =='C'
				{
					System.out.println("The second algorithm for encryption is Ceaser algorithm: ");
					c = new Ceaser_Algo(this.method,this.theFile, this.pathOfFile,this.theKey.getSecondKey());
					key2=c.getKey();
				}
			}
			this.theKey = new Key(key1,key2);
			this.createKeyFile(key1, key2);
		}
		//Decryption :
		else //this.method =='d' || this.method=='D'
		{
			System.out.println("Enter the path of the key - file :");
			Scanner sc = new Scanner(System.in);
			this.pathOfKeyFile = sc.nextLine();
			try {
				this.theKey = readKey(key1,key2,pathOfKeyFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(algo2UserChoose == 'c' || algo2UserChoose =='C')
			{
				System.out.println("First algorithm for decryption - Ceaser :");
				c = new Ceaser_Algo(this.method,this.theFile, this.pathOfFile,this.theKey.getFirstKey());
				if(this.algoUserChoose =='x' || this.algoUserChoose =='X')
				{
					System.out.println("Second algorithm for decryption - XOR");
					xorA = new XOR_Algo(this.method,this.theFile, this.pathOfFile,this.theKey.getSecondKey());
				}
				else // algo1 =='m' || algo1 =='M'
				{
					System.out.println("Second algorithm for decryption - MWO");
					mwo=new MWO_algo(this.method,this.theFile, this.pathOfFile,this.theKey.getSecondKey());
				}
			}
			else if(algo2UserChoose == 'x' || algo2UserChoose =='X')
			{
				System.out.println("First algorithm for decryption - XOR :");
				xorA =new XOR_Algo(this.method,this.theFile, this.pathOfFile,this.theKey.getFirstKey());
				if(this.algoUserChoose =='c' || this.algoUserChoose =='C')
				{
					System.out.println("Second algorithm for decryption - Ceaser");
					c = new Ceaser_Algo(this.method,this.theFile, this.pathOfFile,this.theKey.getSecondKey());
				}
				else // algo1 =='m' || algo1 =='M'
				{
					System.out.println("Second algorithm for decryption - MWO");
					mwo=new MWO_algo(this.method,this.theFile, this.pathOfFile,this.theKey.getSecondKey());
				}
				
			}
			else // algo2 == 'm' || algo2 =='M'
			{
				System.out.println("First algorithm for decryption - MWO :");
				mwo = new MWO_algo(this.method,this.theFile, this.pathOfFile,this.theKey.getFirstKey());
				if(this.algoUserChoose =='c' || this.algoUserChoose =='C')
				{
					System.out.println("Second algorithm for decryption - Ceaser");
					c = new Ceaser_Algo(this.method,this.theFile, this.pathOfFile,this.theKey.getSecondKey());
				}
				else // algo1 =='x' || algo1 =='X'
				{
					System.out.println("Second algorithm for decryption - XOR");
					xorA=new XOR_Algo(this.method,this.theFile, this.pathOfFile,this.theKey.getSecondKey());
				}
			}
		}// else -decryption
	}
}