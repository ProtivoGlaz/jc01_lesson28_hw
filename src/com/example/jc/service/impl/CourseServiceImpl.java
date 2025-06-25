package com.example.jc.service.impl;

import com.example.jc.model.Course;
import com.example.jc.repository.CourseRepository;
import com.example.jc.repository.CourseRepositoryProvider;
import com.example.jc.repository.RepositoryException;
import com.example.jc.service.CourseService;
import com.example.jc.service.ServiceException;

import java.util.List;
import java.util.Optional;

public class CourseServiceImpl implements CourseService {
    private final CourseRepository repo = CourseRepositoryProvider.getInstance().getRepository();

    @Override
    public void createCourse(Course course) throws ServiceException {
        try {
            repo.save(course);
        } catch (RepositoryException e) {
            throw new ServiceException("Unable to create: " + course.getName(), e);
        }
    }

    @Override
    public void updateCourse(Course course) throws ServiceException {
        try {
            repo.update(course);
        } catch (RepositoryException e) {
            throw new ServiceException("Unable to update: " + course.getName(), e);
        }
    }

    @Override
    public boolean courseExists(String courseName) throws ServiceException {
        try {
            return repo.exists(courseName);
        } catch (RepositoryException e) {
            throw new ServiceException("Check-exists failed: " + courseName, e);
        }
    }

    @Override
    public List<Course> getAllCourses() throws ServiceException {
        try {
            return repo.findAll();
        } catch (RepositoryException e) {
            throw new ServiceException("Unable to list courses", e);
        }
    }

    public Optional<Course> getCourseByName(String name) throws ServiceException {
        try {
            return repo.findByName(name);
        } catch (RepositoryException e) {
            throw new ServiceException("Lookup failed: " + name, e);
        }
    }
}
