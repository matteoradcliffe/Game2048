package edu.wm.cs.cs301.game2048;

import java.util.List;
import java.util.ArrayList;
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
		addTile();
		addTile();
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
				if (table[i][j] <= 2048) {
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
				if (table[row][col] != 0 && table[row][col] == table[row][col + 1]) {
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
			
			for (int col = 0; col < 4; col++) {
				if (table[row][col] != 0 ) {
					newRow[index] = table[row][col];
					index--;
				}
			}
			table[row] = newRow;
			
			for (int col = 0; col < 3; col++) {
				if (table[row][col] != 0 && table[row][col] == table[row][col + 1]) {
					table[row][col] *= 2;
					points += table[row][col];
					table[row][col + 1] = 0;
					col++;
				}
			}
			int[] mergedRow = new int [4];
			index = 3;
			for (int col = 0; col < 4; col++) {
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int up() {
		int points = 0;
		for (int col = 0; col < 4; col++) {
			int[] tempList = new int[4];
			int index = 0;
			
			for (int row = 0; row < 4; row++) {
				
			}
		}
		return 0;
	}

}
