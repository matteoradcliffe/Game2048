package edu.wm.cs.cs301.game2048;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class State implements GameState {
	private int[][] table = new int[4][4];
	
	
	public State(GameState original) {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table.length; j++) {
				table[i][j] = original.getValue(i,j);
			}
		}
		
	}
	public State() {
		setEmptyBoard();
	}

	@Override
	public int getValue(int xCoordinate, int yCoordinate) {
		int x = xCoordinate;
		int y = yCoordinate;
		
		return table[y][x];
	}

	@Override
	public void setValue(int xCoordinate, int yCoordinate, int value) {
		table[yCoordinate][xCoordinate] = value;

	}

	@Override
	public void setEmptyBoard() {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				table[i][j] = 0;
			}
		}
	}

	@Override
	public boolean addTile() {
		if (isFull()) {
			return false;
		}
		List<int[]> emptyTiles = new ArrayList<>();
		for (int i = 0; i < table.length; i++) {
			for(int j = 0; j < table.length; j++) {
				if (table[i][j] == 0){
					emptyTiles.add(new int[] {i,j});
				}
			}
		}
		
		Random rand = new Random();
		int randomIndex = rand.nextInt(emptyTiles.size());
		int[] chosenSlot = emptyTiles.get(randomIndex);
		int row = chosenSlot[0];
		int col = chosenSlot[1];
		
		int newValue;
		int randomNum = rand.nextInt(10);
		if (randomNum == 4) {
			newValue = 4;
		}
		else {
			newValue = 2;
		}
		table[row][col] = newValue;
		
		return true;
	}

	@Override
	public boolean isFull() {
		for (int i = 0; i < table.length; i++) {
			for(int j = 0; j < table[i].length; j++) {
				if (table[i][j] == 0) {
					return false;
				}
			}
		}	
		return true;
	}

	@Override
	public boolean canMerge() {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				int value = table[i][j];
				if (value == 0) {
					continue;
				}
				if ( j < 3 && table[i][j+1] == value) {
					return true;
				}
				if ( i < 3 && table[i+1][j] == value) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean reachedThreshold() {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table.length; j++) {
				if (table[i][j] >= 2048) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int left() {
		int points = 0;
		for (int row = 0; row < 4; row++) {
			int[] newRow = new int[4];
			int index = 0;
			
			for (int col = 0; col < 4; col++) {
				if (table[row][col] != 0 ) {
					newRow[index] = table[row][col];
					index++;
				}
			}
			table[row] = newRow;
			for (int col = 0; col < 3; col++) {
				if (table[row][col] != 0 && table[row][col] == table[row][col - 1]) {
					table[row][col] *= 2;
					points += table[row][col];
					table[row][col + 1] = 0;
					col++;
				}
			}
			int[] mergedRow = new int [4];
			index = 0;
			for (int col = 0; col < 4; col++) {
				if (table[row][col] != 0) {
					mergedRow[index] = table[row][col];
					index++;
				}
			}
			table[row] = mergedRow;
		}
		return points;
	}

	@Override
	public int right() {
		int points = 0;
		for (int row = 0; row < 4; row++) {
			int[] newRow = new int[4];
			int index = 3;
			
			for (int col = 3; col >= 0; col--) {
				if (table[row][col] != 0 ) {
					newRow[index] = table[row][col];
					index--;
				}
			}
			table[row] = newRow;
			
			for (int col = 3; col > 0; col--) {
				if (table[row][col] != 0 && table[row][col] == table[row][col - 1]) {
					table[row][col] *= 2;
					points += table[row][col];
					table[row][col - 1] = 0;
					col--;
				}
			}
			int[] mergedRow = new int [4];
			index = 3;
			for (int col = 3; col >= 0; col--) {
				if (table[row][col] != 0) {
					mergedRow[index] = table[row][col];
					index--;
				}
			}
			table[row] = mergedRow;
		}
		return points;
	}

	@Override
	public int down() {
		int points = 0;
		for (int col = 0; col < 4; col++) {
			int[] newCol = new int[4];
			int index = 3;
			
			for (int row = 3; row >= 0; row--) {
				if (table[row][col] != 0 ) {
					newCol[index] = table[row][col];
					index--;
				}
			}
			for (int row = 0; row < 4; row++) {
				table[row][col] = newCol[row];
			}
			
			for (int row = 3; row > 0; row--) {
				if (table[row][col] != 0 && table[row][col] == table[row - 1][col]) {
					table[row][col] *= 2;
					points += table[row][col];
					table[row - 1][col] = 0;
					row--;
				}
			}
			int[] mergedCol = new int [4];
			index = 3;
			for (int row = 3; row >= 0; row--) {
				if (table[row][col] != 0) {
					mergedCol[index] = table[row][col];
					index--;
				}
			}
			for (int row = 0; row < 4; row++) {
				table[row][col] = mergedCol[row];
			}
		}
		return points;
	}

	@Override
	public int up() {
		int points = 0;
		for (int col = 0; col < 4; col++) {
			int[] newCol = new int[4];
			int index = 0;
			
			for (int row = 0; row < 4; row++) {
				if (table[row][col] != 0 ) {
					newCol[index] = table[row][col];
					index++;
				}
			}
			for (int row = 0; row < 4; row++) {
				table[row][col] = newCol[row];
			}
			
			for (int row = 0; row < 3; row++) {
				if (table[row][col] != 0 && table[row][col] == table[row + 1][col]) {
					table[row][col] *= 2;
					points += table[row][col];
					table[row + 1][col] = 0;
					row++;
				}
			}
			int[] mergedCol = new int [4];
			index = 0;
			for (int row = 0; row < 4; row++) {
				if (table[row][col] != 0) {
					mergedCol[index] = table[row][col];
					index++;
				}
			}
			for (int row = 0; row < 4; row++) {
				table[row][col] = mergedCol[row];
			}
		}
		return points;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(table);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		return Arrays.deepEquals(table, other.table);
	}

}
