import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Directory_Enc {
	private char method;
	private char algoUserChoose;
	Key key;

	private File theDirectory;
	private File resultDirectory;
	private File keyFile;
	boolean has2Key;
	private String pathOfFile;
	private String pathOfResDir;

	private char syncAsync;
	private String[] filesNameInDirectory;
	private String[] pathFilesInDir;
	private String[] pathFilesInDirWhichNotDir;
	private String[] pathFilesInDirAfterAlgo;
	private ArrayList<File>contentOfDirectory;
	private ArrayList<File>contentOfDirectoryWithoutSubDirectory;
	private DirEncDecThread[] threads;
	private final int numOfThreads=5; // <= change the number here
	private static int indexOfcurrentFileForThreads=0;




	/**
	 * constructor
	 *
	 */
	public Directory_Enc()
	{
		getDataFromUser();
	}

	/**
	 *The function  gets from the user the path of Directory, 
	 *the method , the sync/a-sync method and the algorithm
	 *he would like to perform
	 */
	public void getDataFromUser()
	{
		// input for the encryption algorithm:
		Scanner sc;
		System.out.println("Choose an algorithm to activate in directory : ");
		System.out.println("'1' for Ceaser Algorithm,");
		System.out.println("'2' for Xor Algorithm,");
		System.out.println("'3' 'M' for MWO Algorithm :");
		System.out.println("'4' for Double Algorithm,");
		System.out.println("'5' for Reverse Algorithm,");
		System.out.println("and '6' for Split Algorithm :");
		sc = new Scanner(System.in);
		algoUserChoose = sc.next().charAt(0);
		while(algoUserChoose !='1' && algoUserChoose !='2' 
				&& algoUserChoose !='3' && algoUserChoose !='4'
				&& algoUserChoose !='5' && algoUserChoose !='6')
		{
			System.out.println("Incorrect input ! try again :");
			System.out.println("Choose an algorithm to activate in directory : ");
			System.out.println("'1' for Ceaser Algorithm,");
			System.out.println("'2' for Xor Algorithm,");
			System.out.println("'3' for MWO Algorithm :");
			System.out.println("'4' for Double Algorithm,");
			System.out.println("'5' for Reverse Algorithm,");
			System.out.println("and '6' for Split Algorithm :");
			sc= new Scanner(System.in);
			algoUserChoose = sc.next().charAt(0);
		}//while
		sc.close();

		//input for method :
		System.out.println("Enter the Method you would like to activate: ");
		System.out.println("'E' for Encryption,");
		System.out.println("'D' for Decryption: ");
		sc = new Scanner (System.in);
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
		sc.close();
		// input for path of directory:

		Scanner scFile=new Scanner(System.in);
		System.out.println("Enter the path of the directory:");
		this.pathOfFile = scFile.next();

		this.theDirectory = new File(pathOfFile);
		boolean isExistPath =theDirectory.exists();

		// while-loop until the path will be real path of the file.
		while(! isExistPath && !theDirectory.isDirectory() )
		{
			if(!isExistPath)
				System.out.println("File does not exist  !  please try again:  ");

			else // this.theFile.exists() && !this.theFile.isDirectory()
				System.out.println("File is not a directory ! try again: ");

			System.out.println("Enter the path of the file : ");
			scFile = new Scanner (System.in);
			pathOfFile = scFile.next();
			theDirectory = new File(pathOfFile);
			isExistPath= theDirectory.exists();
		}//while
		scFile.close();


		System.out.println("Would you like to activate the encryption with 'synchronized' or 'a-synchronized' ? ");
		System.out.println("Enter 's' for 'synchronized'");
		System.out.println("Enter 'a' for 'a-synchronized' :");
		sc = new Scanner (System.in);
		this.syncAsync=sc.next().charAt(0);
		while(this.method!='s' && this.method !='S' 
				&& this.method!='a' && this.method !='A')
		{
			System.out.println("Wrong input , please try again: ");
			System.out.println("Would you like to activate the encryption with 'synchronized' or 'a-synchronized' ? ");
			System.out.println("Enter 's' for 'synchronized'");
			System.out.println("Enter 'a' for 'a-synchronized' :");
			sc = new Scanner (System.in);
			this.syncAsync=sc.next().charAt(0);
		}
		// build the help- array & arrayList to store the data 
		this.contentOfDirectory = new ArrayList<File>();
		this.contentOfDirectoryWithoutSubDirectory = new ArrayList<File>();

		this.filesNameInDirectory = this.theDirectory.list();
		for (int i = 0; i < filesNameInDirectory.length; i++) {
			this.pathFilesInDir[i] = this.pathOfFile+"\\"+filesNameInDirectory[i] ;
			this.contentOfDirectory.add(new File(pathFilesInDir[i]));
		}

		findTheFileThatNotADirectory();

		// input for the key:
		System.out.println("Enter the Key of the algorithm : ");
		System.out.println("is there 2 kys for the algorithm?");
		System.out.println("type 'Y' for yes anf 'N' for no: " );
		char t = sc.next().charAt(0);
		if(t == 'y' || t == 'Y')
		{
			System.out.println("Enter the first key :");
			byte fk = sc.nextByte();
			System.out.println("Enter the second key :");
			byte sk = sc.nextByte();
			key = new Key(fk,sk);
			this.has2Key =true;
		}
		else
		{
			System.out.println("Enter the  key :");
			byte k = sc.nextByte();
			key = new Key(k);
			this.has2Key = false;
		}
		keyFile = new File(this.pathOfFile+"\\"+"key.bin");



	}
	/**
	 * The function checks which from the files are not a directory.
	 * these  that are not a Directory's file will added to
	 * help arrayList <File> for perform the encrypt/decrypt algorithm
	 */
	public void findTheFileThatNotADirectory()
	{

		for (int i = 0; i < contentOfDirectory.size(); i++) {
			if(! this.contentOfDirectory.get(i).isDirectory())
			{
				this.contentOfDirectoryWithoutSubDirectory.add(this.contentOfDirectory.get(i));
			}
		}
	}
	/**
	 * activate the algorithm
	 */
	public void ActivateAlgo()
	{
		String pathOfSubDirective="";
		if(this.method=='e' || this.method=='E')
		{
			pathOfSubDirective=this.pathOfFile+"\\"+"encrypted";
			for (int i = 0; i < filesNameInDirectory.length; i++) {
				pathFilesInDirAfterAlgo[i] = pathOfSubDirective+"\\"+"file"+i+".txt";
			}
		}
		else
		{
			pathOfSubDirective=this.pathOfFile+"\\"+"decrypted";
		}
		File d = new File (pathOfSubDirective);
		if(d.mkdirs()) System.out.println("folder was created successfully");

		findTheFileThatNotADirectory();
		this.pathFilesInDirWhichNotDir =new String [this.contentOfDirectoryWithoutSubDirectory.size()] ;
		for (int i = 0; i < this.contentOfDirectoryWithoutSubDirectory.size(); i++) {
			pathFilesInDirWhichNotDir[i] = this.contentOfDirectoryWithoutSubDirectory.get(i).getPath();
		}
		Semaphore[] sems = new Semaphore[numOfThreads];
		if(this.syncAsync =='a' || this.syncAsync =='A')
		{
			sems = new Semaphore[numOfThreads];
			sems[0] = new Semaphore(1,true);
			for (int i = 1; i<numOfThreads;i++)
				sems[i] = new Semaphore(0,true);

			this.threads = new DirEncDecThread [this.numOfThreads];
			for (int i = 0; i < numOfThreads; i++) {
				threads[i] = new DirEncDecThread(this.method, this.algoUserChoose,this.syncAsync,this.key,this.has2Key,this.theDirectory,
						this.numOfThreads,this.indexOfcurrentFileForThreads++,this.pathFilesInDirWhichNotDir,pathFilesInDirAfterAlgo[indexOfcurrentFileForThreads],1,numOfThreads, sems);
			}

			for (Thread t: threads)
				t.start(); 
		}
		else //this.syncAsync =='s' || this.syncAsync =='S'
		{
			DirEncDecThread dedt=new DirEncDecThread(this.method, this.algoUserChoose,this.syncAsync,this.key,this.has2Key,this.theDirectory,
					this.numOfThreads,this.indexOfcurrentFileForThreads,this.pathFilesInDirWhichNotDir,pathFilesInDirAfterAlgo[indexOfcurrentFileForThreads],1,numOfThreads, sems);

			/*
			 * 	public DirEncDecThread(char method ,char algo,char sync,Key k,boolean has2key, File direct, int numOfThreads,int indexOfCurrentFile ,
			String[]pathsOfFileWithoutDir,String newPathForTheNewFile,int current,int index,Semaphore[] sems ){
			 */

			dedt.start();	
		}
	}
}

/**
 * Thread Class
 * @author Bar
 *The Thread will activate by 2 ways :
 *sync - activate all in 1 thread
 *aSync - activates in several threads. 
 */
class DirEncDecThread extends Thread {
	private int index;
	private int current;
	private Semaphore[] sems;
	private int numOfThreads;
	private String [] pathOfFilesInDir;
	private String newPathForTheNewFile;
	private Key key;
	private boolean has2key;

	private Double_Algo da;
	private Reverse_algo ra;
	private Split_Algo sa;
	private Ceaser_Algo c;
	private XOR_Algo xa;
	private MWO_algo ma;

	private char method;
	private char algoUserChoose;
	private char syncAsync;
	private File theDirectory;
	private String pathOfFile;
	private ArrayList<File>contentOfDirectoryWithoutSubDirectory;
	int indexCurrentFile;
	public DirEncDecThread(char method ,char algo,char sync,Key k,boolean has2key, File direct, int numOfThreads,int indexOfCurrentFile ,
			String[]pathsOfFileWithoutDir,String newPathForTheNewFile,int current,int index,Semaphore[] sems ){

		this.method = method;
		this.algoUserChoose = algo;
		this.syncAsync=sync;
		this.key=k;
		this.has2key=has2key;
		this.theDirectory = direct;
		this.numOfThreads=numOfThreads;
		this.indexCurrentFile=indexOfCurrentFile;
		this.pathOfFilesInDir=pathsOfFileWithoutDir;
		this.newPathForTheNewFile= newPathForTheNewFile;
		this.current=current;
		this.index = index;
		this.sems = sems;
	}

	public void run(){
		long startTime = System.currentTimeMillis();  // <= start measuring time 

		if(this.syncAsync == 'a' || this.syncAsync == 'A')
		{
			System.out.println("User choose A-Synchronous :");

			while(current <= this.pathOfFilesInDir.length){
				try{
					sems[index].acquire();   //  <= lock the Critical Section

					File f =new File(pathOfFilesInDir[indexCurrentFile]);
					switch(this.algoUserChoose)
					{
					case 1 : algoUserChoose='1';
					{
						this.c = new Ceaser_Algo(this.method,f , f.getPath(),key.getFirstKey());
						break;
					}
					case 2 : algoUserChoose='2';
					{
						this.xa = new XOR_Algo(this.method,f , f.getPath(),key.getFirstKey());
						break;
					}
					case 3 : algoUserChoose='3';
					{
						this.ma = new MWO_algo(this.method,f , f.getPath(),key.getFirstKey());
						break;
					}
					case 4 : algoUserChoose='4';
					{
						this.da = new Double_Algo(f , f.getPath());
						break;
					}
					case 5 : algoUserChoose='5';
					{
						this.ra = new Reverse_algo(f , f.getPath());
						break;
					}
					case 6 : algoUserChoose='6';
					{
						this.sa = new Split_Algo(f , f.getPath());
						break;
					}

					}

					current+=5;

					sems[(index+1)%this.numOfThreads].release(); // unlock the Critical Section for the next Thread
				}
				catch(Exception e){
					System.out.println("Error");
					return;
				}
			}
		}

		else//this.syncAsync == 's' || this.syncAsync == 'S'
		{
			System.out.println("User choose Synchronous :");
			File f =new File(pathOfFilesInDir[indexCurrentFile]);
			switch(this.algoUserChoose)
			{
			case 1 : algoUserChoose='1';
			{
				this.c = new Ceaser_Algo(this.method,f , f.getPath(),key.getFirstKey());
				break;
			}
			case 2 : algoUserChoose='2';
			{
				this.xa = new XOR_Algo(this.method,f , f.getPath(),key.getFirstKey());
				break;
			}
			case 3 : algoUserChoose='3';
			{
				this.ma = new MWO_algo(this.method,f , f.getPath(),key.getFirstKey());
				break;
			}
			case 4 : algoUserChoose='4';
			{
				this.da = new Double_Algo(f , f.getPath());
				break;
			}
			case 5 : algoUserChoose='5';
			{
				this.ra = new Reverse_algo(f , f.getPath());
				break;
			}
			case 6 : algoUserChoose='6';
			{
				this.sa = new Split_Algo(f , f.getPath());
				break;
			}

			}
		}
		long endTime   = System.currentTimeMillis(); // < = end of calculate rime of XOR algo
		long totalTime = endTime - startTime;
		System.out.println("Xor algorithm end");
		System.out.println("Total time of XOR algorithm: "+totalTime);
	}

}

