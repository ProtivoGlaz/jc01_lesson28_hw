package com.example.jc.repository.impl;

import com.example.jc.model.Course;
import com.example.jc.model.Student;
import com.example.jc.repository.CourseRepository;
import com.example.jc.repository.RepositoryException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryCourseRepositoryImpl implements CourseRepository {
    private final Map<String, Course> storage = new ConcurrentHashMap<>();

    @Override
    public void save(Course course) throws RepositoryException {
        if (storage.containsKey(course.getName())) {
            throw new RepositoryException("Course exists: " + course.getName());
        }
        storage.put(course.getName(), course);
    }

    @Override
    public void update(Course course) throws RepositoryException {
        if (!storage.containsKey(course.getName())) {
            throw new RepositoryException("No such course: " + course.getName());
        }
        storage.put(course.getName(), course);
    }

    @Override
    public void delete(String courseName) {
        storage.remove(courseName);
    }

    @Override
    public Optional<Course> findByName(String courseName) {
        return Optional.ofNullable(storage.get(courseName));
    }

    @Override
    public List<Course> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public boolean exists(String courseName) {
        return storage.containsKey(courseName);
    }

    @Override
    public void obfuscate() {
        storage.values().forEach(course ->
                course.getParticipants().replaceAll(p ->
                        p instanceof Student s
                                ? new Student(s.getName(), "", s.getGroup(), s.getAverageGrade())
                                : p
                )
        );
    }
}
