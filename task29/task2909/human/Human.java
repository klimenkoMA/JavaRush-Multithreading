package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human implements Alive {
	private BloodGroup bloodGroup;
	private static int nextId = 0;
	protected int age;
	protected String name;
	protected Size size;
	private int id;
	private List<Human> children = new ArrayList<>();


	public class Size{
		public int height;
		public int weight;
	}

	public Human(String name, int age) {
		this.name = name;
		this.age = age;
		this.id = nextId;
		nextId++;
	}

	public String getPosition() {
		return "Человек";
	}

	public void printData() {
		System.out.println(getPosition() + ": " + name);
	}

	public List<Human> getChildren() {
		return Collections.unmodifiableList(children);
	}

	public void addChild(Human human) {
		children.add(human);
	}

	public void removeChild(Human human) {
		List<Human> clone = new ArrayList<>();
		for(Human hum : children
		) {
			clone.add(hum);
		}

		for(Human hum : clone
		) {
			if(hum.equals(human)) {
				children.remove(human);
			}
		}
	}

	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void live() {
	}

	public int getId() {
		return id;
	}

	public void printSize() {
		System.out.println("Рост: " + new Size().height + " Вес: " + new Size().weight);
	}
}