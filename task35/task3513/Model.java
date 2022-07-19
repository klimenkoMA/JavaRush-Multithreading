package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {

	private static final int FIELD_WIDTH = 4;
	int score;
	int maxTile;
	private Stack<Tile[][]> previousStates = new Stack();
	private Stack<Integer> previousScores = new Stack();
	private boolean isSaveNeeded = true;
	private Tile[][] gameTiles;

	public Model() {
		resetGameTiles();
		score = 0;
		maxTile = 0;
	}

	public Tile[][] getGameTiles() {
		return gameTiles;
	}

	void autoMove(){
		PriorityQueue<MoveEfficiency> queue =
				new PriorityQueue<>(4, Collections.reverseOrder());
		queue.offer(getMoveEfficiency(this::right));
		queue.offer(getMoveEfficiency(this::left));
		queue.offer(getMoveEfficiency(this::up));
		queue.offer(getMoveEfficiency(this::down));

		if(queue.peek() != null) {
			queue.peek().getMove().move();
		}
	}

	boolean hasBoardChanged(){

		int currentValues = 0;
		int previewValues = 0;

		for(Tile[] tiles: gameTiles
		    ) {
			for(Tile t: tiles
			    ) {
				currentValues += t.value;
			}
		}

		for(Tile[] tiles: previousStates.peek()
		) {
			for(Tile t: tiles
			) {
				previewValues += t.value;
			}
		}

		return (currentValues - previewValues) != 0;
	}

	MoveEfficiency getMoveEfficiency(Move move){

		MoveEfficiency moveEfficiency;
		int currentEmptyTiles = getEmptyTiles().size();
		int currentScore = score;

		move.move();
		if(!hasBoardChanged()){
			moveEfficiency = new MoveEfficiency(-1, 0, move);
		}else {
			moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
		}
		rollback();

		return moveEfficiency;
	}


	public void randomMove(){

		int n = ((int) (Math.random()*100)) % 4;
		switch(n){
			case 0:
				left();
				break;
			case 1:
				up();
				break;
			case 2:
				right();
				break;
			case 3:
				down();
				break;
		}
	}

	private void saveState(Tile[][] tiles) {

		tiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];

		for(int y = 0; y < FIELD_WIDTH; y++) {
			for(int x = 0; x < FIELD_WIDTH; x++) {
				tiles[y][x] = new Tile(gameTiles[y][x].value);
			}
		}

		previousStates.push(tiles);
		previousScores.push(score);
		isSaveNeeded = false;
	}

	public void rollback() {

		if(!previousStates.isEmpty() &
				!previousScores.isEmpty()) {
			gameTiles = previousStates.pop();
			score = previousScores.pop();
		}
	}

	boolean canMove() {
		boolean canMove = false;

		for(int y = FIELD_WIDTH - 1; y >= 0; y--) {
			for(int x = FIELD_WIDTH - 1; x >= 0; x--) {
				Tile tile = gameTiles[y][x];
				Tile beforeTile;
				Tile upTile;

				if(tile.value == 0) {
					canMove = true;
					break;
				}

				if(x != 0 & y != 0) {

					beforeTile = gameTiles[y][x - 1];
					upTile = gameTiles[y - 1][x];

					if(tile.value == beforeTile.value ||
							tile.value == upTile.value ||
							beforeTile.value == 0 ||
							upTile.value == 0) {
						canMove = true;
						break;
					}
				} else if(x == 0 & y != 0) {
					upTile = gameTiles[y - 1][x];
					if(tile.value == upTile.value ||
							upTile.value == 0) {
						canMove = true;
						break;
					}
				}
				if(x != 0 & y == 0) {
					beforeTile = gameTiles[y][x - 1];

					if(tile.value == beforeTile.value ||
							beforeTile.value == 0) {
						canMove = true;
						break;
					}
				}

			}
		}
		return canMove;
	}

	private boolean mergeTiles(Tile[] tiles) {

		boolean isChanged = false;

		for(int x = 1; x < tiles.length; x++) {
			Tile tile = tiles[x];
			Tile previewTile = tiles[x - 1];
			if(tile.value == previewTile.value & tile.value != 0) {
				previewTile.value *= 2;
				score += previewTile.value;
				if(previewTile.value > maxTile) {
					maxTile = previewTile.value;
				}
				tile.value = 0;
				compressTiles(tiles);
				isChanged = true;
			}
		}
		return isChanged;
	}

	void right() {

		saveState(gameTiles);

		turnTiles();
		turnTiles();
		left();
		turnTiles();
		turnTiles();
	}

	void up() {

		saveState(gameTiles);

		turnTiles();
		turnTiles();
		turnTiles();
		left();
		turnTiles();

	}

	void down() {

		saveState(gameTiles);

		turnTiles();
		left();
		turnTiles();
		turnTiles();
		turnTiles();
	}

	private void turnTiles() {

		Tile[][] reversTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];

		for(int y = 0; y < FIELD_WIDTH; y++) {
			for(int x = 0; x < FIELD_WIDTH; x++) {
				reversTiles[y][x] = gameTiles[FIELD_WIDTH - 1 - x]
						[y];
			}
		}

		for(int y = 0; y < FIELD_WIDTH; y++) {
			System.arraycopy(reversTiles[y], 0, gameTiles[y], 0, FIELD_WIDTH);
		}

	}

	void left() {

		if(isSaveNeeded) {
			saveState(gameTiles);
		}
		boolean isChanged = false;

		for(Tile[] tiles : gameTiles
		) {
			if(compressTiles(tiles) | mergeTiles(tiles)) {
				isChanged = true;
			}
		}
		if(isChanged) {
			addTile();
			isSaveNeeded = true;
		}

	}


	private boolean compressTiles(Tile[] tiles) {

		Tile zero = null;
		boolean isChanged = false;

		for(int x = 0; x < FIELD_WIDTH; x++) {

			int value = tiles[x].value;

			if(value == 0) {
				if(zero == null) {
					zero = tiles[x];
				}
			} else {
				if(zero != null) {
					zero.value = value;
					tiles[x].value = 0;
					zero = null;
					x = 0;
					isChanged = true;
				}
			}
		}
		return isChanged;
	}

	private List<Tile> getEmptyTiles() {

		List<Tile> tiles = new ArrayList<>();
		for(int i = 0; i < FIELD_WIDTH; i++) {
			for(int j = 0; j < FIELD_WIDTH; j++) {
				Tile tile = gameTiles[i][j];
				if(tile.isEmpty()) {
					tiles.add(tile);
				}
			}
		}

		return tiles;
	}

	private void addTile() {
		List<Tile> tiles = getEmptyTiles();

		if(!tiles.isEmpty()) {
			Tile tile = tiles.get(
					(int) (tiles.size() * Math.random()));
			tile.value = Math.random() < 0.9 ? 2 : 4;
		}
	}

	void resetGameTiles() {

		gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
		for(int i = 0; i < FIELD_WIDTH; i++) {
			for(int j = 0; j < FIELD_WIDTH; j++) {
				gameTiles[j][i] = new Tile();
			}
		}

		addTile();
		addTile();
	}
}
