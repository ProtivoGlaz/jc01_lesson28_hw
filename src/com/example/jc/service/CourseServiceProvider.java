package com.example.jc.service;

import com.example.jc.service.impl.CourseServiceImpl;

public class CourseServiceProvider {
    private static final CourseServiceProvider instance = new CourseServiceProvider();
    private final CourseService service = new CourseServiceImpl();

    private CourseServiceProvider() {
    }

    public CourseService getService() {
        return service;
    }

    public static CourseServiceProvider getInstance() {
        return instance;
    }
}
