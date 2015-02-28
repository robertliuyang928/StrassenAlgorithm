import java.util.*;
import java.io.*;



public class StrassenAlgorithm {

	public static void main(String[] args) {
		/*Welcome Interface and Definition of Dimension*/
		System.out.println("Welcome to Matrix Calculator");
		System.out.println("The product of two 2^n * 2^n matrices will be calculated and displayed");
		String input = getInput("The two matrices will be generated with elements as random integers between 0 and 100 \nPlease input an integer n");
		int n = Integer.parseInt(input);
		System.out.println("The integer you input is "+n);
		int m = powerOfTwo(n);						//m is the dimension of the two matrices	
		System.out.println("The product of two " +m+" * " +m+ " matrices will be calculated");	
				
		/*Generation of Two Matrices using dimension m*/
		int a[][] = new int[m][m];
		int b[][] = new int[m][m];
		
		Random Rand= new Random ();	

		System.out.println("\nThe first Matrix a is ");
		for (int x1=0; x1<m; x1++){
			for (int y1=0; y1<m; y1++){
				a[x1][y1]=Rand.nextInt(100);
				System.out.print("a["+x1+"]["+y1+"]="+a[x1][y1]+"\t");
			}
			System.out.println();
		}
		
		System.out.println("\nThe Second Matrix b is ");	
		for (int x2=0; x2<m; x2++){
			for (int y2=0; y2<m; y2++){
				b[x2][y2]=Rand.nextInt(100);
				System.out.print("b["+x2+"]["+y2+"]="+b[x2][y2]+"\t");
			}
			System.out.println();
		}	
		
		System.out.println("\nThe product Matrix c = a * b is ");		
		int c[][] = new int[m][m];
		for (int i=0; i<m; i++){
			for (int j=0; j<m; j++){
				c[i][j]=0;
				for (int k=0; k<m; k++){
					c[i][j]+=a[i][k]*b[k][j];
				}
				System.out.print("c["+i+"]["+j+"]="+c[i][j]+"\t");			
			}
			System.out.println();
		}
		


		
		
		
		


	}
	
	
	
	
	/*BufferedReader method for data input*/
	private static String getInput(String prompt) {		//String prompt serves as input direction
		BufferedReader stdin = new BufferedReader(		
				new InputStreamReader(System.in));

		System.out.println(prompt);
		System.out.flush();
		
		try {
			return stdin.readLine();
		} catch (Exception e) {
			return "Error: " + e.getMessage();
		}
	}
	
	public static int powerOfTwo(int exp){
		int result = 1;
		for (int i=0;i<exp;i++){
			result*=2;
		}
		return result;
	}
	

	
	

}
