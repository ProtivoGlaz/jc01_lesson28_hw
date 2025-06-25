package com.example.jc.model;

import java.util.Objects;

public class Student extends Person {
    private final String group;
    private final double averageGrade;

    public Student(String name, String email, String group, double averageGrade) {
        super(name, email);
        this.group = group;
        this.averageGrade = averageGrade;
    }

    public String getGroup() {
        return this.group;
    }

    public double getAverageGrade() {
        return this.averageGrade;
    }

    @Override
    public void performRole() {
        study();
    }

    private String study() {
        return getName() + " is attending the class.";
    }

    @Override
    public String getRoleDescription() {
        return String.format("Student from group %s with average grade: %.2f", group, averageGrade);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Double.compare(averageGrade, student.averageGrade) == 0 && Objects.equals(group, student.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), group, averageGrade);
    }

    @Override
    public String toString() {
        return "Student{" +
                "group='" + group + '\'' +
                ", averageGrade=" + averageGrade +
                "} " + super.toString() + "\n";
    }
}
