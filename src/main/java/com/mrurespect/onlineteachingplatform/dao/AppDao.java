package com.mrurespect.onlineteachingplatform.dao;

import com.mrurespect.onlineteachingplatform.entity.Course;
import com.mrurespect.onlineteachingplatform.entity.Instructor;
import com.mrurespect.onlineteachingplatform.entity.InstructorDetail;
import com.mrurespect.onlineteachingplatform.entity.Student;

import java.util.List;

public interface AppDao {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);  //if only need instructor , no courses
    void deleteInstructorById(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
    public List<Course> findCOursesByInstructorId(int Id);
    public Instructor findInstructorByIdJoinFetch(int id); //if only need instructor ,and courses

    void update(Instructor instructor);
    Course findCourseById(int id);
    void update(Course course);
    void deleteCourseByID(int id);
    void save(Course course);
    Course findCourseAndReviewsByCourseID(int id);

    Course findCourseAndStudentByCourseId(int id);
    Student findStudentAndCoursesByStudentID(int id);
    void update(Student student);
    void deleteStudentByID(int id);


}
