package com.javarush.task.task35.task3513;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {

	private static final int WINNING_TILE = 2048;
	private Model model;
	private View view;

	public Controller(Model model) {
		this.model = model;
		view = new View(this);
	}

	public View getView() {
		return view;
	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		if(KeyEvent.VK_ESCAPE == keyEvent.getKeyCode()) {
			resetGame();
		}

		if(!model.canMove()) {
			view.isGameLost = true;
		}

		if(!view.isGameLost & !view.isGameWon) {
			switch(keyEvent.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					model.left();
					break;
				case KeyEvent.VK_RIGHT:
					model.right();
					break;
				case KeyEvent.VK_UP:
					model.up();
					break;
				case KeyEvent.VK_DOWN:
					model.down();
					break;
				case KeyEvent.VK_Z:
					model.rollback();
					break;
				case KeyEvent.VK_R:
					model.randomMove();
					break;
				case KeyEvent.VK_A:
					model.autoMove();
					break;
			}
		}

		if(model.maxTile == WINNING_TILE) {
			view.isGameWon = true;
		}

		view.repaint();
	}

	void resetGame() {
		model.score = 0;
		view.isGameLost = false;
		view.isGameWon = false;
		model.resetGameTiles();
	}

	Tile[][] getGameTiles() {
		return model.getGameTiles();
	}

	int getScore() {
		return model.score;
	}
}
