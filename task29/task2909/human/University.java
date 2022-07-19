package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {

	private String name;
	private int age;
	private List<Student> students = new ArrayList<>();

	public University(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student getStudentWithAverageGrade(double averageGrade) {
		//TODO:
		Student target = null;
		for(Student std : students
		) {
			if(std.getAverageGrade() == averageGrade) {
				target = std;
			}
		}
		return target;
	}

	public Student getStudentWithMaxAverageGrade() {
		//TODO:
		Student target = null;
		double maxGrade = Double.MIN_VALUE;

		for(Student std : students
		) {
			if(std.getAverageGrade() >= maxGrade) {
				target = std;
				maxGrade = std.getAverageGrade();
			}
		}

		return target;
	}

	public Student getStudentWithMinAverageGrade() {
		//TODO:
		Student target = null;
		double minGrade = Double.MAX_VALUE;

		for(Student std : students
		) {
			if(std.getAverageGrade() <= minGrade) {
				target = std;
				minGrade = std.getAverageGrade();
			}
		}

		return target;
	}

	public void expel(Student student) {
		List<Student> clone = new ArrayList<>();
		for(Student std : students
		) {
			clone.add(std);
		}

		for(Student std : clone
		) {
			if(std.equals(student)) {
				students.remove(student);
			}
		}
	}
}