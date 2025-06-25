package com.example.jc.repository;

import com.example.jc.repository.impl.FileCourseRepositoryImpl;
// import com.example.jc.repository.impl.MemoryCourseRepositoryImpl;

public class CourseRepositoryProvider {
    private static final CourseRepositoryProvider instance = new CourseRepositoryProvider();

    private final CourseRepository repository = new FileCourseRepositoryImpl();
    // private final CourseRepository repository = new MemoryCourseRepositoryImpl();

    private CourseRepositoryProvider() {
    }

    public CourseRepository getRepository() {
        return repository;
    }

    public static CourseRepositoryProvider getInstance() {
        return instance;
    }
}
