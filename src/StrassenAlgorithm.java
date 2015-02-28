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
				
		/*Implementation of standard method, Strassen algorithm and modified Strassen algorithm*/
		int a[][] = new int[m][m];
		int b[][] = new int[m][m];	
		int standardResult[][] = new int[m][m];
		int strassenResult[][] = new int[m][m];
		int modifiedStrassenResult[][] = new int[m][m];
		
		long tStart;
		long tEnd;
		long standardTime;
		long strassenTime;
		long modifiedStrassenTime;
		
		a=createMatrix(m);	
		b=createMatrix(m);
//		System.out.println("\nThe first Matrix a is ");

//		printMatrix(a);		
//		System.out.println("\nThe second Matrix b is ");

//		printMatrix(b);		
		
//		System.out.println("\nThe standard method result is ");
		tStart = System.nanoTime();
		standardResult=standardMethod(a,b);
		tEnd = System.nanoTime();
		standardTime=tEnd-tStart;
//		printMatrix(standardResult);
		System.out.println("Standard method takes: \t\t\t" + standardTime  + " ns");
		
		
		
//		System.out.println("\nThe Strassen Algorithm result is ");
		tStart = System.nanoTime();
		strassenResult=strassenAlgorithm(a,b);
		tEnd = System.nanoTime();
		strassenTime = tEnd - tStart;
//		printMatrix(strassenResult);
		System.out.println("Strassen Algorithm takes: \t\t" + strassenTime + " ns");
		
		
//		System.out.println("\nThe Strassen Algroithm result is ");
		tStart = System.nanoTime();
		modifiedStrassenResult=modifiedStrassenAlgorithm(a,b);
		tEnd = System.nanoTime();
		modifiedStrassenTime = tEnd - tStart;
//		printMatrix(modifiedStrassenResult);
		System.out.println("Modified Strassen Algorithm takes: \t" + modifiedStrassenTime  + " ns");
	}

	/*Method that prints matrix elements*/
	public static void printMatrix(int a[][]){
		int len = a.length;
		for (int i=0;i<len;i++){
			for (int j=0;j<len;j++){
				System.out.print(a[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	/*Strassen matrices product calculation method*/
	public static int[][] strassenAlgorithm(int a[][], int b[][]){
		int len =a.length;
		int result[][]=new int [len][len];
		if (len==2){
			result = standardMethod(a,b);
		}
		else {
			/*split matrices a and b into 4 quarters each*/
			int a1[][]=quarter(a,1);
			int a2[][]=quarter(a,2);
			int a3[][]=quarter(a,3);
			int a4[][]=quarter(a,4);
			int b1[][]=quarter(b,1);
			int b2[][]=quarter(b,2);
			int b3[][]=quarter(b,3);
			int b4[][]=quarter(b,4);
			/*auxiliary matrices m1~m7 for intermediate calculation*/
			int m1[][]=strassenAlgorithm(sum(a1,a4),sum(b1,b4));
			int m2[][]=strassenAlgorithm(sum(a3,a4),b1);
			int m3[][]=strassenAlgorithm(a1,sub(b2,b4));
			int m4[][]=strassenAlgorithm(a4,sub(b3,b1));
			int m5[][]=strassenAlgorithm(sum(a1,a2),b4);
			int m6[][]=strassenAlgorithm(sub(a3,a1),sum(b1,b2));
			int m7[][]=strassenAlgorithm(sub(a2,a4),sum(b3,b4));
			
			int c1[][]=sum(sum(m1,m4),sub(m7,m5)); //C_1,1 = (M1 + M4) + (M7 - M5)
			int c2[][]=sum(m3,m5);				   //C_1,2 = M3 + M5
			int c3[][]=sum(m2,m4);				   //C_2,1 = M2 + M4
			int c4[][]=sum(sub(m1,m2),sum(m3,m6));  //C_2,2 = (M1 - M2) + (M3 + M6)
			
			result = merge (c1,c2,c3,c4);
		}
		
		return result;
	}
	
	/*modified Strassen algorithm*/
	public static int[][] modifiedStrassenAlgorithm(int a[][], int b[][]){
		int len =a.length;
		int result[][]=new int [len][len];
		if (len==2){
			result = standardMethod(a,b);
		}
		else {
			/*split matrices a and b into 4 quarters each*/
			int a1[][]=quarter(a,1);
			int a2[][]=quarter(a,2);
			int a3[][]=quarter(a,3);
			int a4[][]=quarter(a,4);
			int b1[][]=quarter(b,1);
			int b2[][]=quarter(b,2);
			int b3[][]=quarter(b,3);
			int b4[][]=quarter(b,4);
			/*helper matrices m1~m7 for intermediate calculation*/
			int m1[][]=standardMethod(sum(a1,a4),sum(b1,b4));
			int m2[][]=standardMethod(sum(a3,a4),b1);
			int m3[][]=standardMethod(a1,sub(b2,b4));
			int m4[][]=standardMethod(a4,sub(b3,b1));
			int m5[][]=standardMethod(sum(a1,a2),b4);
			int m6[][]=standardMethod(sub(a3,a1),sum(b1,b2));
			int m7[][]=standardMethod(sub(a2,a4),sum(b3,b4));
			
			int c1[][]=sum(sum(m1,m4),sub(m7,m5)); //C_1,1 = (M1 + M4) + (M7 - M5)
			int c2[][]=sum(m3,m5);				   //C_1,2 = M3 + M5
			int c3[][]=sum(m2,m4);				   //C_2,1 = M2 + M4
			int c4[][]=sum(sub(m1,m2),sum(m3,m6));  //C_2,2 = (M1 - M2) + (M3 + M6)
			
			result = merge (c1,c2,c3,c4);
		}
		
		return result;
	}
	
	/*Method that merges 4 matrices into one matrix
	 * c1 as top-left; c2 as top-right
	 * c3 as bottom-left; c4 as bottom-right*/
	public static int[][] merge(int c1[][],int c2[][],int c3[][],int c4[][]){
		int l = c1.length;
		int len = 2*l;
		int result[][]= new int [len][len];
		
		for (int i=0; i<l;i++){
			for (int j=0;j<l;j++){
				result [i][j]=c1[i][j];
			}
		}
		
		for (int i=0; i<l;i++){
			for (int j=0;j<l;j++){
				result [i][l+j]=c2[i][j];
			}
		}
		
		for (int i=0; i<l;i++){
			for (int j=0;j<l;j++){
				result [l+i][j]=c3[i][j];
			}
		}
		
		for (int i=0; i<l;i++){
			for (int j=0;j<l;j++){
				result [l+i][l+j]=c4[i][j];
			}
		}
		
		return result;
	}

	/*Method that returns one of the 4 quarters of a 2^n * 2 ^n matrix
	 *index 1 for top-left corner; index 2 for top-right corner; 
	 *index 3 for bottom-left corner; index 4 for bottom-right corner; */
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
		
		return quarter;		
	}
	
	/*Brutal force matrices product calculation method with time counter*/
	public static int[][] standardMethod(int[][] a, int[][] b) {
//		long tStart = System.nanoTime();
		int len = a.length;
		int brutal[][] = new int[len][len];
		for (int i=0; i<len; i++){
			for (int j=0; j<len; j++){
				brutal[i][j]=0;
				for (int k=0; k<len; k++){
					brutal[i][j]+=a[i][k]*b[k][j];
				}			
			}
		}

		return brutal;
	}
		
	/*Method for getting the difference between two matrices*/
	public static int[][] sub(int[][] a, int[][] b) {
		int len = a.length;
		int difference[][] = new int [len][len];
		for (int i=0; i<len; i++){
			for (int j=0;j<len;j++){
				difference [i][j]=a[i][j]-b[i][j];
			}
		}
		return difference;
	}

	/*Method for getting the sum of two matrices*/	
	public static int[][] sum(int[][] a, int[][] b) {
		int len = a.length;
		int difference[][] = new int [len][len];
		for (int i=0; i<len; i++){
			for (int j=0;j<len;j++){
				difference [i][j]=a[i][j]+b[i][j];
			}
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
			}
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
