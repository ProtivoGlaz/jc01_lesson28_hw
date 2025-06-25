package com.example.jc.repository.impl;

import com.example.jc.model.Course;
import com.example.jc.model.Student;
import com.example.jc.repository.CourseRepository;
import com.example.jc.repository.RepositoryException;
import com.example.jc.repository.util.CourseFileManager;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileCourseRepositoryImpl implements CourseRepository {
    private static final Pattern COURSE_RE =
            Pattern.compile("^Course: (.+)$");
    private static final Pattern STUDENT_RE =
            Pattern.compile("^\\s+Name: (.*?), Group: (.*?), Grade: (.*?), Email: (.*)$");

    private final CourseFileManager fm = new CourseFileManager("courses.txt");

    private List<Course> loadAll() throws RepositoryException {
        List<Course> list = new ArrayList<>();
        Course curr = null;
        for (String raw : fm.readCourseLines()) {
            Matcher mc = COURSE_RE.matcher(raw);
            if (mc.matches()) {
                curr = new Course(mc.group(1).trim());
                list.add(curr);
                continue;
            }
            Matcher ms = STUDENT_RE.matcher(raw);
            if (curr != null && ms.matches()) {
                String name = ms.group(1).trim();
                String grp = ms.group(2).trim();
                double grd = Double.parseDouble(ms.group(3).trim().replace(',', '.'));
                String email = ms.group(4).trim();
                curr.addParticipant(new Student(name, email, grp, grd));
            }
        }
        return list;
    }

    @Override
    public void save(Course course) throws RepositoryException {
        List<Course> all = loadAll();
        if (all.stream().anyMatch(c -> c.getName().equalsIgnoreCase(course.getName()))) {
            throw new RepositoryException("Course exists: " + course.getName());
        }
        all.add(course);
        fm.writeCourses(all, false);
    }

    @Override
    public void update(Course course) throws RepositoryException {
        List<Course> all = loadAll();
        all.removeIf(c -> c.getName().equalsIgnoreCase(course.getName()));
        all.add(course);
        fm.writeCourses(all, false);
    }

    @Override
    public void delete(String courseName) throws RepositoryException {
        List<Course> all = loadAll();
        all.removeIf(c -> c.getName().equalsIgnoreCase(courseName));
        fm.writeCourses(all, false);
    }

    @Override
    public Optional<Course> findByName(String courseName) throws RepositoryException {
        return loadAll().stream()
                .filter(c -> c.getName().equalsIgnoreCase(courseName))
                .findFirst();
    }

    @Override
    public List<Course> findAll() throws RepositoryException {
        return loadAll();
    }

    @Override
    public boolean exists(String courseName) throws RepositoryException {
        return loadAll().stream()
                .anyMatch(c -> c.getName().equalsIgnoreCase(courseName));
    }

    @Override
    public void obfuscate() throws RepositoryException {
        List<Course> all = loadAll();
        fm.writeCourses(all, true);
    }
}
