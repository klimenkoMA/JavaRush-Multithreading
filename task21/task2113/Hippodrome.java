package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
	static Hippodrome game;
	private List<Horse> horses;

	public Hippodrome(List<Horse> horses) {
		this.horses = horses;
	}

	public static void main(String[] args) throws InterruptedException {
		ArrayList<Horse> horses = new ArrayList<>();
		Horse horse1 = new Horse("Bulka", 3, 0);
		Horse horse2 = new Horse("Konik", 3, 0);
		Horse horse3 = new Horse("Slonik", 3, 0);
		horses.add(horse1);
		horses.add(horse2);
		horses.add(horse3);
		game = new Hippodrome(horses);
		game.run();
		game.printWinner();
	}

	public Horse getWinner(){
		double dist = Integer.MIN_VALUE;
		Horse winner = null;
		for(Horse horse : horses) {
			if(horse.getDistance() >= dist) {
				dist = horse.getDistance();
			}
		}
		for(Horse hors : horses) {
			if(hors.getDistance() == dist) {
				winner = hors;
			}
		}
		return winner;
	}

	public void printWinner(){
		double dist = Integer.MIN_VALUE;
		for(Horse horse : horses) {
			if(horse.getDistance() >= dist) {
				dist = horse.getDistance();
			}
		}
		for(Horse hors : horses) {
			if(hors.getDistance() == dist) {
				System.out.println("Winner is " + hors.getName() + "!");
			}
		}
	}

	public List<Horse> getHorses() {
		return horses;
	}

	void run() throws InterruptedException {
		for(int i = 0; i < 100; i++) {
			move();
			print();
			Thread.sleep(200);
		}
	}

	void move() {
		for(Horse horse: horses
		    ) {
			horse.move();
		}
	}

	void print() {
		for(Horse horse: horses
		    ) {
			horse.print();
		}
		for(int i = 0; i < 10; i++) {
			System.out.println();
		}
	}
}
