import java.io.File;

public class XOR_Algo extends Encryption {

	/**
	 * default constructor
	 * implementation of XOR (^) algorithm
	 * in the end , a message will display to the user ,  
	 * with the time that took for XOR algorithm to work.
	 */
	public XOR_Algo()
	{
		activateXORAlgorithm();
	}

	/**
	 *  constructor
	 * implementation of XOR (^) algorithm
	 * in the end , a message will display to the user ,  
	 * with the time that took for XOR algorithm to work.
	 * @param m - the method('E' or 'D' -case-insensitive)
	 * @param f - the File
	 * @param p - path of the file
	 * @param k - key for the algorithm
	 */
	public XOR_Algo(char m , File f , String p,byte k)
	{
		super(m,f,p,k);
		activateXORAlgorithm(k);
	}

	/**
	 *  constructor
	 * implementation of XOR (^) algorithm
	 * in the end , a message will display to the user ,  
	 * with the time that took for XOR algorithm to work.
	 * @param m - the method('E' or 'D' -case-insensitive)
	 * @param f - the File
	 * @param p - path of the file
	 * @param k1 - first key for the algorithm
	 * @param k2 - second key for the algorithm
	 */
	public XOR_Algo(char m , File f , String p,byte k1,byte k2)
	{
		super(m,f,p,k1,k2);
		activateXORAlgorithmSplit(k1,k2);
	}

	/**
	 *  The function will activates the algorithm of XOR.
	 * both the method - Encryption & Decryption are work 
	 * at the same way (XOR operation) 
	 * the user will get a message of the type of the method
	 * the function is for Split algorithm
	 * (demands 2 keys for 1 algorithm - one for odd-bytes and the second for even-byte )
	 * the function will display the time which took it to run.
	 * @param k1 - the first key
	 * @param k2 - the second key
	 */
	private void activateXORAlgorithmSplit(byte k1, byte k2) {
		System.out.println("XOR algorithm start : ");
		long startTime = System.currentTimeMillis(); // <= start calculate time of XOR algo

		if(this.method == 'E' || this.method=='e')
			System.out.println("XOR Encryption :");
		else 
			System.out.println("XOR Decryption :");

		for (int i = 0; i < this.contentOfOriginalFile.length; i++) {
			byte temp;
			if(i%2==1)
				temp = (byte) (contentOfOriginalFile[i] ^ k1);  // <= XOR (^) operation 
			else //i%2==1
				temp = (byte) (contentOfOriginalFile[i] ^ k2);  // <= XOR (^) operation
			this.contentAfterEncDecAlgo[i] = temp;
			this.contentAfterEncDecAlgoChar[i] =(char)temp;
		}
		long endTime   = System.currentTimeMillis(); // < = end of calculate rime of XOR algo
		long totalTime = endTime - startTime;
		System.out.println("Xor algorithm end");
		System.out.println("Total time of XOR algorithm: "+totalTime);

	}
	/**
	 * The function will activates the algorithm of XOR.
	 * both the method - Encryption & Decryption are work 
	 * at the same way (XOR operation) 
	 * the user will get a message of the type of the method
	 * the function will display the time which took it to run.
	 * @param k - the key
	 */
	private void activateXORAlgorithm(byte k) {
		System.out.println("XOR algorithm start : ");
		long startTime = System.currentTimeMillis(); // <= start calculate time of XOR algo

		if(this.method == 'E' || this.method=='e')
			System.out.println("XOR Encryption :");
		else 
			System.out.println("XOR Decryption :");

		for (int i = 0; i < this.contentOfOriginalFile.length; i++) {
			byte temp = (byte) (contentOfOriginalFile[i] ^ k);  // <= XOR (^) operation
			this.contentAfterEncDecAlgo[i] = temp;
			this.contentAfterEncDecAlgoChar[i] =(char)temp;
		}
		long endTime   = System.currentTimeMillis(); // < = end of calculate rime of XOR algo
		long totalTime = endTime - startTime;
		System.out.println("Xor algorithm end");
		System.out.println("Total time of XOR algorithm: "+totalTime);

	}
	/**
	 * get function of key
	 */
	public byte getKey(){return this.key;}




	/**
	 * a default activate-function of XOR algorithm.
	 * The function gets an input from the user to byte key
	 * the function will display the time which took it to run. 
	 */
	public void activateXORAlgorithm()
	{
		System.out.println("Enter the key :");
		byte t = sc.nextByte();
		while(t<Byte.MIN_VALUE || t > Byte.MAX_VALUE)
		{
			System.out.println("Error ! inpute is not in the range of Byte's value");
			System.out.println("Try again , enter the key :");
			t = sc.nextByte();
		}
		this.key=t;
		System.out.println("the key is: "+key);
		System.out.println("XOR algorithm start : ");
		long startTime = System.currentTimeMillis(); // <= start calculate time of XOR algo

		if(this.method == 'E' || this.method=='e')
			System.out.println("XOR Encryption :");
		else 
			System.out.println("XOR Decryption :");

		for (int i = 0; i < this.contentOfOriginalFile.length; i++) {
			byte temp = (byte) (contentOfOriginalFile[i] ^ key);  // <= XOR (^) operation
			this.contentAfterEncDecAlgo[i] = temp;
			this.contentAfterEncDecAlgoChar[i] =(char)temp;
		}
		long endTime   = System.currentTimeMillis(); // < = end of calculate rime of XOR algo
		long totalTime = endTime - startTime;
		System.out.println("Xor algorithm end");
		System.out.println("Total time of XOR algorithm: "+totalTime);
	}
}
