package com.example.jc.model;

import java.util.Objects;

public class Teacher extends Person {
    private final String subject;

    public Teacher(String name, String email, String subject) {
        super(name, email);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public void performRole() {
        teach();
    }

    private String teach() {
        return getName() + " is explaining " + subject + ".";
    }

    @Override
    public String getRoleDescription() {
        return String.format("Teacher of subject: %s", subject);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(subject, teacher.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subject);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "subject='" + subject + '\'' +
                "} " + super.toString() + "\n";
    }
}
