import java.util.Scanner;
/**
 * Part 
 * @author Bar
 *activates part 4,
 *let the user to choose which algorithm he would like to activate 
 */
public class Part4 {
XOR_Algo xorA ;
MWO_algo mwoA;
char algoChoosen;

/**
 * Constructor
 * take from the user the method he would like to activate
 */
public Part4()
{
	System.out.println("Part 4 activate :");
	algoChoosen = chooseEncAlgo();
	if(algoChoosen =='x' || algoChoosen=='X' )
		xorA = new XOR_Algo();
	
	else // algoChoosen =='m' || algoChoosen=='M'
		mwoA= new MWO_algo();
}


/**
 * wrapping function to get from the user the algorithm he would like to activate.
 * @return x for XOR algorithm or M for multiplication algorithm;
 */
private char chooseEncAlgo()
{
	System.out.println("Choose the encrypt-algorithm which you want to activate :");
	System.out.println("'X' for XOR algorithm ");
	System.out.println("'M' for multiplication algorithm :");

	Scanner sc =new Scanner (System.in);
	char ans = sc.next().charAt(0);
	while(ans != 'x' && ans!= 'X' && ans!='m' && ans!='M')
	{
		System.out.println("Incorrect input , please try again:");
		System.out.println("Choose the encrypt-algorithm which you want to activates :");
		System.out.println("'X' for XOR algorithm ");
		System.out.println("'M' for multiplication algorithm");
		sc =new Scanner(System.in);
		ans = sc.next().charAt(0);
	}//while

	return ans;
}

}
