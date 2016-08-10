import java.io.File;
import java.util.Scanner;

public class Part5 {
	private Double_Algo d;
	private Reverse_algo r;
	private Split_Algo s;
	private char algoMethod;
	
	File theFile;
	String pathOfFile;

/**
 * Constructor 
 * Asks from the user the algorithm he would like to activate.
 * 'D' for Double Algorithm
 * 'R' for Reverse Algorithm
 * 'S' for Split Algorithm
 * the input is insensitive-case
 */
	public Part5()
	{
		System.out.println("Part 5 activate: ");
		// initialize the File and the path:
		 initiliazeFile();
		
		System.out.println("Enter the Encryption-Algorithm you would like to activate:");
		System.out.println(" 'D' for Double Algorithm,");
		System.out.println(" 'R' for Reverse Algorithm,");
		System.out.println("or 'S' for Split Algorithm:");
		Scanner sc =new Scanner (System.in);
		algoMethod = sc.next().charAt(0);
		
	     while(algoMethod !='d' && algoMethod !='D' 
		       && algoMethod !='r' && algoMethod !='R'
		       && algoMethod !='s' && algoMethod !='S')
	     { //while loop for case that the user give an incorrect input
	    	 System.out.println("Incorrect input ! try again :");
	    		System.out.println("Enter the Encryption-Algorithm you would like to activate:");
	    		System.out.println("'D' for Double Algorithm,");
	    		System.out.println("'R' for Reverse Algorithm,");
	    		System.out.println("or 'S' for Split Algorithm:");
	    		 sc =new Scanner (System.in);
	    		algoMethod = sc.next().charAt(0);
	     }//while
		
		if(algoMethod =='d' || algoMethod =='D')
			d = new Double_Algo(this.theFile ,this.pathOfFile);
		else if (algoMethod =='r' || algoMethod =='R')
			r = new Reverse_algo(this.theFile ,this.pathOfFile);
		else // algoMethod =='s' || algoMethod =='S'
			s=new Split_Algo(this.theFile ,this.pathOfFile);
			
	}
	/**
	 * The function gets from the user the path of the file
	 * and initialize theFile and the pathOFFile variables.
	 */
	public void initiliazeFile()
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
			System.out.println("Enter the path of the file : ");
			scFile = new Scanner (System.in);
			pathOfFile = scFile.next();
			theFile = new File(pathOfFile);
			isExistPath= theFile.exists();
		}//while
		
		this.pathOfFile=this.theFile.getPath();
	}
	
	
	
}
