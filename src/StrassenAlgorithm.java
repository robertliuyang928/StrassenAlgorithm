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
//		int sub[][] = new int[m][m];
//		int sum[][] = new int[m][m];		
		int brutalResult[][] = new int[m][m];
		int strassenResult[][] = new int[m][m];
//		int q1[][]= new int [m/2][m/2];
//		int q2[][]= new int [m/2][m/2];
//		int q3[][]= new int [m/2][m/2];
//		int q4[][]= new int [m/2][m/2];
//		int c[][]= new int [m][m];		
		//int strassenResult[][] = new int[m][m];	
		
		long tStart;
		long tEnd;
		long brutalForceTime;
		long strassenTime;

		System.out.println("\nThe first Matrix a is ");
		a=createMatrix(m);
//		printMatrix(a);
		

		System.out.println("\nThe second Matrix b is ");
		b=createMatrix(m);
//		printMatrix(b);
		
		System.out.println("\nThe brutal force result is ");
		tStart = System.nanoTime();
		brutalResult=brutalForceProduct(a,b);
		tEnd = System.nanoTime();
		brutalForceTime=tEnd-tStart;
//		printMatrix(brutalResult);
		System.out.println("Brutal force method takes " + brutalForceTime);
		
		
		
		System.out.println("\nThe Strassen Algroithm result is ");
		tStart = System.nanoTime();
		strassenResult=strassenAlgorithm(a,b);
		tEnd = System.nanoTime();
		strassenTime = tEnd - tStart;
//		printMatrix(strassenResult);
		System.out.println("Strassen Algorithm takes " + strassenTime);
		
		long timeDifference = brutalForceTime - strassenTime;
		
		System.out.println("Strassen Algorithm is " + timeDifference + " faster than the brutal force method ");	
		
	
//		System.out.println("\nThe difference Matrix a is ");
//		sub = sub(a, b);
//		
//		System.out.println("\nThe summation Matrix a is ");
//		sum= sum(a,b);
//		
//				
//		System.out.println("\nThe product Matrix c = a * b is ");
//		brutalResult = brutalForceProduct(a, b);
//		
//		System.out.println("\nThe quarter Matrix is ");
//		q1= quarter(a, 1);		
//		q2= quarter(a, 2);	
//		q3= quarter(a, 3);	
//		q4= quarter(a, 4);	
//		
//		System.out.println("\nThe merge Matrix is ");
//		c = merge(q1,q2,q3,q4);
//		
//		
//		System.out.println("\nThe difference Matrix a is ");
//		sub = sub(a, c);
		
		
		
		
		
		

		
		
		
		


		
		
		
		


	}

	
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
			result = brutalForceProduct(a,b);
		}
		else {
			//ArrayList<int[][]> m = new ArrayList<int[][]> ();
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

	
	/*modified strassen algorithm*/
	public static int[][] modifiedStrassenAlgorithm(int a[][], int b[][]){
		int len =a.length;
		int result[][]=new int [len][len];
		if (len==2){
			result = brutalForceProduct(a,b);
		}
		else {
			//ArrayList<int[][]> m = new ArrayList<int[][]> ();
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
			int m1[][]=brutalForceProduct(sum(a1,a4),sum(b1,b4));
			int m2[][]=brutalForceProduct(sum(a3,a4),b1);
			int m3[][]=brutalForceProduct(a1,sub(b2,b4));
			int m4[][]=brutalForceProduct(a4,sub(b3,b1));
			int m5[][]=brutalForceProduct(sum(a1,a2),b4);
			int m6[][]=brutalForceProduct(sub(a3,a1),sum(b1,b2));
			int m7[][]=brutalForceProduct(sub(a2,a4),sum(b3,b4));
			
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
		
//		for (int i=0; i<len; i++){
//			for (int j=0; j<len; j++){
//				System.out.print("merge["+i+"]["+j+"]="+result[i][j]+"\t");			
//			}
//			System.out.println();
//		}

		
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
		
//		for (int i=0; i<l; i++){
//			for (int j=0; j<l; j++){
//				System.out.print("quarter["+i+"]["+j+"]="+quarter[i][j]+"\t");			
//			}
//			System.out.println();
//		}

		return quarter;
		
	}
	
	/*Brutal force matrices product calculation method with time counter*/
	public static int[][] brutalForceProduct(int[][] a, int[][] b) {
//		long tStart = System.nanoTime();
		int len = a.length;
		int brutal[][] = new int[len][len];
		for (int i=0; i<len; i++){
			for (int j=0; j<len; j++){
				brutal[i][j]=0;
				for (int k=0; k<len; k++){
					brutal[i][j]+=a[i][k]*b[k][j];
				}
//				System.out.print("brutal["+i+"]["+j+"]="+brutal[i][j]+"\t");			
			}
//			System.out.println();
		}
//		long tEnd = System.nanoTime();
//		long time= tEnd-tStart;
//		System.out.println("Brutal Force method costs " + time + "ns");
		return brutal;
	}
		
	/*Method for getting the difference between two matrices*/
	public static int[][] sub(int[][] a, int[][] b) {
		int len = a.length;
		int difference[][] = new int [len][len];
		for (int i=0; i<len; i++){
			for (int j=0;j<len;j++){
				difference [i][j]=a[i][j]-b[i][j];
//				System.out.print("a["+i+"]["+j+"]-b["+i+"]["+j+"]="+ difference[i][j]+"\t");
			}
//			System.out.println();
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
//				System.out.print("a["+i+"]["+j+"]+b["+i+"]["+j+"]="+ difference[i][j]+"\t");
			}
//			System.out.println();
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
//				System.out.print("matrix["+x+"]["+y+"]="+matrix[x][y]+"\t");
			}
//			System.out.println();
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
