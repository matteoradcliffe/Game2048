package edu.wm.cs.cs301.game2048;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class SmartPlayer {

	public class State implements GameState {
		private int[][] table = new int[4][4];

		public State(GameState original) {
			
		}

		public State() {
			// TODO Auto-generated constructor stub
			
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
					if (table[i][j] >= 2048) {
						return true;
					}
				}
			}
			return false;
		}

		@Override
		public boolean reachedThreshold() {
			if (isFull()) {
				return true;
			}
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
			/*
			 * I need to iterate through each row in the table 2d array and in each row move the col(x) position to 0. once this is
			 * done I can then find the value of that tile, save it in a variable, and add each value of each row to find total. 
			 */
			return 0;
		}

		@Override
		public int right() {
			/*
			 * should be similar implementation to left() only diff is the indexes and for loops should be flip flopped.
			 */
			return 0;
		}

		@Override
		public int down() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int up() {
			// TODO Auto-generated method stub
			return 0;
		}

	}


}
