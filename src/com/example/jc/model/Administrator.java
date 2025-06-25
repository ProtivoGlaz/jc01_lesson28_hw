package com.example.jc.model;


import java.util.Objects;

public class Administrator extends Person {
    private final String department;

    public Administrator(String name, String email, String department) {
        super(name, email);
        this.department = department;
    }

    public String getDepartment() {
        return this.department;
    }

    @Override
    public void performRole() {
        organizeProcess();
    }

    private String organizeProcess() {
        return getName() + " from " + getDepartment() + " is organizing the classroom.";
    }

    @Override
    public String getRoleDescription() {
        return String.format("Administrator of department: %s", department);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Administrator that = (Administrator) o;
        return Objects.equals(department, that.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), department);
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "department='" + department + '\'' +
                "} " + super.toString() + "\n";
    }
}
