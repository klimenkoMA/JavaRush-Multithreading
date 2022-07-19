package com.javarush.task.task29.task2909.human;

import java.util.Date;

public class Student extends UniversityPerson {

	private double averageGrade;
	private Date beginningOfSession;
	private Date endOfSession;
	private int course;

	public Student(String name, int age, double averageGrade) {
		super(name, age);
		this.name = name;
		this.age = age;
		this.averageGrade = averageGrade;
	}

	@Override
	public String getPosition() {
		return "Студент";
	}

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	public void live() {
		learn();
	}

	public void learn() {
	}

	public void incAverageGrade(double delta) {
		double d = getAverageGrade();
		setAverageGrade(d + delta);
	}

	public void setBeginningOfSession(Date date) {
		beginningOfSession = date;
	}

	public void setEndOfSession(Date date) {
		endOfSession = date;
	}

	public double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}
}