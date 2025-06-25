package com.example.jc.main;

import com.example.jc.controller.CourseController;
import com.example.jc.model.Course;
import com.example.jc.repository.CourseRepository;
import com.example.jc.repository.CourseRepositoryProvider;
import com.example.jc.repository.RepositoryException;

public class Main {
    public static void main(String[] args) throws RepositoryException {
        CourseController controller = new CourseController();

        Course currentCourse;

        String paramDelimetr = "\n";

        String response;
        String[] params;

        // CREATE_COURSE\nCourseName
        String courseName = "Java Core";
        response = controller.doAction("CREATE_COURSE" + paramDelimetr + courseName);
        params  = response.split("\n");
        System.out.println(response);

        System.out.println("=======================================================");

        // ADD_PARTICIPANT\nCourseName\nRole\nName\nEmail\n
        //(ifSTUDENT\nGroup\nGrade)
        response = controller.doAction("ADD_PARTICIPANT\n" + courseName + "\n" +"STUDENT\nVlad\nvlad@gmail.com\njc01\n10\n");
        params = response.split("\n");
        System.out.println(response);
        //(ifTEACHER\nSubject)
        response = controller.doAction("ADD_PARTICIPANT\n" + courseName + "\n" +"TEACHER\nOlga\nolga@gmail.com\nJavaEE\n");
        params = response.split("\n");
        System.out.println(response);
        //(ifADMIN\nDepartment)
        response = controller.doAction("ADD_PARTICIPANT\n" + courseName + "\n" +"ADMIN\nNata\nnata@gmail.com\nIT-Academy\n");
        params = response.split("\n");
        System.out.println(response);

        System.out.println("=======================================================");

        // SHOW_ALL_COURSES
        System.out.println(controller.doAction("SHOW_ALL_COURSES\n"));

        System.out.println("=======================================================");

        // WRONG_REQUEST
        System.out.println(controller.doAction("WRONG_REQUEST"  + paramDelimetr));

    }
}
