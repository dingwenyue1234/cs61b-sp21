public class TriangleDrawer {

	public static void drawTriangle(int N){
		int SIZE;
		int col = 0;
		int row = 0;
		SIZE = N;
		while(row < SIZE){
			while(col <= row){
				System.out.print('*');
				col+=1;
			}
		System.out.println();
		col = 0;
		row+=1;
	}
	}	

	public static void main(String[] args) {
         drawTriangle(10);
		}
	}
