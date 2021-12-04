public class triangle {
	public static void triangle(int N){
		int row = 0;
		while (row < N){
			int col = 0;
			while(col <= row){

						System.out.print("*");	
						col += 1;
			}
			System.out.println();
			row += 1;
		}
	}
	public static void main(String[] args){
        triangle(5);	
	}
}

