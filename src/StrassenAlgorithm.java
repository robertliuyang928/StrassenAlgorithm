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
		int sub[][] = new int[m][m];
		int sum[][] = new int[m][m];		
		int brutalResult[][] = new int[m][m];
		int q[][]= new int [m/2][m/2];
		//int strassenResult[][] = new int[m][m];		

		System.out.println("\nThe first Matrix a is ");
		a=createMatrix(m);

		System.out.println("\nThe second Matrix b is ");
		b=createMatrix(m);
	
		System.out.println("\nThe difference Matrix a is ");
		sub = substractMatrices(a, b);
		
		System.out.println("\nThe summation Matrix a is ");
		sum= summationMatrices(a,b);
		
				
		System.out.println("\nThe product Matrix c = a * b is ");
		brutalResult = brutalForceProduct(a, b);
		
		System.out.println("\nThe quarter Matrix is ");
		q= quarter(a, 1);		
		q= quarter(a, 2);	
		q= quarter(a, 3);	
		q= quarter(a, 4);	
		

		
		
		
		


		
		
		
		


	}


	
	/*Method that returns one of the 4 quarters of a 2^n * 2 ^n matrix
	  index 1 for top-left corner; index 2 for top-right corner; 
	  index 3 for bottom-left corner; index 4 for bottom-right corner; */
	
	public static int[][] quarter(int[][] A, int index) {
		int len = A.length;
		int l=len/2;
		int quarter[][]= new int [l][l];
		
		switch (index){
		case 1:
			for (int i=0; i<l; i++){
				for (int j=0; j<l;j++){
					quarter[i][j]= A[i][j];
				}
			}
			break;
		case 2:
			for (int i=0; i<l; i++){
				for (int j=0; j<l;j++){
					quarter[i][j]= A[i][l+j];
				}
			}
			break;
		case 3:
			for (int i=0; i<l; i++){
				for (int j=0; j<l;j++){
					quarter[i][j]= A[l+i][j];
				}
			}
			break;
		case 4:
			for (int i=0; i<l; i++){
				for (int j=0; j<l;j++){
					quarter[i][j]= A[l+i][l+j];
				}
			}
			break;
		default:
			break;		
		}		
		
		for (int i=0; i<l; i++){
			for (int j=0; j<l; j++){
				System.out.print("quarter["+i+"]["+j+"]="+quarter[i][j]+"\t");			
			}
			System.out.println();
		}

		return quarter;
		
	}
	
	
	
	
	
	
	
	



	public static int[][] brutalForceProduct(int[][] a, int[][] b) {
		long tStart = System.nanoTime();
		int len = a.length;
		int brutal[][] = new int[len][len];
		for (int i=0; i<len; i++){
			for (int j=0; j<len; j++){
				brutal[i][j]=0;
				for (int k=0; k<len; k++){
					brutal[i][j]+=a[i][k]*b[k][j];
				}
				System.out.print("brutal["+i+"]["+j+"]="+brutal[i][j]+"\t");			
			}
			System.out.println();
		}
		long tEnd = System.nanoTime();
		long time= tEnd-tStart;
		System.out.println("Brutal Force method costs " + time + "ns");
		return brutal;
	}




	
	
	
	
	

	public static int[][] substractMatrices(int[][] a, int[][] b) {
		int len = a.length;
		int difference[][] = new int [len][len];
		for (int i=0; i<len; i++){
			for (int j=0;j<len;j++){
				difference [i][j]=a[i][j]-b[i][j];
				System.out.print("a["+i+"]["+j+"]-b["+i+"]["+j+"]="+ difference[i][j]+"\t");
			}
			System.out.println();
		}
		return difference;
	}

	public static int[][] summationMatrices(int[][] a, int[][] b) {
		int len = a.length;
		int difference[][] = new int [len][len];
		for (int i=0; i<len; i++){
			for (int j=0;j<len;j++){
				difference [i][j]=a[i][j]+b[i][j];
				System.out.print("a["+i+"]["+j+"]+b["+i+"]["+j+"]="+ difference[i][j]+"\t");
			}
			System.out.println();
		}
		return difference;
	}	
	

	
	
	
	/*Method for generating 2^n * 2^n matrix with elements as random integers between 0 and 100 given an integer dimension=2^n*/
	public static int[][] createMatrix(int dimension) {
		int[][] matrix = new int [dimension][dimension];
		Random Rand= new Random ();	
		for (int x=0; x<dimension; x++){
			for (int y=0; y<dimension; y++){
				matrix[x][y]=Rand.nextInt(100);
				System.out.print("matrix["+x+"]["+y+"]="+matrix[x][y]+"\t");
			}
			System.out.println();
		}
		return matrix;
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
	
	/*Simple method that returns 2^n given n*/
	public static int powerOfTwo(int exp){
		int result = 1;
		for (int i=0;i<exp;i++){
			result*=2;
		}
		return result;
	}
	

	
	

}
