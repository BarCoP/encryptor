import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

//C:\\Users\\USER\\Desktop\\test.txt
//C:\\Users\\USER\\Desktop\\test.encrypted.txt 
//C:\\Users\\USER\\Desktop\\test_decrypted.[original_file_Extension].txt

public class Encryption {
	protected File theFile;
	protected String pathOfFile;
	protected char method;
	protected byte key;
	protected byte key2;
	protected int lengthOfFile;
	protected boolean doActivateAlgo;
	protected Scanner sc;
	protected String contentOfFile; 

	protected byte [] contentOfOriginalFile;
	protected char [] contentOfOriginalFileChar;

	protected byte [] contentAfterEncDecAlgo;
	protected char [] contentAfterEncDecAlgoChar;
	//==========
	
	public byte getKey(){return this.key;}
	/**
	 * Set the value of key , if the argument is not in
	 * the range of Byte - value , the function will not allowed the change
	 * @param k
	 */
	public void setKey(byte k)
	{
		if( k> Byte.MAX_VALUE || k < Byte.MIN_VALUE)
		{
			System.out.println("Error ! out of bound of byte's valus");
		}
		else 
			this.key=k;
	}

	public char getMethod(){return this.method;}
	public void setMethod(char m){ this.method=m;}

	public String getPath(){return this.pathOfFile;}
	public void setPath(String path){this.pathOfFile=path;}

	public boolean getDoActivateAlgo(){return this.doActivateAlgo;}
	public void setDoActivateAlgo(boolean b){this.doActivateAlgo=b;}
	//=========== 

	/**
	 * The Default constructor.  
	 */
	public Encryption()
	{
		this.contentOfFile = "";
		this.method=chooseMethod();	
		this.theFile=getTheFile();
		this.pathOfFile=this.theFile.getPath();
		
		initializeEncryptor();
	}
	//==================
	/**
	 * Constructor. 
	 * initialize all the variables : the help-array,the data of the file.
	 * constructor with 1 value-key. 
	 * @param m - the method ('E' for Encryption , 'D' for Decryption)
	 * @param f - the File
	 * @param p - path of the File.
	 * @param k - the key
	 */
	public Encryption(char m,File f,String p,byte k)
	{
		this.contentOfFile = "";
		this.method=m;	
		this.theFile=f;
		this.pathOfFile=p;
		this.key=k;
		
		initializeEncryptor();
	}
	
	/**
	 * initialize all the variables : the help-array,the data of the file.
	 * constructor with 1 value-key.
	 * @param m - the method ('E' for Encryption , 'D' for Decryption)
	 * @param f - the File
	 * @param p - path of the File.
	 * @param k1 - first key
	 * @param k2 - second key
	 */
	public Encryption(char m,File f,String p,byte k1,byte k2)
	{
		this.contentOfFile = "";
		this.method=m;	
		this.theFile=f;
		this.pathOfFile=p;
		this.key=k1;
		this.key2=k2;
		
		initializeEncryptor();
	}
	
	/**
	 * The function initialize all the variables : 
	 * the help-arrays, read the data from the file to the 
	 * help-array.
	 */
	public void initializeEncryptor()
	{
		this.lengthOfFile = (int)this.theFile.length();
		this.contentOfOriginalFile = new byte [lengthOfFile];
		this.contentOfOriginalFileChar = new char [lengthOfFile];
		this.contentAfterEncDecAlgo = new byte [lengthOfFile];
		this.contentAfterEncDecAlgoChar = new char [lengthOfFile];
		
		try {
			this.contentOfFile = new Scanner(new File(this.pathOfFile)).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//initialize the char-array of the original file:
		this.contentOfOriginalFileChar = contentOfFile.toCharArray(); 
		
		//initialize the byte-array of the original file:
		try {
			this.contentOfOriginalFile = Files.readAllBytes(Paths.get(this.pathOfFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * The method requests from the user to enter the method he would like to activate:
	 * 'E' for Encryption
	 * 'D' for Decryption
	 * the input from the user is case-insensitive
	 * if the user will entered a wrong input , he will gets a message of it
	 * and will entered  into while-loop.
	 *  - until he will typed the valid input.
	 * @return the input.
	 */
	public char chooseMethod()
	{
		System.out.println("Please choose a method : 'E' for Encryption and 'D' for decryption :");
		sc = new Scanner(System.in);
		char inputMethod = sc.next().charAt(0);
		while(inputMethod != 'E' && inputMethod!= 'e' && inputMethod!='D' && inputMethod!='d')
		{
			System.out.println("Incorrect input , please try again:");
			System.out.println("Please choose a method : 'E' for Encryption and 'D' for decryption:");
			sc =new Scanner(System.in);
			inputMethod = sc.next().charAt(0);
		}//while

		return inputMethod;
	}
	
	
	/**
	 * Activate part 1 :
	 * The user will input the path of the file which he would like to simulates.
	 * If the path of the file is not a real file (file does not exist),
	 * the user will be asked again to type the path- 
	 * until the path will be of an exist file.
	 * in the end, a message of the simulation 
	 * will be printed  for the user.
	 */
	public File getTheFile()
	{
		Scanner scFile=new Scanner(System.in);
		System.out.println("Enter the path of the source file :");
		this.pathOfFile = scFile.next();

		this.theFile = new File(pathOfFile);
		boolean isExistPath =theFile.exists();

		// while-loop until the path will be real path of the file.
		while(! isExistPath )
		{
			System.out.println("File does not exist  !  please try again :  ");
			System.out.println();
			System.out.println("Enter the path of the file : ");
			scFile = new Scanner (System.in);
			pathOfFile = scFile.next();
			theFile = new File(pathOfFile);
			isExistPath= theFile.exists();
		}//while

		if(method == 'E' || method=='e')
		{
			System.out.println("encryption simulation of file $"+pathOfFile+"$");
			System.out.println();
		}
		else
		{
			System.out.println("decryption simulation of file $"+pathOfFile+"$");
			System.out.println();
		}
		return this.theFile;
	}
	
	//=====
	
	/**
	 * The Function changes the name of the path by the requested-method: 
	 * [original-file-full-name].encrypted  for Encrypted method,
	 * and [original-file-name]_decrypted.[origin-file-extention]  for Decryption method.
	 * using "getIndexOfLastDotInString" - help function.
	 * @param pathOfFile = the path of the file.
	 * @return the new path of the new encrypted/decrypted file.
	 */
	public String createThePathOfNewFile(String pathOfFile) {
		String newPath="";
		int indexOfLastDot= getIndexOfLastDotInString(pathOfFile);
		newPath=pathOfFile.substring(0,indexOfLastDot);

		if(this.method =='E' || this.method=='e') // case of Encryption
			newPath+=".encrypted.txt";

		else // case of Decryption  - this.method =='D' || this.method=='d'
			newPath+="_decrypted."+"[original_file_Extension].txt";
		return newPath;
	}

	/**
	 * The Function returns the index of the last appearance of dot '.' 
	 * @param pathOfFile - the path of file
	 * @return the last appearance of '.' 
	 */
	protected  int getIndexOfLastDotInString(String pathOfFile) {
		int ind =pathOfFile.length()-1;
		while(pathOfFile.charAt(ind) != '.')
		{
			ind--;
		}
		return ind;
	}


}
