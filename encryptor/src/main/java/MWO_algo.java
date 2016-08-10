import java.io.File;
import java.util.Scanner;

public class MWO_algo extends Encryption {
	/**
	 *  default Constructor
	 * implementation of MWO algorithm
	 * in the end of the program, the program will display the time which took to the 
	 * MWO algorithm to work.
	 */
	public MWO_algo () {
		activateMWOAlgorithm();
	}
	/**
	 * constructor
	 * @param m - method 'E' or 'D' -case-insensitive
	 * @param f - the File
	 * @param p - path of File
	 * @param k - the key;
	 */
	public MWO_algo (char m , File f , String p,byte k) {
		super(m,f,p,k);
		activateMWOAlgorithm(k);
	}
	/**
	 * constructor
	 * @param m - method 'E' or 'D' -case-insensitive
	 * @param f - the File
	 * @param p - path of File
	 * @param k1- first key.
	 * @param k2- seconf key.
	 */
	public MWO_algo (char m , File f , String p,byte k1,byte k2) {
		super(m,f,p,k1,k2);
		activateMWOAlgorithm(k1,k2);
	}

	/**
	 * The function activates MWO algorithm
	 * this function is for Split algorithm (requested 2 keys)
	 * the user will get a message for the time which took to MWO to work.
	 * @param k1 - first key 
	 * @param k2 - second key
	 */
	private void activateMWOAlgorithm(byte k1, byte k2) {
		System.out.println("MWO algorithm start:");
		long startTime = System.currentTimeMillis(); // start calculate the time
		if(this.method == 'e' || this.method == 'E')
		{
			System.out.println("Encryption :");
			
			try{
				if(k1 %2 == 0 )
					throw new Key_is_Even_or_zero_Exception();   //<=  throw the costume exception of illegal key

			}catch(Key_is_Even_or_zero_Exception e)
			{
				if(k1 == 0) System.out.println("illegal first key ! key can not be zero ! ");  // message in case that the key is 0
				if(k1 != 0 && k1%2==0)
					System.out.println("illegal first key ! key can not be an even number!(can create a loss of  data))"); // message in case that the key is even
			}
			try{
				if(k2 %2 == 0 )
					throw new Key_is_Even_or_zero_Exception();   //<=  throw the costume exception of illegal key

			}catch(Key_is_Even_or_zero_Exception e)
			{
				if(k2 == 0) System.out.println("illegal second key ! key can not be zero ! ");  // message in case that the key is 0
				if(k2 != 0 && k2%2==0)
					System.out.println("illegal second key ! key can not be an even number!(can create a loss of  data))"); // message in case that the key is even
			}
			
			
			
			
			
			
			

			for (int i = 0; i < this.contentOfOriginalFile.length; i++) {
				byte temp;
				if(i%2==1)
					temp = (byte)(contentOfOriginalFile[i] * k1); // <= MWO operation
				else//i%2==0
					temp = (byte)(contentOfOriginalFile[i] * k2); // <= MWO operation
				this.contentAfterEncDecAlgo[i] =temp;
				this.contentAfterEncDecAlgoChar[i]=(char)temp;
			}
		}
		else//this.method == 'd' || this.method == 'D'
		{
			System.out.println("Decryption :");
			for (int i = 0; i < contentOfOriginalFile.length; i++) {
				byte temp;
				if(i%2==1)
					temp = (byte)(this.contentOfOriginalFile[i] * k1) ;
				else // i%2==0
					temp = (byte)(this.contentOfOriginalFile[i] * k2) ;
				this.contentAfterEncDecAlgo[i]=temp;
				this.contentAfterEncDecAlgoChar[i]=(char)temp;
			}
		}
		String res=this.contentAfterEncDecAlgoChar.toString();
		System.out.println("the result is: "+ res );


		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("time of MWO algorithm: "+totalTime);
		System.out.println("MWO algorithm end");


	}
	/**
	  The function activates MWO algorithm
	 * the user will get a message for the time which took to MWO to work.
	 * @param k - the key 
	 */
	private void activateMWOAlgorithm(byte k) {
		System.out.println("MWO algorithm start:");
		long startTime = System.currentTimeMillis(); // start calculate the time
		if(this.method == 'e' || this.method == 'E')
		{
			System.out.println("Encryption :");

			try{
				if(k %2 == 0 )
					throw new Key_is_Even_or_zero_Exception();   //<=  throw the costume exception of illegal key

			}catch(Key_is_Even_or_zero_Exception e)
			{
				if(k == 0) System.out.println("illegal key ! key can not be zero ! ");  // message in case that the key is 0
				if(k != 0 && this.key%2==0)
					System.out.println("illegal key ! key can not be an even number!(can create a loss of  data))"); // message in case that the key is even
			}
			for (int i = 0; i < this.contentOfOriginalFile.length; i++) {
				byte temp = (byte)(contentOfOriginalFile[i] * k); // <= MWO operation
				this.contentAfterEncDecAlgo[i] =temp;
				this.contentAfterEncDecAlgoChar[i]=(char)temp;
			}

		}
		else//this.method == 'd' || this.method == 'D'
		{
			System.out.println("Decryption :");
			try{
				if(k %2 == 0 )
					throw new Key_is_Even_or_zero_Exception();   //<=  throw the costume exception of illegal key

			}catch(Key_is_Even_or_zero_Exception e)
			{
				if(k == 0) System.out.println("illegal key ! key can not be zero ! ");  // message in case that the key is 0
				if(k != 0 && this.key%2==0)
					System.out.println("illegal key ! key can not be an even number!(can create a loss of  data))"); // message in case that the key is even
			}

			for (int i = 0; i < contentOfOriginalFile.length; i++) {
				byte temp = (byte)(this.contentOfOriginalFile[i] * k) ;
				this.contentAfterEncDecAlgo[i]=temp;
				this.contentAfterEncDecAlgoChar[i]=(char)temp;
			}
		}
		String res=this.contentAfterEncDecAlgoChar.toString();
		System.out.println("the result is: "+ res );


		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("time of MWO algorithm: "+totalTime);
		System.out.println("MWO algorithm end");


	}
	/**
	 * get function for the key.
	 */
	public byte getKey(){return this.key;}

	/**
	 *  Default function activates MWO algorithm
	 * the user will get a message for the time which took to MWO to work.
	 * The Function will throws Exception for the user if the key he entered is not illegal.
	 */
	public void activateMWOAlgorithm()
	{
		System.out.println("MWO algorithm start:");
		long startTime = System.currentTimeMillis(); // start calculate the time
		if(this.method == 'e' || this.method == 'E')
		{
			System.out.println("Encryption :");
			sc = new Scanner(System.in);
			System.out.println("enter a key to encrypt (key need to be odd && !=0 ) :");
			try{
				this.key =sc.nextByte();
				if(this.key %2 == 0 )
					throw new Key_is_Even_or_zero_Exception();   //<=  throw the costume exception of illegal key

			}catch(Key_is_Even_or_zero_Exception e)
			{
				if(this.key == 0) System.out.println("illegal key ! key can not be zero ! ");  // message in case that the key is 0
				if(this.key != 0 && this.key%2==0)
					System.out.println("illegal key ! key can not be an even number!(can create a loss of  data))"); // message in case that the key is even

				while(this.key %2 == 0)
				{
					System.out.println("illegal key ! try again :");
					System.out.println("enter a key to encrypt (key need to be odd && !=0 ) :");
					this.key =sc.nextByte();
				}
			}
			for (int i = 0; i < this.contentOfOriginalFile.length; i++) {
				byte temp = (byte)(contentOfOriginalFile[i] * this.key); // <= MWO operation
				this.contentAfterEncDecAlgo[i] =temp;
				this.contentAfterEncDecAlgoChar[i]=(char)temp;
			}
		}
		else//this.method == 'd' || this.method == 'D'
		{
			byte decKey=1;
			System.out.println("Decryption :");
			System.out.println("Find the decrypted key :");
			boolean isFoundDecKey = false;
			for (byte i = Byte.MIN_VALUE ; (!isFoundDecKey && i <= Byte.MAX_VALUE ) ; i++) {
				byte temp = (byte)(this.contentOfOriginalFile[0] * i);
				if( temp == 1) // this.contentOfOriginalFile[0] -> the first MWO
				{
					decKey=i;
					isFoundDecKey = true;
				}
			}
			if(!isFoundDecKey)
				System.out.println("problem , could not find the Dec key");
			else
			{
				System.out.println("The decrypted key  is : " + decKey);

				for (int i = 0; i < contentOfOriginalFile.length; i++) {
					byte temp = (byte)(this.contentOfOriginalFile[i] * decKey) ;
					this.contentAfterEncDecAlgo[i]=temp;
					this.contentAfterEncDecAlgoChar[i]=(char)temp;
				}
			}
			String res=this.contentAfterEncDecAlgoChar.toString();
			System.out.println("the result is: "+ res );

		}
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("time of MWO algorithm: "+totalTime);
		System.out.println("MWO algorithm end");
	}

}
/**
 * Class Key_is_Even_or_zero_Exception
 * @author Bar
 * created for throwing an Exception if the key will be 
 * illegal (key == 0 ) or (key is even)
 */
class Key_is_Even_or_zero_Exception extends Exception
{
	public Key_is_Even_or_zero_Exception(){}

	public Key_is_Even_or_zero_Exception(String message)
	{
		super(message);
	}

}
