package com.revature.thursday;

import java.util.Arrays;

public class TwoDimensionalArrays {

	public static void main(String[] args) {
		// 2d arrays
		int[][] square = new int[5][5];
		
		int[][] rectangle = new int[3][7];
		
		int[][] jagged = new int[5][];
		
		jagged[0] = new int[2];
		jagged[1] = new int[5];
		int[] arr = {1,2,3,4,5,6,7};
		jagged[2] = arr;
		
		for (int[] row : square) {
			for (int item : row) {
				item = 1;
				System.out.print(item + ", ");
			}
			System.out.println();
		}
		
		
	}

}
