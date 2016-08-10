import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class MoreEncAlgo {
	protected Ceaser_Algo c;
	protected XOR_Algo xorA;
	protected MWO_algo mwo;
	protected char method;
	protected char algoUserChoose;
	
	protected File theFile;
	protected String pathOfFile;
	
	protected Key theKey;
	protected File fileOfKey;
	protected String pathOfKeyFile = "C:\\Users\\USER\\Desktop\\key.bin"; //<= the path
	
	/**
	 * Constructor
	 * @param f - the File
	 * @param p - path of file
	 */
	public MoreEncAlgo(File f , String p)
	{
		this.theFile=f;
		this.pathOfFile=p;
		System.out.println("Enter the Method you would like to activate: ");
		System.out.println("'E' for Encryption,");
		System.out.println("'D' for Decryption: ");
		Scanner sc = new Scanner (System.in);
		this.method=sc.next().charAt(0);
		while(this.method!='e' && this.method !='E' 
				&& this.method!='d' && this.method !='D')
		{
			System.out.println("Wrong input , please try again: ");
			System.out.println("Enter the Method you would like to activate: ");
			System.out.println("'E' for Encryption,");
			System.out.println("'D' for Decryption: ");
			sc = new Scanner (System.in);
			this.method=sc.next().charAt(0);
		}
		System.out.println("Choose algorithm to implements the algorithm : ");
		System.out.println("'C' for Ceaser Algorithm,");
		System.out.println("'X' for Xor Algorithm,");
		System.out.println("or 'M' for MWO Algorithm :");
		System.out.println("Enter the  algorithm :");
		sc = new Scanner(System.in);
		algoUserChoose = sc.next().charAt(0);
		while(algoUserChoose !='c' && algoUserChoose !='C' 
				&& algoUserChoose !='x' && algoUserChoose !='X'
				&& algoUserChoose !='m' && algoUserChoose !='M')
		{
			System.out.println("Incorrect input ! try again :");
			System.out.println("enter the algorithm : ");
			System.out.println("'C' for Ceaser Algorithm,");
			System.out.println("'X' for Xor Algorithm,");
			System.out.println("or 'M' for MWO Algorithm :");
			System.out.println("Enter the algorithm: ");
			sc= new Scanner(System.in);
			algoUserChoose = sc.next().charAt(0);
		}//while
	}
	/**
	 * create the key- file (ObjectOutputStream) key.bin
	 * @param key - the key
	 */
	public void createKeyFile(byte key)
	{
		try {
			FileOutputStream fos = new FileOutputStream(this.pathOfKeyFile);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeByte(key);
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * create the key- file (ObjectOutputStream) key.bin
	 * @param key1 - first Key
	 * @param key2 - second Key
	 */
	public void createKeyFile(byte key1,byte key2)
	{
		try {
			FileOutputStream fos = new FileOutputStream(this.pathOfKeyFile);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeByte(key1);
			os.writeByte(key2);
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * read from file the key(extract the data from key.bin)
	 * @param ke - key
	 * @param s - path of key
	 * @return Key object
	 * @throws IOException case of input is incorrect from the user
	 */
	public Key readKey(byte ke,String s) throws IOException
	{
		FileInputStream fis = new FileInputStream(s);
		ObjectInputStream is = new ObjectInputStream(fis);
		byte k1 = is.readByte();
		is.close();
		Key k = new Key(k1);
		return k;
	}
	/**
	 ** read from file the key(extract the data from key.bin)
	 * @param k1 - first key
	 * @param k2 -second key
	 * @param s - path of key
	 * @return Key object
	 * @throws IOException case of input is incorrect from the user
	 */
	public Key readKey(byte k1 , byte k2,String s) throws IOException
	{
		FileInputStream fis = new FileInputStream(s);
		ObjectInputStream is = new ObjectInputStream(fis);
		byte key1 = is.readByte();
		byte key2 = is.readByte();
		is.close();
		Key k = new Key(key1 , key2);
		return k;
	}
	
}

class Key
{
	private byte firstKey;
	private byte secondKey;
	
	public Key(byte k1, byte k2)
	{
		this.firstKey=k1;
		this.secondKey=k2;
	}
	
	public Key(byte k)
	{
		this.firstKey=k;
	}
	
	
	public byte getFirstKey(){return this.firstKey;}
	public byte getSecondKey(){return this.secondKey;}
}



