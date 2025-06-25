package com.example.jc.repository;

import com.example.jc.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    void save(Course course) throws RepositoryException;

    void update(Course course) throws RepositoryException;

    void delete(String courseName) throws RepositoryException;

    Optional<Course> findByName(String courseName) throws RepositoryException;

    List<Course> findAll() throws RepositoryException;

    boolean exists(String courseName) throws RepositoryException;

    void obfuscate() throws RepositoryException;
}

