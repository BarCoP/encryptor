import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Ceaser_Algo extends Encryption {
	private String pathOfNewFile;
	private byte key;

	/**
	 * Default Constructor - activates Ceaser algorithm.
	 */
	public Ceaser_Algo()
	{
		ActivateCeaserAlgorithm();
	}
	/**
	 * 
	 * @param m - method E or D
	 * @param f - the File
	 * @param p - path of File
	 */
	public Ceaser_Algo(char m , File f , String p,byte k)
	{
		super(m,f,p,k);
		ActivateCeaserAlgorithm(k);
	}
	
	
	private void ActivateCeaserAlgorithm(byte k) {
		System.out.println("Ceaser Algorithm: ");
		//prepare the new name of the file
		pathOfNewFile = this.createThePathOfNewFile(this.pathOfFile);
		
		if(this.method == 'e' || this.method =='E')
		{
			System.out.println("Ceaser - Encrypion: ");
			PrintWriter writer;
			try {
				writer = new PrintWriter(pathOfNewFile, "UTF-8");
				byte temp;
				for (int i = 0; i < contentOfOriginalFile.length; i++) {
					temp = (byte) (contentOfOriginalFile[i] + k);
				

					// Dealing with overflow from MaxValue's side:
					// At first , I wrote the algorithm to handle with the overflow
					// but , when I checked the structure of byte-primitive
					// I saw that the byte-primitive supplies the rules of overflow
					// from both size(MIN value and MAX value) , hence I do not include the term of overflow cases

					this.contentAfterEncDecAlgo[i] = (byte)(temp); //writing to array 
					this.contentAfterEncDecAlgoChar[i] = (char)temp;
					writer.print(this.contentAfterEncDecAlgoChar[i]);  //writing the encrypted char to the file
				}
				writer.close();
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else // this.method == 'D' || this.method=='d'
		{
			System.out.println("Ceaser - Decrypion: ");
			PrintWriter writer;
			try {
				writer = new PrintWriter(pathOfNewFile, "UTF-8"); // creating the new file
				
				for (int i = 0; i < this.lengthOfFile; i++) {
					byte temp;
					 temp = (byte) (this.contentOfOriginalFile[i] - k);
						
					
					this.contentAfterEncDecAlgo[i] = temp; //writing to array 
					this.contentAfterEncDecAlgoChar[i]= (char) temp;
					writer.print(contentAfterEncDecAlgo[i]);  //writing to the new file
				}
				writer.close();
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public Ceaser_Algo(char m , File f , String p,byte k1,byte k2)
	{
		super(m,f,p,k1,k2);
		ActivateCeaserAlgorithmSplit(k1,k2);
	}
	
	
	private void ActivateCeaserAlgorithmSplit(byte k1, byte k2) {
		System.out.println("Ceaser Algorithm: ");
		//prepare the new name of the file
		pathOfNewFile = this.createThePathOfNewFile(this.pathOfFile);
		
		if(this.method == 'e' || this.method =='E')
		{
			System.out.println("Ceaser - Encrypion: ");
			PrintWriter writer;
			try {
				writer = new PrintWriter(pathOfNewFile, "UTF-8");
				byte temp;
				for (int i = 0; i < contentOfOriginalFile.length; i++) {
					if(i%2==1)
					temp = (byte) (contentOfOriginalFile[i] + k1);
					else // i%2==1
						temp = (byte) (contentOfOriginalFile[i] + k2);

					// Dealing with overflow from MaxValue's side:
					// At first , I wrote the algorithm to handle with the overflow
					// but , when I checked the structure of byte-primitive
					// I saw that the byte-primitive supplies the rules of overflow
					// from both size(MIN value and MAX value) , hence I do not include the term of overflow cases

					this.contentAfterEncDecAlgo[i] = (byte)(temp); //writing to array 
					this.contentAfterEncDecAlgoChar[i] = (char)temp;
					writer.print(this.contentAfterEncDecAlgoChar[i]);  //writing the encrypted char to the file
				}
				writer.close();
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else // this.method == 'D' || this.method=='d'
		{
			System.out.println("Ceaser - Decrypion: ");
			PrintWriter writer;
			try {
				writer = new PrintWriter(pathOfNewFile, "UTF-8"); // creating the new file
				
				for (int i = 0; i < this.lengthOfFile; i++) {
					byte temp;
					if (i%2==1)
					 temp = (byte) (this.contentOfOriginalFile[i] - k1);
					else//i%2==0
						 temp = (byte) (this.contentOfOriginalFile[i] - k2);	
					
					this.contentAfterEncDecAlgo[i] = temp; //writing to array 
					this.contentAfterEncDecAlgoChar[i]= (char) temp;
					writer.print(contentAfterEncDecAlgo[i]);  //writing to the new file
				}
				writer.close();
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
	}
	public byte getKey(){return this.key;}
	
	public void ActivateCeaserAlgorithm()
	{
		System.out.println("Ceaser Algorithm: ");
		//prepare the new name of the file
		pathOfNewFile = this.createThePathOfNewFile(this.pathOfFile);
		
		if(this.method == 'e' || this.method =='E')
		{
			System.out.println("Ceaser - Encrypion: ");
			// random the key according the MIN_VALUE and MAX_VALUE of byte's primitive. 
			// the +1 is because of 0
			this.key = (byte)(Math.random()*(Byte.MAX_VALUE + Math.abs(Byte.MIN_VALUE + 1)));
			while(key ==0)
			{
				System.out.println("Key can not be 0 - the encrypt/decrypt will be the same.");
				key = (byte)(Math.random()*(Byte.MAX_VALUE + Math.abs(Byte.MIN_VALUE + 1)));
			}
			System.out.println("The key is: " + this.key);

			PrintWriter writer;
			try {
				writer = new PrintWriter(pathOfNewFile, "UTF-8");
				byte temp;
				for (int i = 0; i < contentOfOriginalFile.length; i++) {
					temp = (byte) (contentOfOriginalFile[i] + key);

					// Dealing with overflow from MaxValue's side:
					// At first , I wrote the algorithm to handle with the overflow
					// but , when I checked the structure of byte-primitive
					// I saw that the byte-primitive supplies the rules of overflow
					// from both size(MIN value and MAX value) , hence I do not include the term of overflow cases

					this.contentAfterEncDecAlgo[i] = (byte)(temp); //writing to array 
					this.contentAfterEncDecAlgoChar[i] = (char)temp;
					writer.print(this.contentAfterEncDecAlgoChar[i]);  //writing the encrypted char to the file
				}
				writer.close();
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else // this.method == 'D' || this.method=='d'
		{
			System.out.println("Ceaser - Decrypion: ");
			PrintWriter writer;
			try {
				writer = new PrintWriter(pathOfNewFile, "UTF-8"); // creating the new file
				@SuppressWarnings("resource")
				Scanner sc =new Scanner(System.in);
				System.out.println("Enter the key for decrypt :");
				byte t = sc.nextByte();
				this.setKey(t);

				for (int i = 0; i < this.lengthOfFile; i++) {
					byte temp = (byte) (this.contentOfOriginalFile[i] - key);

					this.contentAfterEncDecAlgo[i] = temp; //writing to array 
					this.contentAfterEncDecAlgoChar[i]= (char) temp;
					writer.print(contentAfterEncDecAlgo[i]);  //writing to the new file
				}
				writer.close();
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	


}
